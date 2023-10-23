package com.boxi.core.conf;

import com.boxi.core.permission.CustomPermissionEvaluator;
import com.boxi.utils.FindOS;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
@Configuration
public class MultiPartConf {

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String path="c:/tmp-dir";
        if(FindOS.isLinux()) path="/tmp";
        factory.setLocation(path);

        return factory.createMultipartConfig();
    }

    @Bean("customPermissionEvaluator")
    public CustomPermissionEvaluator customPermissionEvaluator() {
        return new CustomPermissionEvaluator();
    }

}
