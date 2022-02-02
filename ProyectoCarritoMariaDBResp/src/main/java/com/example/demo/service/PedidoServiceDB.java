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
	
	public Usuario generarPedido(String nickname) {
		
		Pedidos pedidonuevo=new Pedidos();
		Usuario usu = repoUsuario.findById(nickname).orElse(null);
		/*pedidonuevo.setDireccion(usu.getDireccion());
		pedidonuevo.setCorreoElectronico(usu.getCorreoelectronico());
		pedidonuevo.setTelefono(usu.getTelefono());*/
		usu.getListaPedidos().add(pedidonuevo);
		repoPedido.save(pedidonuevo);
		
		return repoUsuario.save(usu);
	}
	
	public List<LineaPedido> sacarListaPedido(Long id) {
		return repoPedido.getById(id).getListaLineaPedidos();
	}
	
	public Pedidos sacarPedido(Long id) {
		return repoPedido.getById(id);
	}
	
	public void guardarPedido(Pedidos pedidoEntrante) {
		repoPedido.save(pedidoEntrante);
	}
	
	public Pedidos editarPedido(Pedidos pedido,Long id) {
		Pedidos antPedido=repoPedido.getById(id);
		
		if (repoPedido.existsById(id)) {
			antPedido.setCorreoElectronico(pedido.getCorreoElectronico());
			antPedido.setDireccion(pedido.getDireccion());
			antPedido.setTelefono(pedido.getTelefono());
			return repoPedido.save(antPedido);
		} else {
			return null;
		}

		
	}
	
	public Usuario borrarPedido(Long id, String usuarioName) {
		if (repoPedido.existsById(id)) {
			Pedidos pedido = repoPedido.findById(id).orElse(null);
			if (repoUsuario.existsById(usuarioName)) {
				Usuario usuario = repoUsuario.findById(usuarioName).orElse(null);
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

		}else {
			return null;
		}
		
		
	} 
	
	
	
}
