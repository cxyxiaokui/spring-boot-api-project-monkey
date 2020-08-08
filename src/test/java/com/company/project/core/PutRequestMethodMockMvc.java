package com.company.project.core;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * PUT 请求
 * @Author：zhuoqianmingyue
 * @Date： 2020/07/11
 * @Description：
**/
public class PutRequestMethodMockMvc extends AbstractRequestMethodMockMvc implements RequestMethodMockMvcStrategy {

    public PutRequestMethodMockMvc(String requestUri){
       super(requestUri);
    }

    public MockHttpServletRequestBuilder buildMockHttpServletRequest(String requestUri){
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(requestUri);
        return request;
    }
}
