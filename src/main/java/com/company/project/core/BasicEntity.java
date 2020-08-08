package com.company.project.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * 基础 Entity 信息
 *
 * @Author： zhuoqianmingyue
 * @Date： 2020/7/25 12:48 下午
 * @Description：代码参考来源 RuoYi：https://github.com/lerry903/RuoYi
 **/
public class BasicEntity {
    /** 创建者 */
    @ApiModelProperty(hidden=true)
    @JsonIgnore
    private Long createBy;

    /** 创建时间 */
    @ApiModelProperty(hidden=true)
    @JsonIgnore
    private Date createTime;

    /** 更新者 */
    @ApiModelProperty(hidden=true)
    @JsonIgnore
    private Long updateBy;

    /** 更新时间 */
    @ApiModelProperty(hidden=true)
    @JsonIgnore
    private Date updateTime;
    @ApiModelProperty(hidden=true)
    private Integer deleteFlag;

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
