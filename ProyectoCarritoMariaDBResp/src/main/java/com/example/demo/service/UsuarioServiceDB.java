package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pedidos;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Primary
@Service("usuarioServiceDB")
public class UsuarioServiceDB{

	@Autowired
	private UsuarioRepository repoUsuario;
	
	/**
	 * Devuelve la lista de usuarios de la base de datos
	 * @return lista de usuarios
	 */
	
	public List<Usuario> mostrarUsuarios(){
		return repoUsuario.findAll();
	}

	/**
	 * devuelve la lista de pedidos de la lista de pedidos del usuario que se para por parametro
	 * @param a
	 * @return lista de ledidos
	 */
	
	public List<Pedidos> listaPedidos(Usuario a) {
		return a.getListaPedidos();
	}
	
	/**
	 * Pasa el PK de usuario en mi caso el nickname y te devuelve los datos del usuario que esta guardado en la base de datos
	 * @param nickname
	 * @return usuario
	 */

	public Usuario datosUsuario(String nickname) {
		return repoUsuario.findById(nickname).orElse(null);
	}

	/*
	 * recordatorio 
	 * En caso de que la herramienta de eclipse que se llama variables no funcione 
	 * usar syso para ver lo que contiene la variable
	 */
}
