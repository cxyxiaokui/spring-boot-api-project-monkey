package com.company.project.common.constants;

/**
 * 系统日志彩色日志参数数据字典类
 *
 * @Author： zhuoqianmingyue
 * @Date： 2020/6/25 10:46 上午
 * @Description：
 **/
public class SystemLogColorConstant {
    private SystemLogColorConstant(){throw new IllegalStateException("Utility class");}
    /**黄颜色*/
    public static final String YELLOW = "\033[33;3m";
    /**绿颜色*/
    public static final String BULLUE = "\033[32;2m";
    /**颜色结束标志*/
    public static final String END = "\033[0m";
}
