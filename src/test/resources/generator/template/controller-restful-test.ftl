package ${basePackage}.${packageName}.web;

import ${basePackage}.Tester;
import ${basePackage}.core.*;
import ${basePackage}.${packageName}.domain.${modelNameUpperCamel};
import ${basePackage}.${packageName}.service.${modelNameUpperCamel}Service;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author： ${author}
 * @Date： ${date}.
 * @Description：
 **/
public class ${modelNameUpperCamel}ControllerTest extends Tester{

    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    private static String URI = "${baseRequestMapping}";

    /**
        * 新增 API 单元测试
        * @throws Exception
    */
    @Test
    public void testAdd() throws Exception {
        //清空数据
        deleteAllData();
        //调用创建数据API
        ${modelNameUpperCamel} ${modelNameLowerCamel} = buildEntity();
        Result result = postRequest(URI, ${modelNameLowerCamel});
        //是否与预期相同断言
        List< ${modelNameUpperCamel}> ${modelNameLowerCamel}List = ${modelNameLowerCamel}Service.find();
        Assert.assertEquals(result.getCode(), ResultCode.SUCCESS.code());
        Assert.assertNotNull(${modelNameLowerCamel}List);
        Assert.assertEquals(${modelNameLowerCamel}List.size(), 1);
    }



    /**
    * 根据ID查询 API 单元测试
    * @throws Exception
    */
    @Test
    public void testGetById() throws Exception {
        Long id = addDataToDB();

        Result result = getRequest(URI+"/"+id);

        ${modelNameUpperCamel} ${modelNameLowerCamel} = (${modelNameUpperCamel})getDate(result,${modelNameUpperCamel}.class);

        Assert.assertEquals(result.getCode(), ResultCode.SUCCESS.code());
        Assert.assertEquals(${modelNameLowerCamel}.getId(), id);
    }
    /**
     * 分页查询 API 单元测试
     * @throws Exception
    */
    @Test
    public void testQuerylist() throws Exception {
        //清空数据
        deleteAllData();
        //调用创建数据API
        for (int i = 0; i < 12; i++) {
            addDataToDB();
        }
        Result result = getRequest(URI);

        PageInfo page = (PageInfo)getDate(result, PageInfo.class);
        Assert.assertEquals(result.getCode(), ResultCode.SUCCESS.code());
        Assert.assertEquals(page.getPages(), 2);
        Assert.assertEquals(page.getTotal(), 12);
    }

    /**
     * 修改 API 单元测试
     * @throws Exception
    */
    @Test
    public void testUpdate() throws Exception {

        deleteAllData();
        ${modelNameUpperCamel} ${modelNameLowerCamel} = buildUpdateEntity();

        Result result = putRequest(URI,${modelNameLowerCamel});

        ${modelNameUpperCamel} ${modelNameLowerCamel}Update = ${modelNameLowerCamel}Service.getById(${modelNameLowerCamel}.getId());
        Integer deleteFlag = 0;
        Assert.assertEquals(result.getCode(), ResultCode.SUCCESS.code());
        Assert.assertEquals(${modelNameLowerCamel}Update.getDeleteFlag(), deleteFlag);
    }
    /**
     * 批量修改 API 单元测试
     * @throws Exception
    */
    @Test
    public void testUpdateBatch() throws Exception {

        deleteAllData();

        List<${modelNameUpperCamel}> ${modelNameLowerCamel}List = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ${modelNameUpperCamel} ${modelNameLowerCamel} = buildUpdateEntity();
            ${modelNameLowerCamel}List.add(${modelNameLowerCamel});
        }

        Result result = putBatchRequest(URI+"/batch",${modelNameLowerCamel}List);

        Assert.assertEquals(result.getCode(), ResultCode.SUCCESS.code());
        for ( ${modelNameUpperCamel} ${modelNameLowerCamel} : ${modelNameLowerCamel}List) {
            ${modelNameUpperCamel} ${modelNameLowerCamel}Update = ${modelNameLowerCamel}Service.getById(${modelNameLowerCamel}.getId());
            Integer deleteFlag = 0;
            Assert.assertEquals(${modelNameLowerCamel}Update.getDeleteFlag(), deleteFlag);
        }
    }

        /**
         * 删除API 单元测试
         * @throws Exception
        */
        @Test
        public void testDelete() throws Exception {
            //新建数据
            Long id = addDataToDB();
            String deleteUrl = URI +"/"+ id;
            Result result = deleteRequest(deleteUrl);

            Assert.assertEquals(result.getCode(), ResultCode.SUCCESS.code());
            ${modelNameUpperCamel} ${modelNameLowerCamel}Db =  ${modelNameLowerCamel}Service.getById(id);
            Assert.assertNull(${modelNameLowerCamel}Db);
        }

        /**
        * 批量删除 API 单元测试
        * @throws Exception
        */
        @Test
        public void testDeleteBatch() throws Exception {

            List<Long> ids = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                //新建数据
                Long id = addDataToDB();
                ids.add(id);
            }

            Result result = deleteBatchRequest(URI,ids);

            Assert.assertEquals(result.getCode(), ResultCode.SUCCESS.code());
            for (Long id : ids) {
                ${modelNameUpperCamel} ${modelNameLowerCamel}Db =  ${modelNameLowerCamel}Service.getById(id);
                Assert.assertNull(${modelNameLowerCamel}Db);
            }
         }

    private DemoUser buildUpdateEntity() {
        Integer deleteFlag = 0;
        ${modelNameUpperCamel} ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
        ${modelNameLowerCamel}.setId(addDataToDB());
        ${modelNameLowerCamel}.setDeleteFlag(deleteFlag);
        return ${modelNameLowerCamel};
    }

    /**
     * 添加用户到数据库
     *
     * @return Long 用户ID
    */
    private Long addDataToDB() {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = buildEntity();
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        //根据生成的ID调用根据ID获取详情的接口
        return ${modelNameLowerCamel}.getId();
    }
    /**
     * 构建用户信息
     * @return
    */
    private  ${modelNameUpperCamel} buildEntity() {
        throw new BusinessException("请先添加模拟数据逻辑， 建议使用JMockData");
    }

    private void deleteAllData() {
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.find();
        List<Long> ids = list.stream().map( ${modelNameUpperCamel}::getId).collect(Collectors.toList());
        ${modelNameLowerCamel}Service.deleteByIds(ids);
    }
}