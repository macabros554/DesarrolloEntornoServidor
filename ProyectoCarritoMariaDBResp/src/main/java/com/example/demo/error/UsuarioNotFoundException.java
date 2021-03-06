package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException{

	
	/**
	 * Mensaje de error que extiende de Runtime exeption y muestra el mensaje de usuario no encontrado
	 */
	private static final long serialVersionUID = -1373236377498004439L;

	public UsuarioNotFoundException(String id) {
		super("No se puede encontrar el usuario con la ID: " + id);
	}
}
