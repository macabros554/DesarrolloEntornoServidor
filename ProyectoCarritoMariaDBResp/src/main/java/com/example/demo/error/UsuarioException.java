package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1373236377498004439L;

	public UsuarioException(String id) {
		super("No se puede encontrar el usuarui con la ID: " + id);
	}
}
