package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedidos;
import com.example.demo.model.Productos;
import com.example.demo.repository.LineaPedidoRepository;
import com.example.demo.repository.PedidosRepository;
import com.example.demo.repository.ProductosRepository;

@Service("productoServiceDB")
public class ProductoServiceDB implements InterfaceProductos{
	
	@Autowired
	private ProductosRepository repoProductos;
	
	@Autowired
	private PedidosRepository repoPedido;
	
	@Autowired
	private LineaPedidoRepository repoLineaDePedido;
	
	@Autowired
	private HttpSession sesion;
	
	@Override
	public Pedidos addProducto(Integer[] cantidades) {
		int comprobar=0;
		List<Productos> listaProductos=repoProductos.findAll();
		LineaPedido lineaPedido;

		Pedidos pedido = new Pedidos();
		repoPedido.save(pedido);
		List<LineaPedido> listaLineaPedidos;
		
		for (int i = 0; i < cantidades.length; i++) {
			
			lineaPedido=new LineaPedido(cantidades[i], listaProductos.get(i), pedido);
			repoLineaDePedido.save(lineaPedido);
			listaLineaPedidos=pedido.getListaLineaPedidos();
			listaLineaPedidos.add(lineaPedido);
			pedido.setListaLineaPedidos(listaLineaPedidos);
			
			comprobar+=cantidades[i];
			
		}
		if (comprobar>=1) {
			repoPedido.save(pedido);
			sesion.setAttribute("IdUltimoPedido", pedido.getId());
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
		for (LineaPedido a : repoPedido.getById((Long) sesion.getAttribute("IdUltimoPedido")).getListaLineaPedidos()) {
			resultado+= a.getCantidad()*a.getProducto().getPrecio();
		}
		return resultado;
	}	
	
	public Double sumaConcreta(Long id) {
		Double resultado=0.0;
		for (LineaPedido a : repoPedido.getById(id).getListaLineaPedidos()) {
			resultado+= a.getCantidad()*a.getProducto().getPrecio();
		}
		return resultado;
	}	
	
}
