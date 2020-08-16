package com.company.project.generator.model;

import org.apache.commons.lang3.StringUtils;

/**
 * 处理数字类型字段
 *
 * @Author： zhuoqianmingyue
 * @Date： 2020/7/26 8:38 上午
 * @Description：参考RuoYi 项目代码生成器处理逻辑  代码开源项目地址 RuoYi：https://github.com/lerry903/RuoYi
 **/
public class NumbeFieldTypeState extends AbstractJavaFieldTypeState {
    @Override
    public void setJavaType(GenTableColumn tableColumn) {
        String dataType = getDbType(tableColumn.getColumnType());
        if (arraysContains(GenConstants.COLUMNTYPE_NUMBER, dataType)) {
            // 如果是浮点型
            String[] str = StringUtils.split(StringUtils.substringBetween(tableColumn.getColumnType(), "(", ")"), ",");
            int numberOfDecimals = 2;
            int keepDecimalPlaces = 0;
            if (str != null && str.length == numberOfDecimals && Integer.parseInt(str[1]) > keepDecimalPlaces) {
                tableColumn.setJavaType(GenConstants.TYPE_BIGDECIMAL);
            }
            // 如果是整形
            int numberMinValue = 1;
            int numberMaxValue = 10;
            if (str != null && str.length == numberMinValue && Integer.parseInt(str[0]) <= numberMaxValue) {
                tableColumn.setJavaType(GenConstants.TYPE_INTEGER);
            }
            // 长整形
            if (str != null && str.length == numberMinValue && Integer.parseInt(str[0]) > numberMaxValue) {
                tableColumn.setJavaType(GenConstants.TYPE_LONG);
            }
        }else{
            switchState(dataType,tableColumn);
        }
    }

    @Override
    public void switchState(String dataType, GenTableColumn tableColumn) {
            tableColumn.setCurrent(new SetStringFieldTypeState()).getJavaType();
    }
}
