package com.sunny.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {org.activiti.spring.boot.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
public class SunnyActivitiApplication {

    public static void main(String[] args) {
       // System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SunnyActivitiApplication.class, args);
    }

}
