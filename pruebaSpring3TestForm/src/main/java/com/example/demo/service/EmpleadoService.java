package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Empleado;

@Service
public class EmpleadoService {

	private List<Empleado> repositorio = new ArrayList<>();
	
	public Empleado add(Empleado e) {
		repositorio.add(e);
		return e;
	}
	
	public List<Empleado> findAll(){
		return repositorio;
	}
	
	@PostConstruct
	public void init() {
		repositorio.addAll(
				Arrays.asList(new Empleado(1,"Jose Pérez","jose@dominio.es","651442121"),
				new Empleado(2,"Maria Pérez","maria@dominio.es","651212121"),
				new Empleado(3,"Miguel Pérez","miguel@dominio.es","651333333")));
	}
}
