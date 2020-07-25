package com.company.project.core;

/**
 * 服务（业务）异常如“ 账号或密码错误 ”
 *
 * @Author：lihengming
 * @Date：2017/6/23
 * @Description：
 */
public class BusinessException extends RuntimeException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
