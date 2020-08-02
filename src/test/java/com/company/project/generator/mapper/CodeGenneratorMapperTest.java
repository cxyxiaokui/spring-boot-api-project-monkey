package com.company.project.generator.mapper;

import cn.hutool.extra.spring.SpringUtil;
import com.company.project.generator.model.GenTable;
import com.company.project.generator.model.GenTableColumn;
import com.conpany.project.Tester;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @Author： jkli
 * @Date： 2020/7/25 8:56 下午
 * @Description：
 **/
public class CodeGenneratorMapperTest extends Tester {

    @Test
    public void CodeGenneratorMapper() {
        CodeGenneratorMapper codeGenneratorMapper = SpringUtil.getBean(CodeGenneratorMapper.class);
        GenTable demoUser = codeGenneratorMapper.selectTableByTableName("demo_user");
        Assert.assertNotNull(demoUser);
    }

    @Test
    public void selectTableColumnsByTableName() {
        CodeGenneratorMapper codeGenneratorMapper = SpringUtil.getBean(CodeGenneratorMapper.class);
        List<GenTableColumn> demoUserTableColumn = codeGenneratorMapper.selectTableColumnsByTableName("demo_user");
        for (GenTableColumn tableColumn : demoUserTableColumn) {
            String javaType = tableColumn.getJavaType();
            String javaField = tableColumn.getJavaField();
            String javaFieldGetSetName = tableColumn.getJavaFieldGetSetName();
            System.out.println(javaFieldGetSetName);
            System.out.println(javaType);
            System.out.println(javaField);
        }
        Assert.assertNotNull(demoUserTableColumn);
    }
}