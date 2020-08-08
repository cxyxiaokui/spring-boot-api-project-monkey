package com.company.project.generator.service;

import com.company.project.Application;
import com.company.project.generator.model.GenTable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author： jkli
 * @Date： 2020/8/4 10:54 下午
 * @Description：
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CodeGenneratorServiceTest {

    @Autowired
    private CodeGenneratorService CodeGenneratorService;
    @Test
    public void getTableInfo(){
        GenTable tableInfo = CodeGenneratorService.getTableInfo("demo_user");
        Assert.assertNotNull(tableInfo);
    }

}