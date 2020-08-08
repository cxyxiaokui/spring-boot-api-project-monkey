package com.company.project.generator.service;

import com.company.project.generator.mapper.CodeGenneratorMapper;
import com.company.project.generator.model.GenTable;
import com.company.project.generator.model.GenTableColumn;
import javafx.scene.control.TableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author： jkli
 * @Date： 2020/8/4 10:03 下午
 * @Description：
 **/
@Service
public class CodeGenneratorService {

    @Autowired
    private CodeGenneratorMapper codeGenneratorMapper;

    public GenTable getTableInfo(String tableName){
        GenTable table = codeGenneratorMapper.selectTableByTableName(tableName);
        List<GenTableColumn> tableColumns = codeGenneratorMapper.selectTableColumnsByTableName(tableName);
        for (GenTableColumn tableColumn : tableColumns) {
            if ("1".equals(tableColumn.getIsPk())) {
                table.setPkColumn(tableColumn);
            }
        }
        table.setColumns(tableColumns);
        return table;
    }
}
