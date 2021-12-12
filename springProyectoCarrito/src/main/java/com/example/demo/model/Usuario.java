package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
	
	private String nickName;
	private String nombre;
	private String contrasenia;
	private String direccion;
	private String telefono;
	private static List<Pedidos> listapedidos = new ArrayList<>();
		
	public Usuario() {
	}
	
	public Usuario(String nickName,String contrasenia) {
		super();
		this.nickName = nickName;
		this.contrasenia = contrasenia;
	}

	public Usuario(String nickName, String nombre, String contrasenia, String direccion, String telefono) {
		super();
		this.nickName = nickName;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contrasenia, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(contrasenia, other.contrasenia) && Objects.equals(nombre, other.nombre);
	}
	
	
}
