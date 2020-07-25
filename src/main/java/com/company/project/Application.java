package com.company.project;

import com.company.project.core.SystemLogColorConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author：zhuoqianmingyue
 * @Date： 2020/07/11
 * @Description：自定义符号日志输出代码来源 RuoYi：https://github.com/lerry903/RuoYi（在其基础上为其添加颜色输出展示）
 **/
@SpringBootApplication
public class Application {
    private static Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info(SystemLogColorConstant.YELLOW +"(♥◠ ‿ ◠) ﾉﾞ"+ SystemLogColorConstant.END+"：MonKey 启动成功 没有发现异常"+" "+ SystemLogColorConstant.YELLOW+   "ლ ( ´ڡ` ლ) ﾞ"+ SystemLogColorConstant.END+" "+ SystemLogColorConstant.BULLUE +  " OH YEAH ！"+ SystemLogColorConstant.END );
    }
}

