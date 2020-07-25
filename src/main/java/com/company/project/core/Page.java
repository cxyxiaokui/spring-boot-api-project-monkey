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
        return isNullSetDeault(pageNum, 1);
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return isNullSetDeault(pageSize, 10);
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 如何分页信息为null则设置分页信息的默认值
     * @param num
     * @param i
     * @return
     */
    private Integer isNullSetDeault(Integer num, int i) {
        if (num == null) {
            num = i;
        }
        return num;
    }
}
