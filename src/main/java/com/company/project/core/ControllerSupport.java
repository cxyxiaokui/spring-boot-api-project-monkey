package com.company.project.core;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Controler 扩展功能封装
 *
 * @Author zhuoqianmingyue
 * @Date 2020/4/5 11:39 上午
 * @Description：对 Contoller 经常使用的逻辑进行抽取
 **/
public class ControllerSupport<T> {

    public Result<T> sucess(){
        return ResultGenerator.genSuccessResult();
    }

    public Result<PageInfo> pageSucess(List<T> list) {
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    public Result<T> sucess(T object){
        return  ResultGenerator.genSuccessResult(object);
    }
}
