package com.company.project.core;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * DELETE 请求
 * @Author：zhuoqianmingyue
 * @Date：2020/07/11
 * @Description：
**/
public class DeleteRequestMethodMockMvc extends AbstractRequestMethodMockMvc implements RequestMethodMockMvcStrategy {

    public DeleteRequestMethodMockMvc(String requestUri){
       super(requestUri);
    }

    public MockHttpServletRequestBuilder buildMockHttpServletRequest(String requestUri){
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(requestUri);
        return request;
    }
}
