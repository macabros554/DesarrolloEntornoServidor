package com.example.demo;

public class DependentService {

	private Service1 service1;
	private Service2 service2;
	
	public DependentService() {

	}
	
	public void doSmth() {
		service1.doSmth();
		service2.doSmth();
	}

	public void setService1(Service1 service1) {
		this.service1 = service1;
	}

	public void setService2(Service2 service2) {
		this.service2 = service2;
	}
	
	
	
}
