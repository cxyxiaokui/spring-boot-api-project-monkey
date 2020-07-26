package com.company.project.generator.model;

/**
 * 处理 String 类型字段
 *
 * @Author： zhuoqianmingyue
 * @Date： 2020/7/26 8:36 上午
 * @Description：参考RuoYi 项目代码生成器处理逻辑  代码开源项目地址 RuoYi：https://github.com/lerry903/RuoYi
 **/
public class SetStringFieldTypeState extends SetJavaFieldTypeState {

    @Override
    public void setJavaType(GenTableColumn tableColumn) {

        String dataType = getDbType(tableColumn.getColumnType());
        if (arraysContains(GenConstants.COLUMNTYPE_STR, dataType)){
            tableColumn.setJavaType(GenConstants.TYPE_STRING);
        }else{
            switchState(dataType,tableColumn);
        }
    }

    @Override
    public void switchState(String dataType, GenTableColumn tableColumn) {
        if (arraysContains(GenConstants.COLUMNTYPE_NUMBER, dataType)) {
            tableColumn.setCurrent(new NumbeFieldTypeState()).getJavaType();
        }

        if (arraysContains(GenConstants.COLUMNTYPE_TIME, dataType)){
            tableColumn.setCurrent(new DateFieldTypeState()).getJavaType();
        }
    }

}
