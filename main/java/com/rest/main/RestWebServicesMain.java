package com.rest.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.rest.main.models"}) 
@ComponentScan
public class RestWebServicesMain {
	
	public static void main(String[] args) {
		SpringApplication.run(RestWebServicesMain.class, args);
	}
}