package com.company.project.generator.model;

/**
 * 代码生成通用常量
 * 
 * @author ruoyi
 *
 * @Editor：zhuoqianmingyue
 * @ModifiedDate： 2020/07/26
 * @Description：去除部分Monkey使用不到的常量。代码开源项目地址 RuoYi：https://github.com/lerry903/RuoYi
 */
public class GenConstants {

    /** 数据库字符串类型 */
    public static final String[] COLUMNTYPE_STR = { "char", "varchar", "narchar", "varchar2", "tinytext", "text",
            "mediumtext", "longtext" };

    /** 数据库时间类型 */
    public static final String[] COLUMNTYPE_TIME = { "datetime", "time", "date", "timestamp" };

    /** 数据库数字类型 */
    public static final String[] COLUMNTYPE_NUMBER = { "tinyint", "smallint", "mediumint", "int", "number", "integer",
            "bigint", "float", "double", "decimal" };

    /** Entity基类字段 */
    public static final String[] BASE_ENTITY = { "createBy", "createTime", "updateBy", "updateTime", "remark" };


    /** 文本框 */
    public static final String HTML_INPUT = "input";

    /** 文本域 */
    public static final String HTML_TEXTAREA = "textarea";

    /** 下拉框 */
    public static final String HTML_SELECT = "select";

    /** 单选框 */
    public static final String HTML_RADIO = "radio";

    /** 复选框 */
    public static final String HTML_CHECKBOX = "checkbox";

    /** 日期控件 */
    public static final String HTML_DATETIME = "datetime";

    /** 字符串类型 */
    public static final String TYPE_STRING = "String";

    /** 整型 */
    public static final String TYPE_INTEGER = "Integer";

    /** 长整型 */
    public static final String TYPE_LONG = "Long";

    /** 浮点型 */
    public static final String TYPE_DOUBLE = "Double";

    /** 高精度计算类型 */
    public static final String TYPE_BIGDECIMAL = "BigDecimal";

    /** 时间类型 */
    public static final String TYPE_DATE = "Date";
}
