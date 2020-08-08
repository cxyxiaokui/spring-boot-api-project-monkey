package com.company.project.generator.utils;

import com.google.common.base.CaseFormat;

/**
 * @Author： jkli
 * @Date： 2020/8/8 4:49 下午
 * @Description：
 **/
public class CodeNameConverUtil {
    /**
     *  将模块名转换成Mapping 路径
     * @param modelName
     * @return
     */
    public static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }
    /**
     *  将表名转换成Mapping 路径
     * @param tableName
     * @return
     */
    public static  String tableNameConvertMappingPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }
    /**
     *  代码包路径转换成文件路径
     * @param packageName
     * @return
     */
    public static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    /**
     *  将表名转出成驼峰风格并且首字母小写
     * @param tableName
     * @return
     */
    public static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    /**
     * 将表名转出成驼峰风格并且首字母大写
     * @param tableName
     * @return
     */
    public static  String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }
}
