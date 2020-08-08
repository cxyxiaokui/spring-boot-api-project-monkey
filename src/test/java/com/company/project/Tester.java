package com.company.project;

import com.company.project.common.utils.JsonConvertUtil;
import com.company.project.core.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 单元测试继承该类即可
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Rollback
public abstract class Tester {
    @Autowired
    protected QuickMockMvc quickMockMvc;

    public  Result toResult(String jsonResult) {
        return JsonConvertUtil.jsonToObject(jsonResult, Result.class);
    }

    /**
     *  创建请求API Mock 访问
     * @param uri
     * @param entity
     * @return
     * @throws Exception
     */
    protected Result postRequest(String uri, BasicEntity entity) throws Exception {

        PostRequestMethodMockMvc post = new PostRequestMethodMockMvc(uri);
        post.setRequestBodyModel(entity);
        post.expectReponseStatus(MockMvcResultMatchers.status().isCreated());
        String jsonResult = quickMockMvc.setRequestMockMvc(post).start();

        Result result = toResult(jsonResult);
        return result;
    }

    protected Result getRequest(String uri) throws Exception {
        GetRequestMethodMockMvc get = new GetRequestMethodMockMvc(uri);
        String jsonResult = quickMockMvc.setRequestMockMvc(get).start();
        return toResult(jsonResult);
    }

    protected Object getDate(Result result,Class clazz) {
        String dataJsonString = JsonConvertUtil.toJson(result.getData());
        return JsonConvertUtil.jsonToObject(dataJsonString, clazz);
    }

    protected Result putRequest(String uri,BasicEntity entity) throws Exception {
        PutRequestMethodMockMvc putMockMvc = new PutRequestMethodMockMvc(uri);
        putMockMvc.setRequestBodyModel(entity);
        String jsonResult = quickMockMvc.setRequestMockMvc(putMockMvc).start();
        return toResult(jsonResult);
    }

    protected Result putBatchRequest(String uri,List list) throws Exception {
        PutRequestMethodMockMvc putMockMvc = new PutRequestMethodMockMvc(uri);
        putMockMvc.setRequestBodyModel(list);
        String jsonResult = quickMockMvc.setRequestMockMvc(putMockMvc).start();
        return toResult(jsonResult);
    }

    protected Result deleteRequest(String uri) throws Exception {
        DeleteRequestMethodMockMvc deleteMockMvc = new DeleteRequestMethodMockMvc(uri);
        String jsonResult = quickMockMvc.setRequestMockMvc(deleteMockMvc).start();
        return toResult(jsonResult);
    }

    protected Result deleteBatchRequest(String uri,List<Long> userIds) throws Exception {
        DeleteRequestMethodMockMvc deleteBatchMockMvc = new DeleteRequestMethodMockMvc(uri);
        deleteBatchMockMvc.setRequestBodyModel(userIds);
        String jsonResult = quickMockMvc.setRequestMockMvc(deleteBatchMockMvc).start();
        return toResult(jsonResult);
    }
}