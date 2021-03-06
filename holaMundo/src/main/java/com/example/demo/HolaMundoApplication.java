package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HolaMundoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(HolaMundoApplication.class, args);
		
		ApplicationContext ctx = SpringApplication.run(HolaMundoApplication.class, args);
		((DependentService) ctx.getBean("dependentService")).doSmth();
	}

}
