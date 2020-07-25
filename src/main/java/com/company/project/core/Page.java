package com.company.project.core;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 分页查询信息封装
 *
 * @Author：zhuoqianmingyue
 * @Date： 2020/6/21
 * @Description：用于 Controler 分页参数传递
**/
public  class Page implements Serializable {

    @ApiModelProperty(value = "第几页")
    @Min(value = 1, message = "当前页数不能小于1")
    private Integer pageNum;

    @ApiModelProperty(value = "每页显示条数")
    @Min(value = 1, message = "每页条数不能小于1")
    private Integer pageSize;

    public Integer getPageNum() {
        if (this.pageNum == null) {
            this.pageNum = 1;
        }
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        if (this.pageSize == null) {
            this.pageSize = 10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
