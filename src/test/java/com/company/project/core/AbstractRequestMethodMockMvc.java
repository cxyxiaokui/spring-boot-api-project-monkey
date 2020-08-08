package com.company.project.core;

import cn.hutool.core.util.StrUtil;
import com.company.project.common.utils.JsonConvertUtil;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 不同请求MockMvc 请求的抽象
 * @Author： jkli
 * @Date： 2020/6/25 9:17 下午
 * @Description：
 **/

public abstract class AbstractRequestMethodMockMvc implements RequestMethodMockMvcStrategy {

    /**
     * 请求 URI
     * */
    private String requestUri;
    /**
     * MockMvc
     * */
    private MockMvc mockMvc;
    /**
     * 期望的响应状态码的值
     * */
    private ResultMatcher expectReponseStatus;
    /**
     * 传递JSON 对象的参数
     * */
    private Object requestBodyModel;

    /**
     * 认证授权的 accessToken
     * */
    private String accessToken;
    /**
     * 请求参数日期格式
     */
    private String requestBodyModelDateFormat;


    public AbstractRequestMethodMockMvc(String controllerUri){
        this.requestUri = controllerUri;
    }

    public void setRequestBodyModelDateFormat(String requestBodyModelDateFormat){
        this.requestBodyModelDateFormat = requestBodyModelDateFormat;
    }

    /**
     * 调用 请求 API并获取结果
     * @return
     * @throws Exception
     */
    public String call(MockMvc mockMvc) throws Exception {

        MockHttpServletRequestBuilder request = buildMockHttpServletRequest(requestUri);

        authorization(request);
        setRequestBody(requestBodyModel,request);

        return getResult(mockMvc,request);
    }

    private void setRequestBody(Object model,MockHttpServletRequestBuilder request) {
        if(model != null){
            if (StrUtil.isEmpty(requestBodyModelDateFormat)) {
                requestBodyModelDateFormat = "yyyy-MM-dd";
            }
            String modelJson = JsonConvertUtil.toJson(model, requestBodyModelDateFormat, true);
            request.content(modelJson).contentType(MediaType.APPLICATION_JSON);
        }
    }

    public abstract MockHttpServletRequestBuilder buildMockHttpServletRequest(String uri);

    /**
     * 设置认证授权的 accessToken
     * @param accessToken
     */
    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    /**
     *  请求的JSON 数据Model
     * @param requestBodyModel
     */
    public void setRequestBodyModel(Object requestBodyModel){
        this.requestBodyModel = requestBodyModel;
    }

    /**
     * 设置期望的响应状态码的值 例如 200
     * @param expect
     */
    public void expectReponseStatus(ResultMatcher expect){
        this.expectReponseStatus = expect;
    }

    /**
     * 期望的响应状态码 默认是 200
     * @return ResultMatcher
     */
    private ResultMatcher expectStatus() {
        if(expectReponseStatus != null){
            return expectReponseStatus;
        }
        return MockMvcResultMatchers.status().isOk();
    }
    /**
     * 执行MockMvc 并获取Result
     * @param mockMvc
     * @param request
     * @return
     * @throws Exception
     */
    protected String getResult(MockMvc mockMvc, MockHttpServletRequestBuilder request) throws Exception {
        MvcResult mvcResult = this.perform(mockMvc,request);
        MockHttpServletResponse response = mvcResult.getResponse();
        return response.getContentAsString();
    }

    /**
     * 认证授权
     * @param request
     */
    public void authorization(MockHttpServletRequestBuilder request){
        if (StrUtil.isNotEmpty(accessToken)) {
            request.header("Authorization",accessToken);
        }
    }

    /**
     *  MockMoc 具体执行API 打印执行请求和响应信息
     * @param request
     * @return MvcResult
     * @throws Exception
     */
    private  MvcResult perform(MockMvc mockMvc,MockHttpServletRequestBuilder request) throws Exception {

        ResultMatcher ok = expectStatus();//MockMvcResultMatchers.status().isOk();
        MvcResult mvcResult = mockMvc.perform(request)
                //将请求信息和响应信息都打印出来，添加ResultHandler结果处理器，比如调试时打印结果到控制台（对返回的数据进行的判断）
                .andDo(MockMvcResultHandlers.print())
                //添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确（对返回的数据进行的判断），等同于Assert.assertEquals(200,status);
                .andExpect(ok)
                //最后返回相应的MvcResult；然后进行自定义验证/进行下一步的异步处理
                .andReturn();
        return mvcResult;
    }


}
