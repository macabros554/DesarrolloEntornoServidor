package com.example.demo;

import org.springframework.context.annotation.*;

@Configuration
public class AppConfig {

		@Bean
		public Holamundo holamundo() {
			return new Holamundo();
		}
}
