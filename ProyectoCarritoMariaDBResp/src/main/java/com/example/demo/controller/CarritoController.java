package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.PedidoNotFoundException;
import com.example.demo.error.UsuarioException;
import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedidos;
import com.example.demo.model.Productos;
import com.example.demo.model.Usuario;
import com.example.demo.service.LineaService;
import com.example.demo.service.PedidoServiceDB;
import com.example.demo.service.ProductoServiceDB;
import com.example.demo.service.UsuarioServiceDB;

@RestController
public class CarritoController {
	
	@Autowired
	private UsuarioServiceDB servicioUsuario;
	
	@Autowired
	private PedidoServiceDB servicioPedido;
	
	@Autowired
	private ProductoServiceDB servicioProductos;
	
	@Autowired
	private LineaService servicioLinea;

	
	@GetMapping({"/users"})
	public ResponseEntity<List<Usuario>> listaUsuarios() {
		List<Usuario> result = servicioUsuario.mostrarUsuarios();
		
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(result);
		}
	}
	
	@GetMapping({"/user"})
	public ResponseEntity<Usuario> usuario2(@RequestBody Usuario id) {
		
		if (servicioUsuario.datosUsuario(id.getNickname())==null) {
			throw new UsuarioException(id.getNickname());
		}else {
			return ResponseEntity.ok(servicioUsuario.datosUsuario(id.getNickname()));
		}		
	}
	
	@GetMapping("/user/Pedidos")
	public ResponseEntity<List<Pedidos>> listaDePedidos(@RequestBody Usuario nickname) {	
		List<Pedidos> result = servicioUsuario.listaPedidos(servicioUsuario.datosUsuario(nickname.getNickname()));
		
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(result);
		}
		
	}
	
	@GetMapping("/Productos")
	public ResponseEntity<List<Productos>> listaDeProductos() {	
		List<Productos> result = servicioProductos.listaProductos();
		
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(result);
		}
		
	}
	
	@PostMapping("/Pedido")
	public ResponseEntity<Usuario> crearPedido(@RequestBody Usuario nickname) {
		
		if (servicioUsuario.datosUsuario(nickname.getNickname())==null) {
			throw new UsuarioException(nickname.getNickname());
		}else {
			return ResponseEntity.ok(servicioPedido.generarPedido(nickname.getNickname()));
		}
	}
	
	@PostMapping("Pedido/{id}/Linea/")
	public ResponseEntity<LineaPedido> nuevaLinea(@PathVariable Long id,@RequestBody LineaPedido linea) {
		LineaPedido result = servicioLinea.anadirLinea(linea, id);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PutMapping("/Pedido/{id}")
	public Pedidos editarPedido(@PathVariable Long id,@RequestBody Pedidos pedido) {		
		Pedidos result=servicioPedido.editarPedido(pedido,id);
		
		if (result==null) {
			throw new PedidoNotFoundException(id);
		}else {
			return result;
		}
		
	}
	
	@DeleteMapping("/Pedido/{id}")
	public ResponseEntity<?> borrarPedido(@PathVariable Long id,@RequestBody String nickname) {		
		Usuario result=servicioPedido.borrarPedido(id,nickname);
		
		if (result==null) {
			throw new PedidoNotFoundException(id);
		}else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	@DeleteMapping("Pedido/{idPed}/Linea/{idLin}")
	public ResponseEntity<?> borrarLinea(@PathVariable Long idLin,@PathVariable Long idPed) {
		
		Pedidos result=servicioLinea.borrarLinea(idLin,idPed);
		
		if (result==null) {
			throw new PedidoNotFoundException(idLin);
		}else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	@PutMapping("Pedido/{idPed}/Linea/{idLin}")
	public LineaPedido EditarLinea(@PathVariable Long idPed,@PathVariable Long idLin,@RequestBody LineaPedido linea) {		
		LineaPedido result=servicioLinea.editarLinea(linea,idLin);
		
		if (result==null) {
			throw new PedidoNotFoundException(idLin);
		}else {
			return result;
		}
		
	}
	
	
	

	
	
	
	
	
	
	
}