package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Productos;
import com.example.demo.repository.ProductosRepository;

@Service("productoServiceDB")
public class ProductoServiceDB{
	
	@Autowired
	private ProductosRepository repoProductos;
	
	
	public List<Productos> listaProductos() {
		return repoProductos.findAll();
	}
	
	public Productos buscarProducto(Long id) {
		return repoProductos.findById(id).orElse(null);
	}
}
