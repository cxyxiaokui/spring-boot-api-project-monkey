package com.company.project.user.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author： zhuoqianmingyue
 * @Date： 2020/6/21 7:21 下午
 * @Description：
 **/
@ApiModel(value="用户")
public class DemoUserVO {

    @ApiModelProperty(value="id",name="id",example="1")
    private Long id;
    @ApiModelProperty(value="用户名称",name="username",example="zhangsan")
    @NotBlank(message = "用户名称不能为空")
    private String username;
    @ApiModelProperty(value="昵称",name="nickName",example="zhangsan")
    private String nickName;
    @ApiModelProperty(value="性别",name="sex",example="1")
    private Integer sex;
    @ApiModelProperty(value="注册日期",name="registerDate",example="1")
    private Date registerDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", registerDate=" + registerDate +
                '}';
    }
}
