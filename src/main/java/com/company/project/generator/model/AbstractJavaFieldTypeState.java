package com.company.project.generator.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @Author： zhuoqianmingyue
 * @Date： 2020/7/26 8:25 上午
 * @Description：参考RuoYi 项目代码生成器处理逻辑  代码开源项目地址 RuoYi：https://github.com/lerry903/RuoYi
 **/
public abstract class AbstractJavaFieldTypeState {

    public static final String LEFT_PARENTHESIS = "(";

    /**
     * 设置Java字段类型
     * @param tableColumn
     */
    public abstract void setJavaType(GenTableColumn tableColumn);

    /**
     * 切换状态
     * @param dataType
     * @param tableColumn
     */
    public abstract void switchState(String dataType, GenTableColumn tableColumn);
    /**
     * 校验数组是否包含指定值
     *
     * @param arr 数组
     * @param targetValue 值
     * @return 是否包含
     */
    public  boolean arraysContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 获取数据库类型字段
     *
     * @author ruoyi
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static String getDbType(String columnType) {
        if (StringUtils.indexOf(columnType, LEFT_PARENTHESIS) > 0) {
            return StringUtils.substringBefore(columnType, LEFT_PARENTHESIS);
        } else {
            return columnType;
        }
    }

}
