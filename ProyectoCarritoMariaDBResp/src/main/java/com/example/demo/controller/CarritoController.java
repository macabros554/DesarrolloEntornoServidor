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

import com.example.demo.error.LineaNotFoundException;
import com.example.demo.error.PedidoNotFoundException;
import com.example.demo.error.ProductoNotFoundException;
import com.example.demo.error.UbicacionNotFoundException;
import com.example.demo.error.UsuarioNotFoundException;
import com.example.demo.error.ValorNuloNotFoundException;
import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedidos;
import com.example.demo.model.Productos;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PedidosRepository;
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
	
	/**
	 * Metodo para recuperar la lista de usuarios de la base de datos
	 * @return lista de usuarios
	 */
	
	@GetMapping({"/users"})
	public ResponseEntity<List<Usuario>> listaUsuarios() {
		List<Usuario> result = servicioUsuario.mostrarUsuarios();
		
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(result);
		}
	}
	
	/**
	 * Devuelve la informacion de un usuario que pasamos por parametro
	 * Pasamos silo el nickname que es su id
	 * @param nickname 
	 * @return usuario
	 */
	
	@GetMapping({"/user"})
	public ResponseEntity<Usuario> usuario2(@RequestBody Usuario nickname) {
		Usuario result=servicioUsuario.datosUsuario(nickname.getNickname());
		
		if (result==null) {
			throw new UsuarioNotFoundException(nickname.getNickname());
		}else {
			return ResponseEntity.ok(result);
		}		
	}
	
	/**
	 * Devuelve todos los productos que podemos comprar
	 * @return lista de productos
	 */
	
	@GetMapping("/Productos")
	public ResponseEntity<List<Productos>> listaDeProductos() {	
		List<Productos> result = servicioProductos.listaProductos();
		
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(result);
		}
	}
	
	/**
	 * Devuelve los pedidos de un usuario
	 * @param nickname
	 * @return lista de pedidos
	 */
	
	@GetMapping("/Pedidos")
	public ResponseEntity<List<Pedidos>> listaDePedidos(@RequestBody Usuario nickname) {	
		List<Pedidos> result = servicioUsuario.listaPedidos(servicioUsuario.datosUsuario(nickname.getNickname()));
		
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(result);
		}
	}
	
	/**
	 * Muestra el pedido por la id que reciba 
	 * @param id
	 * @return pedido
	 */
	
	@GetMapping("/Pedido/{id}")
	public ResponseEntity<Pedidos> pedido(@PathVariable Long id) {	
		Pedidos result = servicioPedido.sacarPedido(id);
		
		if (result==null) {
			throw new PedidoNotFoundException(id);
		}else {
			System.out.println(result);
			return ResponseEntity.ok(result);
		}
	}
		
	/**
	 * Crea un pedido vacio en el usuario que reciba
	 * @param nickname
	 * @return Usuario
	 */
	
	@PostMapping("/Pedido")
	public ResponseEntity<Pedidos> crearPedido(@RequestBody Usuario nickname) {
		
		if (servicioUsuario.datosUsuario(nickname.getNickname())==null) {
			throw new UsuarioNotFoundException(nickname.getNickname());
		}else {
			return ResponseEntity.ok(servicioPedido.generarPedido(nickname.getNickname()));
		}
	}
	
	/**
	 * Edita el pedido que pasen por id con los datos del pedido que pasamos por requestBody
	 * @param id
	 * @param pedido
	 * @return pedido
	 */
	
	@PutMapping("/Pedido/{id}")
	public Pedidos editarPedido(@PathVariable Long id,@RequestBody Pedidos pedido) {		
		
		if("".equals(pedido.getDireccion()) || "".equals(pedido.getTelefono()) || "".equals(pedido.getCorreoElectronico())
				|| pedido.getDireccion()==null || pedido.getTelefono()==null || pedido.getCorreoElectronico()==null) {
			throw new ValorNuloNotFoundException();
		}else {
			Pedidos result=servicioPedido.editarPedido(pedido,id);
			
			if (result==null) {
				throw new PedidoNotFoundException(id);
			}else {
				return result;
			}
		}
	}
	
	/**
	 * Borramos el pedido que pasemos por id
	 * @param id
	 * @return si funciona no devuelve nada
	 */
	
	@DeleteMapping("/Pedido/{id}")
	public ResponseEntity<?> borrarPedido(@PathVariable Long id) {		
		Usuario result=servicioPedido.borrarPedido(id);
		
		if (result==null) {
			throw new PedidoNotFoundException(id);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	/**
	 * Recupera la linea a partir de la id de la linea 
	 * @param id
	 * @return Linea pedido
	 */
	
	@GetMapping("/Pedido/{idPed}/Linea/{idLin}")
	public ResponseEntity<LineaPedido> linea(@PathVariable Long idPed,@PathVariable Long idLin) {	
		LineaPedido result = servicioLinea.buscarLinea(idLin);
		
		if (result==null) {
			throw new LineaNotFoundException(idLin);
		}else {
			if (servicioPedido.sacarPedido(idPed)==null) {
				throw new PedidoNotFoundException(idPed);
			}else {
				if(servicioLinea.lineaExisteEnPedido(idPed, idLin)==null) {
					throw new UbicacionNotFoundException(idLin, idPed);
				}else {
					return ResponseEntity.ok(result);
				}
			}
		}
	}
	
	/**
	 * Creamos una linea en el pedido pasandole la id del pedido y los datos de la linea(cantidad y codigo de producto)
	 * @param id
	 * @param linea
	 * @return lineaCreada
	 */
	
	@PostMapping("Pedido/{id}/Linea/") 
	public ResponseEntity<LineaPedido> nuevaLinea(@PathVariable Long id,@RequestBody LineaPedido linea) {
		if (servicioProductos.buscarProducto(linea.getProducto().getId())==null) {
			throw new ProductoNotFoundException(linea.getProducto().getId());
		}else {
			if (servicioPedido.sacarPedido(id)==null) {
				throw new PedidoNotFoundException(id);
			}else {
				LineaPedido result = servicioLinea.anadirLinea(linea, id);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(result);
			}
		}

	}
	
	/**
	 * Borra la linea del pedido
	 * @param idPed
	 * @param idLin
	 * @return
	 */
	
	@DeleteMapping("Pedido/{idPed}/Linea/{idLin}")
	public ResponseEntity<?> borrarLinea(@PathVariable Long idPed,@PathVariable Long idLin) {
		
		if (servicioPedido.sacarPedido(idPed)==null) {
			throw new PedidoNotFoundException(idPed);
		}else {
			if(servicioLinea.lineaExisteEnPedido(idPed, idLin)==null) {
				throw new UbicacionNotFoundException(idLin, idPed);
			}else {
				servicioLinea.borrarLinea(idLin,idPed);
				return ResponseEntity.noContent().build();
			}
		}
	}
	
	/**
	 * Edita la linea que le pasemos la id del pedido que le pasemos por la id con los datos que le pasemos por request
	 * @param idPed
	 * @param idLin
	 * @param linea
	 * @return linea pedido
	 */
	
	@PutMapping("Pedido/{idPed}/Linea/{idLin}")
	public ResponseEntity<LineaPedido> editarLinea(@PathVariable Long idPed,@PathVariable Long idLin,@RequestBody LineaPedido linea) {		
		
		if (servicioProductos.buscarProducto(linea.getProducto().getId())==null) {
			throw new ProductoNotFoundException(linea.getProducto().getId());
		}else {
			if (servicioPedido.sacarPedido(idPed)==null) {
				throw new PedidoNotFoundException(idPed);
			}else {
				if(servicioLinea.lineaExisteEnPedido(idPed, idLin)==null) {
					throw new UbicacionNotFoundException(idLin, idPed);
				}else {
					LineaPedido result=servicioLinea.editarLinea(linea,idLin);
					
					if (result==null) {
						throw new LineaNotFoundException(idLin);
					}else {
						return ResponseEntity.ok(result);
					}
				}
			}
		}
	}
}