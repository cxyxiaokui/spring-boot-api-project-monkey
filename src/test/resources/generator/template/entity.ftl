package ${basePackage}.${packageName}.domain;
import com.company.project.core.BasicEntity;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * @Author： ${author}
 * @Date： ${date}.
 * @Description：
 **/
public class ${modelNameUpperCamel} extends BasicEntity{

<#list table.columns as column>
<#if column.columnName != "update_time" && column.columnName != "create_time"
        && column.columnName != "update_by" && column.columnName != "create_by" && column.columnName != "delete_flag">

 <#if  (column.javaType == "Long")>
    @ApiModelProperty(value="${column.columnComment}",name="${column.javaField}",example="1")
 </#if>
 <#if  (column.javaType == "String")>
    @ApiModelProperty(value="${column.columnComment}",name="${column.javaField}",example="string")
 </#if>
 <#if  (column.javaType == "Integer")>
    @ApiModelProperty(value="${column.columnComment}",name="${column.javaField}",example="1")
 </#if>
 <#if  (column.javaType == "Date")>
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="${column.columnComment}",name="${column.javaField}",example="2020-01-01")
 </#if>
    private ${column.javaType} ${column.javaField};
</#if>
</#list>

<#list table.columns as column>
<#if column.columnName != "update_time" && column.columnName != "create_time"
        && column.columnName != "update_by" && column.columnName != "create_by" && column.columnName != "delete_flag">

    public ${column.javaType} get${column.javaFieldGetSetName}(){
        return ${column.javaField};
    }
    public void set${column.javaFieldGetSetName}(${column.javaType}  ${column.javaField}){
        this.${column.javaField} = ${column.javaField};
    }
</#if>
</#list>
}