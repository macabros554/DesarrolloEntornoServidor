package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedidos;
import com.example.demo.repository.LineaPedidoRepository;

@Service("lineaService")
public class LineaService {
	
	@Autowired
	private LineaPedidoRepository repoLinea;
	
	@Autowired
	private PedidoServiceDB servicioPedido;
	
	@Autowired
	private ProductoServiceDB servicioProducto;
	
	public LineaPedido anadirLinea(LineaPedido linea, Long idPedido) {
		
		Pedidos pedido=servicioPedido.sacarPedido(idPedido);
		LineaPedido lineaNueva=new LineaPedido();
		lineaNueva.setCantidad(linea.getCantidad());
		lineaNueva.setPedido(pedido);
		lineaNueva.setProducto(servicioProducto.buscarProducto(linea.getProducto().getId()));
		pedido.getListaLineaPedidos().add(lineaNueva);
		guardarLinea(lineaNueva);
		servicioPedido.guardarPedido(pedido);
		
		return lineaNueva;
		
	}
	
	public LineaPedido editarLinea(LineaPedido linea,Long id) {
		if (repoLinea.existsById(id)) {
			LineaPedido lineaAnt=repoLinea.getById(id);
			lineaAnt.setCantidad(linea.getCantidad());
			lineaAnt.setProducto(servicioProducto.buscarProducto(linea.getProducto().getId()));
			return repoLinea.save(lineaAnt);
		}else {
			return null;
		}
		
	}
	
	public LineaPedido buscarLinea(Long id) {
		return repoLinea.findById(id).orElse(null);
	}
	
	public void guardarLinea(LineaPedido linea) {
		repoLinea.save(linea);
	}
	
	public LineaPedido lineaExisteEnPedido(Long idLin,Long idPed) {
		Pedidos pedido= servicioPedido.sacarPedido(idPed);
		LineaPedido lineapedido=null;
		for (LineaPedido linea : pedido.getListaLineaPedidos()) {
			if (linea.getId()==idLin) {
				lineapedido=linea;
			}
		}
		return lineapedido;
	}
	
	public Pedidos borrarLinea(Long idLinea,Long idPedido) {
		if (repoLinea.existsById(idLinea)) {
			repoLinea.delete(repoLinea.getById(idLinea));
			return servicioPedido.sacarPedido(idPedido);
		}else {
			return null;
		}
		
		
	}
	
}
