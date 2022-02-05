package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoNotFoundException extends RuntimeException{

	
	/**
	 * Mensaje de error que extiende de Runtime exeption y muestra el mensaje de pedido no encontrado
	 */
	private static final long serialVersionUID = 3050440165348817552L;

	public PedidoNotFoundException(Long id) {
		super("No se puede encontrar el Pedido con la ID: " + id);
	}
}
