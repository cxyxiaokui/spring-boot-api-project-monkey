package com.company.project.demoUser.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.demoUser.domain.DemoUser;
import com.company.project.demoUser.service.DemoUserService;
import com.company.project.kkantireptile.cn.keking.anti_reptile.annotation.AntiReptile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.company.project.core.ControllerSupport;
import com.company.project.core.Page;
/**
 * @Author： zhuoqianmingyue
 * @Date： 2020/08/09.
 * @Description：
 **/
@RestController
@RequestMapping("/demo/user")
public class DemoUserController extends ControllerSupport<DemoUser>{

    @Autowired
    private DemoUserService demoUserService;

    @ApiOperation(value = "新增", notes = "新增")
    @ApiOperationSupport(author = "zhuoqianmingyue")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result add(@RequestBody @Validated DemoUser demoUser) {
        demoUserService.save(demoUser);
        return sucess();
    }

    @ApiOperation(value = "批量新增", notes = "批量新增")
    @PostMapping("/batch")
    public Result addList(@RequestBody List<DemoUser> demoUserList) {
        demoUserService.saveBatch(demoUserList);
        return sucess();
    }

    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id) {
        demoUserService.deleteById(id);
        return sucess();
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping
    public Result deleteBatch(@RequestBody() List<Long> ids) {
        demoUserService.deleteByIds(ids);
        return sucess();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping
    public Result update(@RequestBody DemoUser demoUser) {
        demoUserService.update(demoUser);
        return sucess();
    }

    @ApiOperation(value = "批量修改", notes = "批量修改")
    @PutMapping("/batch")
    public Result updateList(@RequestBody List<DemoUser> demoUserList) {
        demoUserService.updateBatch(demoUserList);
        return sucess();
    }

    @ApiOperation(value = "根据ID查询", notes = "根据ID查询")
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Long id){
        DemoUser demoUser =  demoUserService.getById(id);
        return sucess(demoUser);
    }

    @ApiOperation(value = "分页查询",notes = "分页查询",responseContainer = "List",response = PageInfo.class)
    @GetMapping
    @AntiReptile
    public Result queryist(DemoUser demoUser, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<DemoUser> demoUserList = demoUserService.find(demoUser);
        return pageSucess(demoUserList);
    }
}