package com.company.project.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 快速使用MockMvc 功能类
 * @Author： jkli
 * @Date： 2020/07/11
 * @Description：
 **/
@Component
public class QuickMockMvc {

    @Autowired
    private  WebApplicationContext context;
    private  MockMvc mockMvc;
    private RequestMethodMockMvcStrategy mvcTest;

    public QuickMockMvc() {}

    @PostConstruct
    public void buildMockMvc(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    public QuickMockMvc setRequestMockMvc(RequestMethodMockMvcStrategy mvcTest){
        this.mvcTest = mvcTest;
        return this;
    }
    public String start() throws Exception {
        return mvcTest.call(mockMvc);
    }
}
