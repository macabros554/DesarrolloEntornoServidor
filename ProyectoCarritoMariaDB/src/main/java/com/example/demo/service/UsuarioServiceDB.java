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
	
	@Override
	public Usuario sacarUsuario(Usuario e) {
		for (Usuario usuario : repoUsuario.findAll()) {
			if (usuario.getNickName().equalsIgnoreCase(e.getNickName())) {
				if (usuario.getContrasenia().equalsIgnoreCase(e.getContrasenia())) {
					return usuario;
				}
			}
		}
		return null;
	}

	@Override
	public Pedidos sacarPedido(Usuario e) {
		
		return null;
	}

	@Override
	public List<Pedidos> listaPedidos(Usuario a) {
		// TODO Auto-generated method stub
		return a.getListaPedidos();
	}

	@Override
	public boolean editPedido(Pedidos e, Usuario a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void guardarUsuario(Pedidos e) {
		Usuario usu=(Usuario) sesion.getAttribute("usuario1");
		List<Pedidos> listaPedidos = new ArrayList<>();
		listaPedidos=usu.getListaPedidos();
		listaPedidos.add(e);
		usu.setListaPedidos(listaPedidos);
		sesion.setAttribute("usuario1", usu);
		repoUsuario.save(usu);
		
	}

}
