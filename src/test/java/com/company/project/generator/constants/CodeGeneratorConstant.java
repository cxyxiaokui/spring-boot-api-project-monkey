package com.company.project.generator.constants;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author：zhuoqianmingyue
 * @Date： 2020/6/21
 * @Description： 项目公共常量
**/
public final class CodeGeneratorConstant {

    /**生成代码所在的基础包名称，可根据自己公司的项目修改（注意：这个配置修改之后需要手工修改src目录项目默认的包路径，使其保持一致，不然会找不到类）*/
    public static final String BASE_PACKAGE = "com.company.project";
    /**项目在硬盘上的基础路径*/
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    /**java文件路径*/
    public static final String JAVA_PATH = "/src/main/java";
    /**test文件路径*/
    public static final String JAVA_TEST_PATH = "/src/test/java";
    /**java绝对文件路径前缀*/
    public static final String JAVA_PATHP_REFIX = PROJECT_PATH + JAVA_PATH;
    /**资源文件路径*/
    public static final String RESOURCES_PATH = "/src/main/resources";
    /**资源文件绝对路径前缀*/
    public static final String RESOURCES_PATH_REFIX = PROJECT_PATH + RESOURCES_PATH;
    /**代码作者*/
    public static final String AUTHOR = "zhuoqianmingyue";
    /**日期*/
    public static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
}
