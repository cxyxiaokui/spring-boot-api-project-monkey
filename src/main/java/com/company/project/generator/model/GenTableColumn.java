package com.company.project.generator.model;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.CaseFormat;

/**
 * 代码生成业务字段表 gen_table_column
 * @author ruoyi
 *
 * @Editor： zhuoqianmingyue
 * @ModifiedDate： 2020/7/14 10:50 下午
 * @Description：参考RuoYi 项目代码生成器代码  代码开源项目地址 RuoYi：https://github.com/lerry903/RuoYi
 **/
public class GenTableColumn {

    private SetJavaFieldTypeState current;

    public GenTableColumn setCurrent(SetJavaFieldTypeState current) {
        this.current = current;
        return this;
    }

    public GenTableColumn(){
        current = new SetStringFieldTypeState();
    }
    /** 编号 */
    private Long columnId;

    /** 归属表编号 */
    private Long tableId;

    /** 列名称 */
    private String columnName;

    /** 列描述 */
    private String columnComment;

    /** 列类型 */
    private String columnType;

    /** JAVA类型 */
    private String javaType;

    /** JAVA字段名 */
    private String javaField;

    private String javaFieldXML;

    public String getJavaFieldBatchXML() {
        return "#{item."+this.getJavaField()+"}";
    }

    private String javaFieldBatchXML;

    public String getJavaFieldXML() {
        return "#{"+this.getJavaField()+"}";
    }

    public String getJavaFieldGetSetName() {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, this.columnName.toLowerCase());
    }

    private String javaFieldGetSetName;

    /** 是否主键（1是） */
    private String isPk;

    /** 是否自增（1是） */
    private String isIncrement;

    /** 是否必填（1是） */
    private String isRequired;

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getJavaType() {
        current.setJavaType(this);
        return this.javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaField() {
        String columnName = this.getColumnName();
        this.setJavaField(StrUtil.toCamelCase(columnName));
        return javaField;
    }

    public void setJavaField(String javaField) {
        this.javaField = javaField;
    }

    public String getIsPk() {
        return isPk;
    }

    public void setIsPk(String isPk) {
        this.isPk = isPk;
    }

    public String getIsIncrement() {
        return isIncrement;
    }

    public void setIsIncrement(String isIncrement) {
        this.isIncrement = isIncrement;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }
}
