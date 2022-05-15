package com.edu.icesi.sigcfp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SigcfpBackEndBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SigcfpBackEndBusinessApplication.class, args);
    }

}
