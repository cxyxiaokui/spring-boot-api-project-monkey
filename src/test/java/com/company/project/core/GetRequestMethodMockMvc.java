package com.company.project.core;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


/**
 * GET 请求
 * @Author：zhuoqianmingyue
 * @Date： 2020/07/11
 * @Description：
**/
public class GetRequestMethodMockMvc extends AbstractRequestMethodMockMvc implements RequestMethodMockMvcStrategy {


    public GetRequestMethodMockMvc(String requestUri){
        super(requestUri);
    }

    public MockHttpServletRequestBuilder buildMockHttpServletRequest(String requestUri){
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(requestUri);
        return request;
    }
}
