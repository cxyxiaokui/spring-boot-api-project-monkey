package com.company.project.user.web;

import cn.keking.anti_reptile.annotation.AntiReptile;
import com.company.project.core.ControllerSupport;
import com.company.project.core.Page;
import com.company.project.core.Result;
import com.company.project.user.domain.DemoUser;
import com.company.project.user.domain.DemoUserVO;
import com.company.project.user.service.DemoUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Author： zhuoqianmingyue
 * @Date： 2020/6/21 7:24 下午
 * @Description：
 **/
@Api(value = "用户DEMO",tags = "用户DEMO")
@ApiSupport(author = "ljk126wy@126.com")
@RestController
@RequestMapping("/user")
public class DemoUserController extends ControllerSupport<DemoUser> {

    @Autowired
    private DemoUserService userService;

    @ApiOperation(value = "根据ID查询", notes = "根据ID查询")
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Long id){
        DemoUser demoUser = userService.getById(id);
        return sucess(demoUser);
    }

    @AntiReptile
    @ApiOperation(value = "分页查询",notes = "分页查询",responseContainer = "List",response = PageInfo.class)
    @GetMapping
    public Result queryist(DemoUser user, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<DemoUser> demoUserList = userService.find(user);
        return pageSucess(demoUserList);
    }

    @ApiOperation(value = "新增", notes = "新增")
    @ApiOperationSupport(author = "zhuoqianmingyue")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result add(@RequestBody @Validated DemoUser user) {
        userService.save(user);
        return sucess();
    }

    @ApiOperation(value = "批量新增", notes = "批量新增")
    @PostMapping("/batch")
    public Result addList(@RequestBody List<DemoUser> demoUserList) {
        userService.saveBatch(demoUserList);
        return sucess();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping
    public Result update(@RequestBody DemoUser demoUser) {
        userService.update(demoUser);
        return sucess();
    }

    @ApiOperation(value = "批量修改", notes = "批量修改")
    @PutMapping("/batch")
    public Result updateList(@RequestBody List<DemoUser> demoUserList) {
        userService.updateBatch(demoUserList);
        return sucess();
    }

    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id) {
        userService.deleteById(id);
        return sucess();
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping
    public Result deleteBatch(@RequestBody List<Long> ids) {
        userService.deleteByIds(ids);
        return sucess();
    }
}
