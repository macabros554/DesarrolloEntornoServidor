package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String welcome(Model model) {
		model.addAttribute("mensaje","¡Hola Nuevo mundo!");
		return "index";
	}
	
	@GetMapping("/que")
	public String queHace(Model model) {
		model.addAttribute("mensaje","¡Hola Nuevo mundo!");
		return "que";
	}
	
	@GetMapping("/contacto")
	public String contacto(Model model) {
		model.addAttribute("mensaje","¡Hola Nuevo mundo!");
		return "contacto";
	}
	
}
