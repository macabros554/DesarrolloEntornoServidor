package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedidos;
import com.example.demo.model.Usuario;

@Service("pedidoServiceDB")
public class PedidoServiceDB implements InterfacePedidos{
	
	@Autowired
	private HttpSession sesion;
	
	//no usado
	@Override
	public boolean nuevoPedido(Pedidos base,Pedidos nuevo) {
		Usuario usu= (Usuario) sesion.getAttribute("usuario1");
		List<Pedidos> listaPedidos = new ArrayList<>();
		base.setDireccion(nuevo.getDireccion());
		base.setTelefono(nuevo.getTelefono());
		base.setCorreoElectronico(nuevo.getCorreoElectronico());
		listaPedidos= usu.getListaPedidos();
		listaPedidos.add(base);
		usu.setListaPedidos(listaPedidos);
		sesion.setAttribute("usuario1", usu);
		
		return false;
	}

	@Override
	public List<LineaPedido> ultimoPedido() {
		
		return null;
	}
	
}
