package com.edu.icesi.sigcfp.sigcfpbackendbusiness.persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.edu.icesi.sigcfp.sigcfpbackendbusiness"})
public class PersistenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersistenceApplication.class, args);
    }

}
