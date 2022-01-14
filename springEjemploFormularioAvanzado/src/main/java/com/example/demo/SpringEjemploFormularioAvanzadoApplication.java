package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.service.EmpleadoServiceMemory;

@SpringBootApplication
public class SpringEjemploFormularioAvanzadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEjemploFormularioAvanzadoApplication.class, args);
	}
	
	/*@Bean
	ComandLineRunner initData(EmpleadoRepository repositorio) {
		return (args) -> {
			
		}

	}*/

}
