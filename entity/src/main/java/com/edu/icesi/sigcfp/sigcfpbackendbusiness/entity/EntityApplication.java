package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.edu.icesi.sigcfp.sigcfpbackendbusiness"})
public class EntityApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntityApplication.class, args);
    }

}
