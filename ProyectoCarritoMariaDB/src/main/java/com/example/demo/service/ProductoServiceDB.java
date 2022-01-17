package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedidos;
import com.example.demo.model.Productos;
import com.example.demo.repository.PedidosRepository;
import com.example.demo.repository.ProductosRepository;

@Service("productoServiceDB")
public class ProductoServiceDB implements InterfaceProductos{
	
	@Autowired
	private ProductosRepository repoProductos;
	
	@Autowired
	private PedidosRepository repoPedido;
	
	private Pedidos ultimoPedido;
	
	@Override
	public Pedidos addProducto(Integer[] cantidades) {
		int comprobar=0;
		List<Productos> listaProductos= new ArrayList<Productos>();
		LineaPedido lineaPedido;

		Pedidos pedido = new Pedidos();
		List<LineaPedido> listaLineaPedidos = new ArrayList<>();
		listaProductos=repoProductos.findAll();
		
		for (int i = 0; i < cantidades.length; i++) {
			lineaPedido=new LineaPedido(cantidades[i], listaProductos.get(i), pedido);
			listaLineaPedidos=pedido.getListaLineaPedidos();
			listaLineaPedidos.add(lineaPedido);
			pedido.setListaLineaPedidos(listaLineaPedidos);
			
			comprobar+=cantidades[i];
		}
		
		if (comprobar>=1) {
			this.ultimoPedido=pedido;

			return pedido;
		}else {
			return null;
		}
	}

	@Override
	public List<Productos> listaProductos() {
		return repoProductos.findAll();
	}

	@Override
	public Double suma() {
		Double resultado=0.0;
		for (LineaPedido a : ultimoPedido.getListaLineaPedidos()) {
			resultado+= a.getCantidad()*a.getProducto().getPrecio();
		}
		
		return resultado;
	}

	public Pedidos getUltimoPedido() {
		return ultimoPedido;
	}

	public void setUltimoPedido(Pedidos ultimoPedido) {
		this.ultimoPedido = ultimoPedido;
		repoPedido.save(ultimoPedido);
	}

	
	
}
