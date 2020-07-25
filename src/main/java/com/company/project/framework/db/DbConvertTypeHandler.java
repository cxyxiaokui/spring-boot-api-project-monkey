package com.company.project.framework.db;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;


/**
 * Mybatis 数据库 日期和ID 类型转换
 * @Author：zhuoqianmingyue
 * @Date： 2020/07/11
 * @Description：
**/

public class DbConvertTypeHandler extends BaseTypeHandler<Object> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object obj, JdbcType jdbcType) throws SQLException {
        if (jdbcType == JdbcType.BIGINT) {
            long value = Long.parseLong(obj.toString());
            preparedStatement.setLong(i, value);
        }else if(jdbcType == JdbcType.DATE){
            Date date = new Date(Long.parseLong(obj.toString()));
            preparedStatement.setDate(i,date);
        }else {
            preparedStatement.setObject(i, obj);
        }
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getObject(columnName);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return resultSet.getObject(columnIndex);
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int i) throws SQLException {
        return cs.getObject(i);
    }
}
