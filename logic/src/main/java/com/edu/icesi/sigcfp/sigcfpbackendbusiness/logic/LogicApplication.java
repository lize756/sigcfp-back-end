package com.edu.icesi.sigcfp.sigcfpbackendbusiness.logic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.edu.icesi.sigcfp.sigcfpbackendbusiness"})
public class LogicApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogicApplication.class, args);
    }

}
