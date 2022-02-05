package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductoNotFoundException extends RuntimeException{

	/**
	 * Mensaje de error que extiende de Runtime exeption y muestra el mensaje de producto no encontrado
	 */
	private static final long serialVersionUID = -2506811582208367968L;

	public ProductoNotFoundException(Long id) {
		super("No se puede encontrar el Producto con la ID: " + id);
	}
}
