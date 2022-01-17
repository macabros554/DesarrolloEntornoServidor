package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@Column(name = "nickname", nullable = false)
	private String nickName;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "contrasenia", nullable = false)
	private String contrasenia;
	@Column(name = "email", nullable = false)
	private String correoElectronico;
	@Column(name = "direccion", nullable = false)
	private String direccion;
	@Column(name = "telefono", nullable = false)
	private String telefono;
	@OneToMany(fetch = FetchType.EAGER)
	@Column(name = "telefono", nullable = false)
	private List<Pedidos> listaPedidos = new ArrayList<>();
	
	public Usuario() {}

	public Usuario(String nickName, String nombre, String contrasenia, String correoElectronico, String direccion,
			String telefono) {
		super();

		this.nickName = nickName;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.correoElectronico = correoElectronico;
		this.direccion = direccion;
		this.telefono = telefono;
	}


	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
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

	public List<Pedidos> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<Pedidos> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	
	
	
	
}
