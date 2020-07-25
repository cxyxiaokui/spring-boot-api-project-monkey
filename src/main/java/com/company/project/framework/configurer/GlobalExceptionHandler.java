package com.company.project.framework.configurer;

import com.company.project.core.BusinessException;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhuoqianmingyue
 * @Date 2020/6/21
 * @Description：统一异常处理 Handle
 **/

@RestControllerAdvice()
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    /**
     * 系统异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public <T> Result<T> exception(Exception e, HttpServletRequest request ) {
        log.error(e.getMessage(), e);

        return ResultGenerator.genFailResult("访问：" + request.getRequestURI() + " 出错:"+e.getMessage()+"，请联系管理员");
    }

    /**
     * 接口不存在异常 (NoHandlerFoundException)
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoHandlerFoundException.class)
    public <T> Result<T> noHandlerFoundException(NoHandlerFoundException exception, HttpServletRequest request , HttpServletResponse respons) {
        log.error(exception.getMessage(), exception);

        return  ResultGenerator.genFailResult("接口：" + request.getRequestURI() + " 没有找到！");
    }


    /**
     * 业务异常(BusinessException)
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public <T> Result<T> businessException(BusinessException exception) {
        log.error(exception.getMessage(), exception);

        return  ResultGenerator.genFailResult(exception.getMessage());
    }
    /**
     * 自定义验证异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public <T> Result<T> validatedBindException(BindException exception)
    {
        log.error(exception.getMessage(), exception);
        String message = exception.getAllErrors().get(0).getDefaultMessage();
        return ResultGenerator.genFailResult(message);
    }
}
