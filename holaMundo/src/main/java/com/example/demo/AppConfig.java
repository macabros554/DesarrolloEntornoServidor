package com.example.demo;

import org.springframework.context.annotation.*;

@Configuration
public class AppConfig {

	/*@Bean
		public Holamundo holamundo() {
			return new Holamundo();
	}*/
	
	@Bean
	public DependentService dependentService() {
		DependentService ds = new DependentService();
		ds.setService1(service1());
		ds.setService2(service2());
		return ds;
	}
	
	@Bean
	public Service1 service1() {
		return new Service1();
	}
	
	@Bean
	public Service2 service2() {
		return new Service2();
	}
	
}
