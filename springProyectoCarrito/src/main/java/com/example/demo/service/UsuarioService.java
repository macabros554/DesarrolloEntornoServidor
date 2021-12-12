package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;

@Service
public class UsuarioService {

	private List<Usuario> listaUsuarios = new ArrayList<>();
	
	//por si luego me da tiempo a añadir usuarios
	public boolean anadirEmpleado(Usuario e) {
		return listaUsuarios.add(e);
	}
	//Si lo encuentra dice true y continuamos si no tiene que dar error(se implementara en otro lado)
	public boolean confirmarUsuario(Usuario e) {
		boolean confirmar=false;
		
		for (Usuario usuario : listaUsuarios) {
			if (usuario.getNickName().equalsIgnoreCase(e.getNickName())) {
				if (usuario.getContrasenia().equalsIgnoreCase(e.getContrasenia())) {
					confirmar=true;
					return confirmar;
				}
			}
		}
		
		return confirmar;
	}
	//
	public void editarUsuario(Usuario e) {
		
	}
	
	@PostConstruct
	public void init() {
		listaUsuarios.addAll(
				Arrays.asList(new Usuario("admin","admin","admin","admin","000000001"),
						new Usuario("javi","javi","2001","la lentega Nº3 2A","123456789"),
						new Usuario("usuario","usuario","usuario","la lentega Nº3 1A","123456789")
						)
				);
		
	}
}
