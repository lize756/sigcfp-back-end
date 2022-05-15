package com.edu.icesi.sigcfp.sigcfpbackendbusiness.delegate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.edu.icesi.sigcfp.sigcfpbackendbusiness"})

public class DelegateApplication {

    public static void main(String[] args) {
        SpringApplication.run(DelegateApplication.class, args);
    }

}
