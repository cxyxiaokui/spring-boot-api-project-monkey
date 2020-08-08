package com.company.project.generator.utils;

import com.google.common.base.CaseFormat;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @Author： jkli
 * @Date： 2020/8/4 10:11 下午
 * @Description：
 **/
public class FreemarkerUtil {
    private static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";//模板位置


    private Map data;
    public FreemarkerUtil( Map<String, Object> data){
        this.data = data;
    }

    public void createFile(String templateFile,String filePath) throws IOException, TemplateException {
        Configuration cfg = getConfiguration();
        File fileModel = new File(filePath);
        if (!fileModel.getParentFile().exists()) {
            fileModel.getParentFile().mkdirs();
        }
        FileWriter fileWriter = new FileWriter(fileModel);
        cfg.getTemplate(templateFile).process(data,
                fileWriter );
        fileWriter.flush();
        fileWriter.close();
    }

    private  freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private  String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }
}
