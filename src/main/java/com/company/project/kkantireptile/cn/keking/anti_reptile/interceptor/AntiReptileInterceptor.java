package com.company.project.kkantireptile.cn.keking.anti_reptile.interceptor;

import com.company.project.common.utils.JsonConvertUtil;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.kkantireptile.cn.keking.anti_reptile.annotation.AntiReptile;
import com.company.project.kkantireptile.cn.keking.anti_reptile.config.AntiReptileProperties;
import com.company.project.kkantireptile.cn.keking.anti_reptile.module.VerifyImageDTO;
import com.company.project.kkantireptile.cn.keking.anti_reptile.rule.RuleActuator;
import com.company.project.kkantireptile.cn.keking.anti_reptile.util.CrosUtil;
import com.company.project.kkantireptile.cn.keking.anti_reptile.util.VerifyImageUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/**
 * @author chenjh
 * @since 2020/2/4 17:45
 *
 * @Editor：zhuoqianmingyue
 * @ModifiedDate： 2020/8/11
 * @Description：添加触发规则后JSON格式提醒
 */
public class AntiReptileInterceptor extends HandlerInterceptorAdapter {


    private String antiReptileForm;

    private RuleActuator actuator;

    private List<String> includeUrls;

    private boolean globalFilterMode;

    private VerifyImageUtil verifyImageUtil;

    private AtomicBoolean initialized = new AtomicBoolean(false);

    public void init(ServletContext context) {
        ClassPathResource classPathResource = new ClassPathResource("verify/index.html");
        try {
            classPathResource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
            this.antiReptileForm = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("反爬虫验证模板加载失败！");
            e.printStackTrace();
        }
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        assert ctx != null;
        this.actuator = ctx.getBean(RuleActuator.class);
        this.verifyImageUtil = ctx.getBean(VerifyImageUtil.class);
        this.includeUrls = ctx.getBean(AntiReptileProperties.class).getIncludeUrls();
        this.globalFilterMode = ctx.getBean(AntiReptileProperties.class).isGlobalFilterMode();
        if (this.includeUrls == null) {
            this.includeUrls = new ArrayList<>();
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        String interceptionType = ctx.getBean(AntiReptileProperties.class).getInterceptionType();
        if (!initialized.get()) {
            if("HTML".equals(interceptionType)){
                init(request.getServletContext());
            }
            initialized.set(true);
        }
        HandlerMethod handlerMethod;
        try {
            handlerMethod = (HandlerMethod) handler;
        } catch (ClassCastException e) {
            return true;
        }
        Method method = handlerMethod.getMethod();
        AntiReptile antiReptile = AnnotationUtils.findAnnotation(method, AntiReptile.class);
        boolean isAntiReptileAnnotation = antiReptile != null;
        String requestUrl = request.getRequestURI();
        if (isIntercept(requestUrl, isAntiReptileAnnotation) && !actuator.isAllowed(request, response)) {
            CrosUtil.setCrosHeader(response);


            String data = "";
            response.setStatus(ResultCode.BANDWIDTH_LIMIT_EXCEEDED.code());
            if("JSON".equals(interceptionType)){

                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                Result result = new Result();
                result.setCode(ResultCode.BANDWIDTH_LIMIT_EXCEEDED);
                result.setMessage("您访问的太频繁或者改设备禁止访问！");
               JsonConvertUtil.toJson(result);
            }
            if("HTML".equals(interceptionType)){
                response.setContentType("text/html;charset=utf-8");
                VerifyImageDTO verifyImage = verifyImageUtil.generateVerifyImg();
                verifyImageUtil.saveVerifyCodeToRedis(verifyImage);
                String str1 = this.antiReptileForm.replace("verifyId_value", verifyImage.getVerifyId());
                String str2 = str1.replaceAll("verifyImg_value", verifyImage.getVerifyImgStr());
                data = str2.replaceAll("realRequestUri_value", requestUrl);
            }

            response.getWriter().write(data);
            response.getWriter().close();
            return false;
        }
        return true;
    }

    /**
     * 是否拦截
     * @param requestUrl 请求uri
     * @param isAntiReptileAnnotation 是否有AntiReptile注解
     * @return 是否拦截
     */
    public boolean isIntercept(String requestUrl, Boolean isAntiReptileAnnotation) {
        if (this.globalFilterMode || isAntiReptileAnnotation || this.includeUrls.contains(requestUrl)) {
            return true;
        } else {
            for (String includeUrl : includeUrls) {
                if (Pattern.matches(includeUrl, requestUrl)) {
                    return true;
                }
            }
            return false;
        }
    }
}
