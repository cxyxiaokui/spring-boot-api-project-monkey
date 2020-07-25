package com.company.project.framework.configurer;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @Author：lihengming
 * @Date：2017/6/23
 * @Description：
 *
 * Spring MVC 配置 不建议继承 WebMvcConfigurerAdapter 而是基于Java8的默认方法实现 WebMvcConfigurer
 *
 * @Editor：zhuoqianmingyue
 * @ModifiedDate：2020/6/21
 * @Description：去掉统一异常处理，并将继承WebMvcConfigurerAdapter改为实现 WebMvcConfigurer 添加 在线问题工具 knife4j 静态资源拦截配置
**/
@Configuration
public class WebMvcConfigurerConfig implements WebMvcConfigurer {

    /**使用阿里 FastJson 作为JSON MessageConverter*/
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        //保留空的字段
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8,MediaType.APPLICATION_FORM_URLENCODED));
        converters.add(converter);
    }

    /**解决跨域问题*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        swaggerUiResource(registry);
    }

    private void swaggerUiResource(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/service-worker.js")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }


}
