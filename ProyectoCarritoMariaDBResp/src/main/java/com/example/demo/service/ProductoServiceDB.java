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
	
	/**
	 * devuelve la lista de productos
	 * @return lista de productos
	 */
	
	public List<Productos> listaProductos() {
		return repoProductos.findAll();
	}
	
	/**
	 * busca el producto
	 * @param id
	 * @return producto
	 */
	
	public Productos buscarProducto(Long id) {
		return repoProductos.findById(id).orElse(null);
	}
}
