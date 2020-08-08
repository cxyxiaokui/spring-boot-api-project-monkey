package com.company.project.core;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * POST 请求
 * @Author：zhuoqianmingyue
 * @Date： 2020/07/11
 * @Description：
**/
public class PostRequestMethodMockMvc extends AbstractRequestMethodMockMvc implements RequestMethodMockMvcStrategy {

    public PostRequestMethodMockMvc(String requestUri){
        super(requestUri);
    }

    public MockHttpServletRequestBuilder buildMockHttpServletRequest(String requestUri){
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(requestUri);
        return request;
    }
}
