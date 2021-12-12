package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Pedidos;
import com.example.demo.model.Productos;

@Service
public class PedidosService {

	private List<Pedidos> listaPedidos = new ArrayList<Pedidos>();
	private ProductoService sevicioProductos;
	Map<Productos, Integer> productosStatic = new HashMap<Productos, Integer>();
	
	public boolean addPedido(Pedidos e) {
		return listaPedidos.add(e);
	}
	
	@PostConstruct
	public void init() {
		listaPedidos.addAll(Arrays.asList(new Pedidos("C/noseque 1ªB","29126617"),
								new Pedidos("C/noseque2 1ªB","39126617"),
								new Pedidos("C/noseque3 1ªB","49126617"),
								new Pedidos("C/noseque4 1ªB","59126617"),
								new Pedidos("C/noseque5 1ªB","69126617")));
		
	}

	public List<Pedidos> getListaProductos() {
		return listaPedidos;
	}
	
	public boolean modifiProducto(Productos e) {
		
		
		return false;
	}
	
	public boolean meterPedidos(Integer[] cantidades) {
		int contador=0;
		for (Productos producto : sevicioProductos.getListaProductos()) {
			productosStatic.put(producto, cantidades[contador]);
			contador++;
		}
		return true;
	}


	public void setListaProductos(List<Pedidos> listaProductos) {
		this.listaPedidos = listaProductos;
	}
	
	
}
