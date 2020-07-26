package com.company.project.generator.mapper;

import com.company.project.generator.model.GenTable;
import com.company.project.generator.model.GenTableColumn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ruoyi
 *
 * @Editor： zhuoqianmingyue
 * @ModifiedDate： 2020/7/14 10:49 下午
 * @Description：参考 RuoYi 项目代码生成器代码 代码开源项目地址 RuoYi：https://github.com/lerry903/RuoYi
 **/
@Mapper
public interface CodeGenneratorMapper {

    /**
     * 根据表名查询表的信息
     *
     * @param tableName 表名称
     * @return 表信息
     */
    public GenTable selectTableByTableName(String tableName);

    /**
     * 查询据库表的所有列的信息
     *
     * @param tableName 表名
     * @return 数据库表列的集合
     */
    public List<GenTableColumn> selectTableColumnsByTableName(String tableName);
}
