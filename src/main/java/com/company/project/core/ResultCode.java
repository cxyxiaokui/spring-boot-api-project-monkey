package com.company.project.core;

/**
 * 响应码枚举，参考HTTP状态码的语义
 *
 * @Author：lihengming
 * @Date：2017/6/23
 * @Description：
 *
 * @Editor：zhuoqianmingyue
 * @ModifiedDate： 2020/6/21
 * @Description：添加对应的注释信息
 */
public enum ResultCode {

    /**成功*/
    SUCCESS(200),
    /**失败*/
    FAIL(400),
    /**未认证（签名错误）*/
    UNAUTHORIZED(401),
    /**接口不存在*/
    NOT_FOUND(404),
    /**服务器内部错误*/
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
