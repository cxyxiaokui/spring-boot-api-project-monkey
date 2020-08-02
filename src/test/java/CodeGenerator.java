import cn.hutool.extra.spring.SpringUtil;
import com.company.project.Application;
import com.company.project.generator.mapper.CodeGenneratorMapper;
import com.company.project.generator.model.GenTable;
import com.company.project.generator.model.GenTableColumn;
import com.google.common.base.CaseFormat;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.company.project.common.constants.ProjectConstant.*;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 *
 * @Author：lihengming
 * @Date：2017/6/23
 * @Description：
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class CodeGenerator {


    private static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";//模板位置

    private static final String JAVA_PATH = "/src/main/java"; //java文件路径
    private static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径


    private static final String AUTHOR = "CodeGenerator";//@author
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date

    public static void main(String[] args) throws IOException, TemplateException {
        //genCode("输入表名");
        //genCodeByCustomModelName("demo_user",".demoUser","DemoUser");
    }

    @Test
    public void genCodeByCustomModelName() throws IOException, TemplateException {
        this.genCodeByCustomModelName("demo_user","demoUser","DemoUser");
    }


    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     * @param tableNames 数据表名称...
     */
    public  void genCode(String... tableNames) throws IOException, TemplateException {
        for (String tableName : tableNames) {
            genCodeByCustomModelName(tableName, null,null);
        }
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
    }


    public  void genModelAndMapper(String tableName,String packageName, String modelName) {
        CodeGenneratorMapper codeGenneratorMapper = SpringUtil.getBean(CodeGenneratorMapper.class);
        GenTable table = codeGenneratorMapper.selectTableByTableName(tableName);
        List<GenTableColumn> tableColumns = codeGenneratorMapper.selectTableColumnsByTableName(tableName);
        for (GenTableColumn tableColumn : tableColumns) {
            if ("1".equals(tableColumn.getIsPk())) {
                table.setPkColumn(tableColumn);
            }
        }
        table.setColumns(tableColumns);

        Map<String, Object> data = new HashMap<>();
        data.put("date", DATE);
        data.put("author", AUTHOR);
        String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
        data.put("modelNameUpperCamel", modelNameUpperCamel);
        data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
        data.put("basePackage", BASE_PACKAGE);
        data.put("packageName", packageName);
        data.put("table",table);
        data.put("mybatisIdParam"," #{id}");
        data.put("mybatisBatchIdParam","#{item.id}");


        freemarker.template.Configuration cfg = null;
        try {
            cfg = getConfiguration();
            File fileModel = new File(PROJECT_PATH + JAVA_PATH +  packageConvertPath( BASE_PACKAGE+"."+ packageName+ ".domain") + modelNameUpperCamel + ".java");
            if (!fileModel.getParentFile().exists()) {
                fileModel.getParentFile().mkdirs();
            }
            cfg.getTemplate("entity.ftl").process(data,
                    new FileWriter(fileModel));
            System.out.println(modelNameUpperCamel + ".java 生成成功");

            File file = new File(PROJECT_PATH + JAVA_PATH + packageConvertPath(BASE_PACKAGE +"."+ packageName+ ".mapper") + modelNameUpperCamel + "Mapper.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("mapper.ftl").process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Mapper.java 生成成功");

            File fileXml = new File(PROJECT_PATH + RESOURCES_PATH + "/mapper/" + modelNameUpperCamel + "Mapper.xml");
            if (!fileXml.getParentFile().exists()) {
                fileXml.getParentFile().mkdirs();
            }
            cfg.getTemplate("mapperXml.ftl").process(data,
                    new FileWriter(fileXml));
            System.out.println(modelNameUpperCamel + "Mapper.xml");
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

    }

    public  void genService(String tableName,String packageName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", BASE_PACKAGE);
            data.put("packageName", packageName);

            File file = new File(PROJECT_PATH + JAVA_PATH + packageConvertPath(BASE_PACKAGE +"."+ packageName+ ".service") + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(PROJECT_PATH + JAVA_PATH + packageConvertPath(BASE_PACKAGE  +"."+ packageName+".service" + ".impl") + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    public  void genController(String tableName,String packageName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            data.put("packageName", packageName);

            File file = new File(PROJECT_PATH + JAVA_PATH + packageConvertPath(BASE_PACKAGE  +"."+ packageName+ ".web") + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("controller-restful.ftl").process(data, new FileWriter(file));
           // cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    private  freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private  String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private  String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    private  String tableNameConvertMappingPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private  String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private  String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

}
