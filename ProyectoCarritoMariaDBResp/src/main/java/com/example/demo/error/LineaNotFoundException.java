package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LineaNotFoundException extends RuntimeException{


	private static final long serialVersionUID = 8495021113400401729L;

	/**
	 * 
	 */
	

	public LineaNotFoundException(Long id) {
		super("No se puede encontrar la linea con la ID: " + id);
	}
}
