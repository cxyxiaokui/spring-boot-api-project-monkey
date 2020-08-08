package com.company.project.generator;

import cn.hutool.extra.spring.SpringUtil;
import com.company.project.Application;
import com.company.project.generator.model.GenTable;
import com.company.project.generator.service.CodeGenneratorService;
import com.company.project.generator.utils.CodeNameConverUtil;
import com.company.project.generator.utils.FreemarkerUtil;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.util.*;
import static com.company.project.generator.constants.CodeGeneratorConstant.*;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 *
 * @Author：lihengming
 * @Date：2017/6/23
 * @Description：
 *
 * @Editor：zhuoqianmingyue
 * @ModifiedDate： 2020/08/08
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class CodeGenerator {

    private Logger log = LoggerFactory.getLogger(CodeGenerator.class);

    @Test
    public void genCodeByCustomModelName() throws IOException, TemplateException {
        this.genCodeByCustomModelName("demo_user","demoUser","DemoUser");
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public  void genCodeByCustomModelName(String tableName,String packageName, String modelName) throws IOException, TemplateException {
        genModelAndMapper(tableName,packageName, modelName);
        genService(tableName,packageName, modelName);
        genController(tableName,packageName, modelName);
        genControllerTest(tableName,packageName, modelName);
    }

    public  void genModelAndMapper(String tableName,String packageName, String modelName) {
        CodeGenneratorService codeGenneratorService = SpringUtil.getBean(CodeGenneratorService.class);
        GenTable tableInfo = codeGenneratorService.getTableInfo(tableName);

        Map<String, Object> data = initBaseTemplateParamMap(tableName,packageName,modelName);

        data.put("table",tableInfo);
        data.put("mybatisIdParam"," #{id}");
        data.put("mybatisBatchIdParam","#{item.id}");

        try {
            FreemarkerUtil freemarkerUtil = new FreemarkerUtil(data);

            String modelNameUpperCamel = (String)data.get("modelNameUpperCamel");
            String entityPath = JAVA_PATHP_REFIX + CodeNameConverUtil.packageConvertPath(BASE_PACKAGE + "." + packageName + ".domain") + modelNameUpperCamel + ".java";
            freemarkerUtil.createFile("entity.ftl",entityPath);
            log.info(modelNameUpperCamel + ".java 生成成功");

            String mapperPath = JAVA_PATHP_REFIX + CodeNameConverUtil.packageConvertPath(BASE_PACKAGE +"."+ packageName+ ".mapper") + modelNameUpperCamel + "Mapper.java";
            freemarkerUtil.createFile("mapper.ftl",mapperPath);
            log.info(modelNameUpperCamel + "Mapper.java 生成成功");

            String mapperXmlPath = RESOURCES_PATH_REFIX + "/mapper/" + modelNameUpperCamel + "Mapper.xml";
            freemarkerUtil.createFile("mapperXml.ftl",mapperXmlPath);
            log.info(modelNameUpperCamel + "Mapper.xml");
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public  void genService(String tableName,String packageName, String modelName) {
        try {
            Map<String, Object> data = initBaseTemplateParamMap(tableName,packageName, modelName);
            String modelNameUpperCamel = (String)data.get("modelNameUpperCamel");

            FreemarkerUtil freemarkerUtil = new FreemarkerUtil(data);
            String servicePath = JAVA_PATHP_REFIX + CodeNameConverUtil.packageConvertPath(BASE_PACKAGE +"."+ packageName+ ".service") + modelNameUpperCamel + "Service.java";
            freemarkerUtil.createFile("service.ftl",servicePath);
            log.info(modelNameUpperCamel + "Service.java 生成成功");

            String serviceImplPath =  JAVA_PATHP_REFIX + CodeNameConverUtil.packageConvertPath(BASE_PACKAGE  +"."+ packageName+".service" + ".impl") + modelNameUpperCamel + "ServiceImpl.java";
            freemarkerUtil.createFile("service-impl.ftl",serviceImplPath);
            log.info(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    public  void genController(String tableName,String packageName, String modelName) {
        try {

            Map<String, Object> data = initBaseTemplateParamMap(tableName,packageName, modelName);
            String modelNameUpperCamel = (String)data.get("modelNameUpperCamel");
            data.put("baseRequestMapping", CodeNameConverUtil.modelNameConvertMappingPath(modelNameUpperCamel));

            String controllerPath = PROJECT_PATH + JAVA_PATH + CodeNameConverUtil.packageConvertPath(BASE_PACKAGE  +"."+ packageName+ ".web") + modelNameUpperCamel + "Controller.java";
            FreemarkerUtil freemarkerUtil = new FreemarkerUtil(data);
            freemarkerUtil.createFile("controller-restful.ftl",controllerPath);
            log.info(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    public  void genControllerTest(String tableName,String packageName, String modelName) {
        try {

            Map<String, Object> data = initBaseTemplateParamMap(tableName,packageName, modelName);
            String modelNameUpperCamel = (String)data.get("modelNameUpperCamel");
            data.put("baseRequestMapping", CodeNameConverUtil.modelNameConvertMappingPath(modelNameUpperCamel));

            String controllerTestPath = PROJECT_PATH + JAVA_TEST_PATH + CodeNameConverUtil.packageConvertPath(BASE_PACKAGE  +"."+ packageName+ ".web") + modelNameUpperCamel + "ControllerTest.java";
            FreemarkerUtil freemarkerUtil = new FreemarkerUtil(data);
            freemarkerUtil.createFile("controller-restful-test.ftl",controllerTestPath);
            log.info(modelNameUpperCamel + "ControllerTest.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    private Map<String, Object> initBaseTemplateParamMap(String tableName,String packageName,String modelName) {
        Map<String, Object> data = new HashMap<>();
        data.put("date", DATE);
        data.put("author", AUTHOR);
        String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? CodeNameConverUtil.tableNameConvertUpperCamel(tableName) : modelName;
        data.put("modelNameUpperCamel", modelNameUpperCamel);
        data.put("modelNameLowerCamel", CodeNameConverUtil.tableNameConvertLowerCamel(tableName));
        data.put("basePackage", BASE_PACKAGE);
        data.put("packageName", packageName);
        return data;
    }
}
