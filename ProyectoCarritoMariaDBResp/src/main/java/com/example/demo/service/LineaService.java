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
	
	/**
	 * Para añadir la linea con los datos que nos han pasado al pedido que nos han pasado
	 * creamos una nueva linea y le añadinos los datos que tenemos
	 * @param linea
	 * @param idPedido
	 * @return linea
	 */
	
	
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
	
	/**
	 * Modificamos la linea que nos pasan la id con los datos de la linea que pasan por parametro
	 * @param linea
	 * @param id
	 * @return linea
	 */
	
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
	
	/**
	 * Comprueba si la linea existe, si existe envia la linea si no null
	 * @param id
	 * @return linea
	 */
	
	public LineaPedido buscarLinea(Long id) {
		return repoLinea.findById(id).orElse(null);
	}
	
	/**
	 * Guarda la linea que pe pasan como parametro
	 * @param linea
	 */
	
	public void guardarLinea(LineaPedido linea) {
		repoLinea.save(linea);
	}
	
	/**
	 * comprueba si la linea existe dentro del pedido que pasan por parametro
	 * @param idLin
	 * @param idPed
	 * @return linea
	 */
	
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
	
	/**
	 * Borra la linea que le pasan por parametro
	 * @param idLinea
	 * @param idPedido
	 * @return
	 */
	
	public Pedidos borrarLinea(Long idLinea,Long idPedido) {
		if (repoLinea.existsById(idLinea)) {
			repoLinea.delete(repoLinea.getById(idLinea));
			return servicioPedido.sacarPedido(idPedido);
		}else {
			return null;
		}
		
		
	}
	
}
