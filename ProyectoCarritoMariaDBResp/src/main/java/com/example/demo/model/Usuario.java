package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	/*
	 * Le decioms que la id es el nickname 
	 */
	
	@Id
	private String nickname;
	private String nombre;
	private String contrasenia;
	private String correoelectronico;
	private String direccion;
	private String telefono;
	/*
	 * en la relacion OneToMany usamos el fetch de tipipo eager porque cargará todos los datos de las entidades que sean necesarias 
	 * esto incluye a los hijos
	 */
	@OneToMany(fetch = FetchType.EAGER)
	private List<Pedidos> listaPedidos = new ArrayList<>();
	
	public Usuario() {}

	public Usuario(String nickName, String nombre, String contrasenia, String correoElectronico, String direccion,
			String telefono) {
		super();

		this.nickname = nickName;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.correoelectronico = correoElectronico;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCorreoelectronico() {
		return correoelectronico;
	}

	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
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
	
	/*
	 * generamos hashCode, equals y toString
	 */

	@Override
	public int hashCode() {
		return Objects.hash(nickname);
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
		return Objects.equals(nickname, other.nickname);
	}

	@Override
	public String toString() {
		return "Usuario [nickname=" + nickname + ", nombre=" + nombre + ", contrasenia=" + contrasenia
				+ ", correonlectronico=" + correoelectronico + ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}

	
	
	
	
}
