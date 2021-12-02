package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Student;

@Service
public class StudentService {

	public List<Student> repositorio = new ArrayList<>();
	
	public List<Student> findAll(){
		return repositorio;
	}
	
	@PostConstruct
	public void init() {
		repositorio.addAll(
				Arrays.asList(new Student(1,"Luis Garcia"),
						new Student(2,"Manolo Pérez"),
						new Student(3,"Jefecita Gonzalez"))
				);
	}
}
