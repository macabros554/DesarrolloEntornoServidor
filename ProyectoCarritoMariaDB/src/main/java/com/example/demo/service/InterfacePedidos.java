package com.example.demo.service;

import java.util.List;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedidos;
import com.example.demo.model.Productos;

public interface InterfacePedidos {

	public boolean nuevoPedido(Pedidos base,Pedidos nuevo);
	public List<LineaPedido> ultimoPedido();
	
	
}
