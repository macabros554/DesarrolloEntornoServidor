package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pedidos;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Primary
@Service("usuarioServiceDB")
public class UsuarioServiceDB implements InterfaceUsuario{

	@Autowired
	private UsuarioRepository repoUsuario;
	
	@Autowired
	private HttpSession sesion;
	
	@Autowired
	private PedidoServiceDB servicioPedido;
	
	@Override
	public Usuario sacarUsuario(Usuario e) {
		for (Usuario usuario : repoUsuario.findAll()) {
			if (usuario.getNickName().equalsIgnoreCase(e.getNickName())) {
				if (usuario.getContrasenia().equalsIgnoreCase(e.getContrasenia())) {
					sesion.setAttribute("usuario1", usuario.getNickName());
					return usuario;
				}
			}
		}
		return null;
	}

	@Override
	public List<Pedidos> listaPedidos(Usuario a) {
		return a.getListaPedidos();
	}

	@Override
	public void guardarPedidoEnUsuario() {
		Usuario usu=datosUsuario((String)sesion.getAttribute("usuario1"));
		List<Pedidos> listaPedidos;
		listaPedidos=usu.getListaPedidos();
		listaPedidos.add(servicioPedido.ultimoPedido());
		usu.setListaPedidos(listaPedidos);
		repoUsuario.save(usu);
	}

	@Override
	public Usuario datosUsuario(String nickname) {
		return repoUsuario.getById(nickname);
	}

}
