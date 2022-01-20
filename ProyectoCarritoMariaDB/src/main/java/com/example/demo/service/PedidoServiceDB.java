package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedidos;
import com.example.demo.model.Productos;
import com.example.demo.model.Usuario;
import com.example.demo.repository.LineaPedidoRepository;
import com.example.demo.repository.PedidosRepository;
import com.example.demo.repository.ProductosRepository;
import com.example.demo.repository.UsuarioRepository;

@Service("pedidoServiceDB")
public class PedidoServiceDB {
	
	@Autowired
	private HttpSession sesion;
	
	@Autowired
	private UsuarioServiceDB servicioUsuario;
	
	@Autowired
	private PedidosRepository repoPedido;
	
	@Autowired
	private LineaPedidoRepository repoLineaDePedido;
	
	@Autowired
	private UsuarioRepository repoUsuario;
	@Autowired
	private ProductosRepository repoProductos;

	
	public List<LineaPedido> ultimaListaPedido() {
		return repoPedido.getById((Long) sesion.getAttribute("IdUltimoPedido")).getListaLineaPedidos();
	}

	
	public Pedidos ultimoPedido() {
		return repoPedido.getById((Long) sesion.getAttribute("IdUltimoPedido"));
	}

	
	public void guardarPedido(Pedidos pedidoEntrante) {
		repoPedido.save(pedidoEntrante);
	}

	
	public void actualizarPedido(Pedidos pedidoEntrante) {
		Pedidos pedidoExistente=ultimoPedido();
		pedidoExistente.setCorreoElectronico(pedidoEntrante.getCorreoElectronico());
		pedidoExistente.setDireccion(pedidoEntrante.getDireccion());
		pedidoExistente.setTelefono(pedidoEntrante.getTelefono());
		repoPedido.save(pedidoExistente);
		
	}

	
	public Pedidos editPedido(Integer[] cantidades) {
		int comprobar=0;
		LineaPedido lineaPedido;
		Pedidos pedido = ultimoPedido();
		List<LineaPedido> listaLineaPedidos = new ArrayList<>();
		listaLineaPedidos=pedido.getListaLineaPedidos();
		
		for (int i = 0; i < cantidades.length; i++) {
			lineaPedido=listaLineaPedidos.get(i);
			lineaPedido.setCantidad(cantidades[i]);
			listaLineaPedidos.get(i).setCantidad(cantidades[i]);
			repoLineaDePedido.save(lineaPedido);
			comprobar+=cantidades[i];
		}
		if (comprobar>=1) {
			pedido.setListaLineaPedidos(listaLineaPedidos);
			repoPedido.save(pedido);
			sesion.setAttribute("IdUltimoPedido", pedido.getId());
			return pedido;
		}else {
			return null;
		}
	}
	
	public Pedidos sacarPedido(Long id) {
		return repoPedido.getById(id);
	}
	
	
	public void borrarPedido(Long id, String usuarioName) {
		Pedidos pedido = repoPedido.findById(id).orElse(null);
		Usuario usuario = repoUsuario.findById(usuarioName).orElse(null);
		usuario.getListaPedidos().remove(pedido);
		repoUsuario.save(usuario);
		
		for (LineaPedido currentOrder: pedido.getListaLineaPedidos()) {
			repoLineaDePedido.delete(currentOrder);
		}
		
		repoPedido.delete(pedido);
		
	}
	
	
	
}
