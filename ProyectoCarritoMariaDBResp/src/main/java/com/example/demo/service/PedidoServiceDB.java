package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedidos;
import com.example.demo.model.Usuario;
import com.example.demo.repository.LineaPedidoRepository;
import com.example.demo.repository.PedidosRepository;
import com.example.demo.repository.UsuarioRepository;

@Service("pedidoServiceDB")
public class PedidoServiceDB {
	
	@Autowired
	private PedidosRepository repoPedido;
	
	@Autowired
	private LineaPedidoRepository repoLineaDePedido;
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	public Pedidos generarPedido(String nickname) {
		
		Pedidos pedidonuevo=new Pedidos();
		Usuario usu = repoUsuario.findById(nickname).orElse(null);
		pedidonuevo.setUsuario(usu);
		/*pedidonuevo.setDireccion(usu.getDireccion());
		pedidonuevo.setCorreoElectronico(usu.getCorreoelectronico());
		pedidonuevo.setTelefono(usu.getTelefono());*/
		usu.getListaPedidos().add(pedidonuevo);
		repoPedido.save(pedidonuevo);
		repoUsuario.save(usu);
		
		return pedidonuevo;
	}
	
	public List<LineaPedido> sacarListaPedido(Long id) {
		return repoPedido.getById(id).getListaLineaPedidos();
	}
	
	public Pedidos sacarPedido(Long id) {
		return repoPedido.findById(id).orElse(null);
	}
	
	public void guardarPedido(Pedidos pedidoEntrante) {
		repoPedido.save(pedidoEntrante);
	}
	
	public Pedidos editarPedido(Pedidos pedido,Long id) {
		if (repoPedido.existsById(id)) {
			Pedidos antPedido=repoPedido.getById(id);
			antPedido.setCorreoElectronico(pedido.getCorreoElectronico());
			antPedido.setDireccion(pedido.getDireccion());
			antPedido.setTelefono(pedido.getTelefono());
			return repoPedido.save(antPedido);
		} else {
			return null;
		}

		
	}
	
	public Usuario borrarPedido(Long id) {
		if (repoPedido.existsById(id)) {
			Pedidos pedido = repoPedido.findById(id).orElse(null);
			
			Usuario usuario = repoUsuario.findById(pedido.getUsuario().getNickname()).orElse(null);
			usuario.getListaPedidos().remove(pedido);
			repoUsuario.save(usuario);
				
			for (LineaPedido linea: pedido.getListaLineaPedidos()) {
				repoLineaDePedido.delete(linea);
			}
				
			repoPedido.delete(pedido);
			return usuario;
		}else {
			return null;
		}
		
		
	}
	
	
	
}
