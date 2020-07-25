package com.company.project.user.domain;

import com.company.project.core.BasicEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author： jkli
 * @Date： 2020/7/25 12:26 下午
 * @Description：
 **/
public class DemoUser extends BasicEntity {

    @ApiModelProperty(value="id",name="id",example="1")
    private Long id;
    @ApiModelProperty(value="用户名称",name="username",example="zhangsan")
    @NotBlank(message = "用户名称不能为空")
    private String username;
    @ApiModelProperty(value="密码",name="password",example="123456")
    private String password;
    @ApiModelProperty(value="昵称",name="nickName",example="zhangsan")
    private String nickName;
    @ApiModelProperty(value="性别",name="sex",example="1")
    private Integer sex;
    @ApiModelProperty(value="注册日期",name="registerDate",example="2020-01-01")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
