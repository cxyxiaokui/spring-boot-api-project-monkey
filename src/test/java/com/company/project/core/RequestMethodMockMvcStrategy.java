package com.company.project.core;

import org.springframework.test.web.servlet.MockMvc;

/**
 * MvcMvc 请求类型的策略接口
 * @Author： jkli
 * @Date： 2020/07/11
 * @Description：
 **/
public interface RequestMethodMockMvcStrategy {

    String call(MockMvc mockMvc) throws Exception;
}
