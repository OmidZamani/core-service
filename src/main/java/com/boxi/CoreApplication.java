package com.boxi;

import com.boxi.utils.SSLFix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableEurekaClient
@EnableAutoConfiguration

public class CoreApplication {

    public static void main(String[] args) {
        SSLFix.execute();
        SpringApplication.run(CoreApplication.class, args);
    }

}
