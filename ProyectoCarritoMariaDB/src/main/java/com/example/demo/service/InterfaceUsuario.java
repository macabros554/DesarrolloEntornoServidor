package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Pedidos;
import com.example.demo.model.Usuario;

public interface InterfaceUsuario {
	
	public Usuario sacarUsuario(Usuario u);
	public Pedidos sacarPedido(Usuario e);
	public List<Pedidos> listaPedidos(Usuario a);
	public boolean editPedido(Pedidos e,Usuario a);
	public void guardarUsuario(Pedidos e);

}
