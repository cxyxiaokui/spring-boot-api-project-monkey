package com.company.project.demoUser.domain;
import com.company.project.core.BasicEntity;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * @Author： zhuoqianmingyue
 * @Date： 2020/08/09.
 * @Description：
 **/
public class DemoUser extends BasicEntity{


    @ApiModelProperty(value="",name="id",example="1")
    private Long id;

    @ApiModelProperty(value="",name="username",example="string")
    private String username;

    @ApiModelProperty(value="",name="password",example="string")
    private String password;

    @ApiModelProperty(value="",name="nickName",example="string")
    private String nickName;

    @ApiModelProperty(value="",name="email",example="string")
    private String email;

    @ApiModelProperty(value="",name="sex",example="1")
    private Integer sex;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="",name="registerDate",example="2020-01-01")
    private Date registerDate;


    public Long getId(){
        return id;
    }
    public void setId(Long  id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String  username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String  password){
        this.password = password;
    }

    public String getNickName(){
        return nickName;
    }
    public void setNickName(String  nickName){
        this.nickName = nickName;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String  email){
        this.email = email;
    }

    public Integer getSex(){
        return sex;
    }
    public void setSex(Integer  sex){
        this.sex = sex;
    }

    public Date getRegisterDate(){
        return registerDate;
    }
    public void setRegisterDate(Date  registerDate){
        this.registerDate = registerDate;
    }
}