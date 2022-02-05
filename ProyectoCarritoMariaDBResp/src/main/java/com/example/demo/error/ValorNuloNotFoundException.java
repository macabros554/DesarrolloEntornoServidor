package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ValorNuloNotFoundException extends RuntimeException{

	/**
	 * Mensaje de error que extiende de Runtime exeption y muestra el mensaje de un valor no puede ser nulo
	 */
	
	private static final long serialVersionUID = 1861511056225774607L;

	public ValorNuloNotFoundException() {
		super("No se pueden enviar valores nulos ");
	}
}
