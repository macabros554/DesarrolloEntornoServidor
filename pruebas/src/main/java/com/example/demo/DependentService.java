package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DependentService {

	@Autowired
	private Service1 service1;
	@Autowired
	private Service2 service2;
	
	//public DependentService() {}
	/*@Autowired
	public DependentService(Service1 service1,Service2 service2) {
		this.service1=service1;
		this.service2=service2;
	}*/
	
	public void doSmth() {
		service1.doSmth();
		service2.doSmth();
	}
	
	/*@Autowired
	public void setService1(Service1 service1) {
		this.service1 = service1;
	}
	
	@Autowired
	public void setService2(Service2 service2) {
		this.service2 = service2;
	}*/
	
	
	
}
