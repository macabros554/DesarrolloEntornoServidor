package com.example.demo.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.StudentService;

@Controller
public class MainController {
	
	@Autowired
	private StudentService servicio;

	@GetMapping("/attibute_example")
	public String simpleAttributeExample(Model model) {
		SimpleDateFormat dateFormat =  new SimpleDateFormat("dd-MM-yyyy");
		model.addAttribute("serverTime", dateFormat.format(new Date()));
		return "attributeExample";
	}
	
	@GetMapping("/list")
	public String studentList(Model model) {
		model.addAttribute("listaEstudiantes",servicio.findAll());
		return "list";
	}
	
}
