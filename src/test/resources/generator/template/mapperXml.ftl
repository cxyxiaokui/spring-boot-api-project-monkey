<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.${packageName}.mapper.${modelNameUpperCamel}Mapper">

    <select id="getById" resultType="${basePackage}.${packageName}.domain.${modelNameUpperCamel}">
        SELECT * FROM  ${table.tableName} WHERE ${table.pkColumn.columnName} = ${mybatisIdParam}
    </select>

    <select id="findByIds" resultType="${basePackage}.${packageName}.domain.${modelNameUpperCamel}">
        SELECT * FROM  ${table.tableName}  WHERE ${table.pkColumn.columnName} IN
        <foreach collection="ids" item="id" open="(" separator="," close=")">
            ${mybatisIdParam}
        </foreach>
    </select>

    <select id="find" resultType="${basePackage}.${packageName}.domain.${modelNameUpperCamel}">
        SELECT * FROM ${table.tableName}
    </select>

    <select id="findByMap" resultType="${basePackage}.${packageName}.domain.${modelNameUpperCamel}" parameterType="Map">
        SELECT * FROM ${table.tableName}
        <where>
            <#list table.columns as column>
                <#if column.columnName != "delete_flag" && column.columnName != table.pkColumn.columnName >
                    <#if column.javaType == "String" >
            <if test="${column.javaField} != null and ${column.javaField} != ''">
                ${column.columnName} = ${column.javaFieldXML}  AND
            </if>
                    <#else>
            <if test="${column.javaField} != null ">
                ${column.columnName} = ${column.javaFieldXML} AND
            </if>
                    </#if>
                </#if>
            </#list>
            delete_flag = 1
        </where>
    </select>

    <insert id="save" parameterType="${basePackage}.${packageName}.domain.${modelNameUpperCamel}" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO ${table.tableName}
        (
        <#list table.columns as column>
             <#if column.columnName != table.pkColumn.columnName &&  column.columnName != "create_time" &&  column.columnName != "delete_flag">
            `${column.columnName}`,
             </#if>
        </#list>
            `create_time`,
            `delete_flag`
        )
        VALUES
        (
        <#list table.columns as column>
             <#if column.columnName != table.pkColumn.columnName &&  column.columnName != "create_time" &&  column.columnName != "delete_flag">
            ${column.javaFieldXML} ,
             </#if>
        </#list>
            now(),
            1
        )
    </insert>
    <insert id="saveBatch" parameterType="List" useGeneratedKeys="true" keyProperty="id">
        <if test="list != null and list.size() > 0">
            INSERT INTO ${table.tableName}
            (
            <#list table.columns as column>
                <#if column.columnName != table.pkColumn.columnName &&  column.columnName != "create_time" &&  column.columnName != "delete_flag" >
                `${column.columnName}`,
                </#if>
            </#list>
                `create_time`,
                `delete_flag`
            )VALUES
            <foreach collection="list" item="item" index="index" separator=",">
                (

                <#list table.columns as column>
                     <#if column.columnName != table.pkColumn.columnName  &&  column.columnName != "create_time"  &&  column.columnName != "delete_flag" >
                        ${column.javaFieldBatchXML},
                     </#if>
                </#list>
                    now(),
                    1
                )
            </foreach>
        </if>
    </insert>
    <update id="update" parameterType="${basePackage}.${packageName}.domain.${modelNameUpperCamel}">
        UPDATE ${table.tableName}
        <set>
            <#list table.columns as column>
                <#if column.columnName != table.pkColumn.columnName  &&  column.columnName != "update_time" >
                <if test="${column.javaField} != null">  `${column.columnName}` = ${column.javaFieldXML}, </if>
                </#if>
            </#list>

            update_time = now()
        </set>

        WHERE ${table.pkColumn.columnName} = ${mybatisIdParam}
    </update>

    <update id="updateBatch"  >
        <foreach collection="list" item="item" index="index" separator=";" >
            UPDATE ${table.tableName}
            <set>
                <#list table.columns as column>
                     <#if column.columnName != table.pkColumn.columnName  &&  column.columnName != "update_time" >
                    <if test="item.${column.javaField} != null">  `${column.columnName}` =  ${column.javaFieldBatchXML}, </if>
                     </#if>
                </#list>
                update_time = now()
            </set>
            WHERE ${table.pkColumn.columnName} = ${mybatisBatchIdParam}
        </foreach>
    </update>

    <delete id="deleteById" parameterType="Long">
        DELETE  FROM  ${table.tableName} WHERE ${table.pkColumn.columnName} = ${mybatisIdParam}
    </delete>

    <delete id="deleteByIds" >
        DELETE FROM  ${table.tableName}  WHERE ${table.pkColumn.columnName} IN
        <foreach collection="ids" item="id" open="(" separator="," close=")">
            ${mybatisIdParam}
        </foreach>
    </delete>
</mapper>