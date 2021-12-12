package com.example.demo.model;

import java.time.LocalDateTime;

public class Pedidos {
	
	private LocalDateTime fechaPack;
	private static int referencia=0;
	private String direccion;
	private String telefono;

	public Pedidos(String direccion, String telefono) {
		super();
		this.direccion = direccion;
		this.telefono = telefono;
		this.fechaPack = LocalDateTime.now();
		referencia+=1;
	}

	public LocalDateTime getFechaPack() {
		return fechaPack;
	}

	public void setFechaPack(LocalDateTime fechaPack) {
		this.fechaPack = fechaPack;
	}

	public static int getReferencia() {
		return referencia;
	}

	public static void setReferencia(int referencia) {
		Pedidos.referencia = referencia;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
