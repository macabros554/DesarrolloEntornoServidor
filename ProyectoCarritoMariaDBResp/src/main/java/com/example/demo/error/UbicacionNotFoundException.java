package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UbicacionNotFoundException extends RuntimeException{
	
	/**
	 * Mensaje de error que extiende de Runtime exeption y muestra el mensaje de linea no encontrada en pedido
	 */
	private static final long serialVersionUID = -3784400546833702284L;

	public UbicacionNotFoundException(Long idLin, Long idPed) {
		super("No se puede encontrar la linea con la ID: " + idLin + " en el pedido con ID: " + idPed);
	}
}
