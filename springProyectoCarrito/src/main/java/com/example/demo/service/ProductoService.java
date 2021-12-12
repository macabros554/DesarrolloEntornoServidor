package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Productos;

@Service
public class ProductoService {

	private List<Productos> listaProductos = new ArrayList<Productos>();
	
	@PostConstruct
	public void init() {
		listaProductos.addAll(Arrays.asList(new Productos("Leche",1.20),new Productos("Cafe",1.80),
				new Productos("Manzana",0.32),new Productos("Lechuga",0.80),new Productos("Avena",2.10)));
	}
	
	public ProductoService(List<Productos> listaProductos) {
		super();
		this.listaProductos = listaProductos;
	}
	
	public List<Productos> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Productos> listaProductos) {
		this.listaProductos = listaProductos;
	}
	


	
}
