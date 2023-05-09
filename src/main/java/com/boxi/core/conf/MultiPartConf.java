package com.boxi.core.conf;

import com.boxi.core.permission.CustomPermissionEvaluator;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
@Configuration
public class MultiPartConf {

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("/app/tmp");
        return factory.createMultipartConfig();
    }

    @Bean("customPermissionEvaluator")
    public CustomPermissionEvaluator customPermissionEvaluator() {
        return new CustomPermissionEvaluator();
    }

}
