package com.company.project.demoUser.web;

import com.company.project.Tester;
import com.company.project.core.*;
import com.company.project.demoUser.domain.DemoUser;
import com.company.project.demoUser.service.DemoUserService;
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
 * @Author： zhuoqianmingyue
 * @Date： 2020/08/09.
 * @Description：
 **/
public class DemoUserControllerTest extends Tester{

    @Autowired
    private DemoUserService demoUserService;

    private static String URI = "/demo/user";

    /**
        * 新增 API 单元测试
        * @throws Exception
    */
    @Test
    public void testAdd() throws Exception {
        //清空数据
        deleteAllData();
        //调用创建数据API
        DemoUser demoUser = buildEntity();
        Result result = postRequest(URI, demoUser);
        //是否与预期相同断言
        List< DemoUser> demoUserList = demoUserService.find();
        Assert.assertEquals(result.getCode(), ResultCode.SUCCESS.code());
        Assert.assertNotNull(demoUserList);
        Assert.assertEquals(demoUserList.size(), 1);
    }



    /**
    * 根据ID查询 API 单元测试
    * @throws Exception
    */
    @Test
    public void testGetById() throws Exception {
        Long id = addDataToDB();

        Result result = getRequest(URI+"/"+id);

        DemoUser demoUser = (DemoUser)getDate(result,DemoUser.class);

        Assert.assertEquals(result.getCode(), ResultCode.SUCCESS.code());
        Assert.assertEquals(demoUser.getId(), id);
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
        DemoUser demoUser = buildUpdateEntity();

        Result result = putRequest(URI,demoUser);

        DemoUser demoUserUpdate = demoUserService.getById(demoUser.getId());
        Integer deleteFlag = 0;
        Assert.assertEquals(result.getCode(), ResultCode.SUCCESS.code());
        Assert.assertEquals(demoUserUpdate.getDeleteFlag(), deleteFlag);
    }
    /**
     * 批量修改 API 单元测试
     * @throws Exception
    */
    @Test
    public void testUpdateBatch() throws Exception {

        deleteAllData();

        List<DemoUser> demoUserList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DemoUser demoUser = buildUpdateEntity();
            demoUserList.add(demoUser);
        }

        Result result = putBatchRequest(URI+"/batch",demoUserList);

        Assert.assertEquals(result.getCode(), ResultCode.SUCCESS.code());
        for ( DemoUser demoUser : demoUserList) {
            DemoUser demoUserUpdate = demoUserService.getById(demoUser.getId());
            Integer deleteFlag = 0;
            Assert.assertEquals(demoUserUpdate.getDeleteFlag(), deleteFlag);
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
            DemoUser demoUserDb =  demoUserService.getById(id);
            Assert.assertNull(demoUserDb);
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
                DemoUser demoUserDb =  demoUserService.getById(id);
                Assert.assertNull(demoUserDb);
            }
         }

    private DemoUser buildUpdateEntity() {
        Integer deleteFlag = 0;
        DemoUser demoUser = new DemoUser();
        demoUser.setId(addDataToDB());
        demoUser.setDeleteFlag(deleteFlag);
        return demoUser;
    }

    /**
     * 添加用户到数据库
     *
     * @return Long 用户ID
    */
    private Long addDataToDB() {
        DemoUser demoUser = buildEntity();
        demoUserService.save(demoUser);
        //根据生成的ID调用根据ID获取详情的接口
        return demoUser.getId();
    }
    /**
     * 构建用户信息
     * @return
    */
    private  DemoUser buildEntity() {
        MockConfig mockConfig = new MockConfig()
                //随机段落字符串
                .stringRegex("I'am a nice man\\.And I'll just scribble the characters, like：[a-z]{2}-[0-9]{2}-[abc123]{2}-\\w{2}-\\d{2}@\\s{1}-\\S{1}\\.?-.")
                // 邮箱
                .subConfig(DemoUser.class,"email")
                .stringRegex("[a-z0-9]{5,15}\\@\\w{3,5}\\.[a-z]{2,3}")
                // 邮箱
                .subConfig(DemoUser.class,"username","password")
                .stringRegex("[a-zA-Z_]{1}[a-z0-9_]{5,15}")

                .subConfig(DemoUser.class,"nickName")
                .stringSeed("zhuoqianmingyue","xiaoliu","xiaoli")

                .subConfig(DemoUser.class,"sex","registerDate")
                .intRange(0,1)
                .dateRange("2018-11-20", "2018-11-30")
                .globalConfig()
                // 排除所有Array开头/Boxing结尾的字段。表达式不区分大小写。
                .excludes(DemoUser.class,"id","createBy","updateBy","createTime","updateTime");
        DemoUser demoUser = JMockData.mock(DemoUser.class, mockConfig);
        return demoUser;
    }

    private void deleteAllData() {
        List<DemoUser> list = demoUserService.find();
        List<Long> ids = list.stream().map( DemoUser::getId).collect(Collectors.toList());
        demoUserService.deleteByIds(ids);
    }
}