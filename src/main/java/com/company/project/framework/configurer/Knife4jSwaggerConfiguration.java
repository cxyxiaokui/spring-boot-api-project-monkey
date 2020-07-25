package com.company.project.framework.configurer;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 在线文档工具 knife4j 的Swagger UI 配置。
 * @Author：zhuoqianmingyue
 * @Date： 2020/07/11
 * @Description：
**/
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Knife4jSwaggerConfiguration {

    @Bean
    public Docket systemDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(systemApiInfo())
                .groupName(Knife4jSwaggerConstants.SYSTEM_GROUP_NAME)
                .select()
                .apis(RequestHandlerSelectors.basePackage(Knife4jSwaggerConstants.SYSTEM_GROUP_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo systemApiInfo() {
        Contact contact = new Contact(Knife4jSwaggerConstants.SYSTEM_API_INFO_CONTACT_NAME, Knife4jSwaggerConstants.SYSTEM_API_INFO_CONTACT_URL,Knife4jSwaggerConstants.SYSTEM_API_INFO_CONTACT_EMAIL);

        return new ApiInfoBuilder()
                .title(Knife4jSwaggerConstants.SYSTEM_API_INFO_TITLE)
                .description(Knife4jSwaggerConstants.SYSTEM_API_INFO_DESCRIPTION)
                .termsOfServiceUrl(Knife4jSwaggerConstants.TERMS_OF_SERVICE_URL)
                .contact(contact)
                .version(Knife4jSwaggerConstants.SYSTEM_API_INFO_VERSION)
                .build();

    }
}
