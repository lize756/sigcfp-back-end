package com.edu.icesi.sigcfp.sigcfpbackendbusiness.rest;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
@ComponentScan(basePackages = { "com.edu.icesi.sigcfp.sigcfpbackendbusiness" })
public class RestApplication {

	/**
	 * Permite cambiar la zona horaria de spring boot para que la fecha en la base
	 * de datos se guarde en la hora correcta
	 */
	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // It will set UTC timezone
		System.out.println("Spring boot application running in UTC timezone :" + new Date()); // It will print UTC
																								// timezone
	}

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

}
