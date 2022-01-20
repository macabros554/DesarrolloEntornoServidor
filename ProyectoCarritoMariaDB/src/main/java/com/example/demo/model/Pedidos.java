package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="pedidos")
public class Pedidos {
	@CreationTimestamp
	private Date fechaPack;
	private String direccion;
	private String telefono;
	private String correoElectronico;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @OneToMany(mappedBy = "pedido")
	private List<LineaPedido> listaLineaPedidos = new ArrayList<>();

	public Pedidos(){}

	public Pedidos(String direccion, String telefono, String correoElectronico) {
		super();
		this.direccion = direccion;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
	}
	
	
	public Date getFechaPack() {
		return fechaPack;
	}

	public void setFechaPack(Date fechaPack) {
		this.fechaPack = fechaPack;
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
	
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<LineaPedido> getListaLineaPedidos() {
		return listaLineaPedidos;
	}

	public void setListaLineaPedidos(List<LineaPedido> listaLineaPedidos) {
		this.listaLineaPedidos = listaLineaPedidos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedidos other = (Pedidos) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Pedidos [fechaPack=" + fechaPack + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", correoElectronico=" + correoElectronico + ", id=" + id + "]";
	}
	
	
	
}
