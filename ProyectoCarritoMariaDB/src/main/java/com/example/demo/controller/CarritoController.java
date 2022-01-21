package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Pedidos;
import com.example.demo.model.Usuario;
import com.example.demo.service.PedidoServiceDB;
import com.example.demo.service.ProductoServiceDB;
import com.example.demo.service.UsuarioServiceDB;

@Controller
public class CarritoController {
	
	@Autowired
	private HttpSession sesion;
	
	@Autowired
	private UsuarioServiceDB servicioUsuario;
	
	@Autowired
	private PedidoServiceDB servicioPedido;
	
	@Autowired
	private ProductoServiceDB servicioProductos;
	

	
	@GetMapping({"/","login"})
	public String logearUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/login/submit")
	@RequestMapping (value="/login/submit", method=RequestMethod.POST)
	public String logearUsuarioSubmit(@Validated @ModelAttribute("usuario") Usuario intentoDeLogin,BindingResult bindingResult ) {
		
		if (servicioUsuario.sacarUsuario(intentoDeLogin)==null) {
			return "login";
		}else {
			return "redirect:/login/select";
		}
		
	}
	
	@GetMapping("/login/select")
	public String seleccionarAccion1(Model model) {
		model.addAttribute("usuario", servicioUsuario.datosUsuario((String)sesion.getAttribute("usuario1")));
		model.addAttribute("asas", "asda");
		return "select";
	}

	
	@GetMapping("/login/select/listaP")
	public String listaDeProductos(Model model) {
		model.addAttribute("usuario", servicioUsuario.datosUsuario((String)sesion.getAttribute("usuario1")));		
		model.addAttribute("pedidos",servicioUsuario.listaPedidos(servicioUsuario.datosUsuario((String)sesion.getAttribute("usuario1"))));
		return "listaDeProductos";
	}
	
	
	@GetMapping("/login/select/NuevoP")
	public String nuevoPedido(Model model) {
		model.addAttribute("usuario", servicioUsuario.datosUsuario((String)sesion.getAttribute("usuario1")));
		model.addAttribute("productos",servicioProductos.listaProductos());
		return "pedidoNuevo";
	}
	
	@PostMapping("/login/select/NuevoP/submit")
	public String nuevoPedidoSubmit(Model model, @RequestParam(name="cantidades") Integer[] nuevoProducto) {
		if(servicioProductos.addProducto(nuevoProducto)==null) {
			return "redirect:/login/select/NuevoP";
		}else {
			return "redirect:/login/select/NuevoP/envio";
		}
	}
	
	@GetMapping("/login/select/NuevoP/envio")
	public String envio(Model model) {
		model.addAttribute("usuario", servicioUsuario.datosUsuario((String)sesion.getAttribute("usuario1")));
		model.addAttribute("listaPedidos",servicioPedido.ultimaListaPedido());
		model.addAttribute("direccion", servicioPedido.ultimoPedido());
		return "envio";
	}
	
	@PostMapping("/login/select/NuevoP/envio/submit")
	public String nuevoEnvioSubmit(Model model,
			@RequestParam(name="direccion") String direccion,
			@RequestParam(name="email") String email,
			@RequestParam(name="telefono") String telefono
			) {
		if(direccion=="" || telefono=="" || email=="") {
			return "redirect:/login/select/NuevoP/envio";
		}else {
			Pedidos pedidoGuardado = servicioPedido.ultimoPedido();
			pedidoGuardado.setCorreoElectronico(email);
			pedidoGuardado.setDireccion(direccion);
			pedidoGuardado.setTelefono(telefono);
			servicioPedido.actualizarPedido(pedidoGuardado);
			return "redirect:/login/select/NuevoP/envio/resumen";
		}
	}
	
	@GetMapping("/login/select/NuevoP/envio/resumen")
	public String resumen(Model model) {
		model.addAttribute("usuario", servicioUsuario.datosUsuario((String)sesion.getAttribute("usuario1")));
		model.addAttribute("listaPedidos",servicioPedido.ultimaListaPedido());
		model.addAttribute("direccion", servicioPedido.ultimoPedido());
		model.addAttribute("total", servicioProductos.suma());
		servicioUsuario.guardarPedidoEnUsuario();
		return "resumenEnvio";
	}
	
	@GetMapping("/login/select/EditarProducto/{id}")
	public String editarPedido(@PathVariable Long id, Model model) {
		model.addAttribute("usuario", servicioUsuario.datosUsuario((String)sesion.getAttribute("usuario1")));
		model.addAttribute("lista",servicioPedido.ultimaListaPedido());
		if (id==null) {
			return "redirect:/login/select/listaP";
		}else {
			sesion.setAttribute("IdUltimoPedido", id);
			return "pedidoNuevoEdit";
		}
	}
	
	@PostMapping("/login/select/EditarProducto/submit")
	public String editarPedidoSubmit(@RequestParam(name="cantidades") Integer[] nuevoProducto) {
		//creamos la lista de productos en los servicios pedidos y productos
		if(servicioPedido.editPedido(nuevoProducto)==null) {
			return "redirect:/login/select/EditarProducto/"+sesion.getAttribute("IdUltimoPedido");
		}else {
			return "redirect:/login/select/EditarProducto/EditarEnvio";
		}
	}
	
	@GetMapping("/login/select/EditarProducto/EditarEnvio")
	public String editarEnvio(Model model) {
		model.addAttribute("usuario", servicioUsuario.datosUsuario((String)sesion.getAttribute("usuario1")));
		model.addAttribute("listaPedidos", servicioPedido.ultimaListaPedido());
		return "envioEdit";
	}
	
	@PostMapping("/login/select/EditarProducto/EditarEnvio/submit")
	public String editarEnvioSubmit(Model model, @ModelAttribute("nuevaDireccion")
			@RequestParam(name="direccion") String direccion,
			@RequestParam(name="email") String email,
			@RequestParam(name="telefono") String telefono
			) {
		if(direccion=="" || telefono=="" || email=="") {
			return "redirect:/login/select/EditarProducto/EditarEnvio";
		}else {
			Pedidos pedidoGuardado = servicioPedido.ultimoPedido();
			pedidoGuardado.setCorreoElectronico(email);
			pedidoGuardado.setDireccion(direccion);
			pedidoGuardado.setTelefono(telefono);
			servicioPedido.actualizarPedido(pedidoGuardado);
			return "redirect:/login/select/NuevoP/envio/resumenEditado";
		}
	}
	
	@GetMapping("/login/select/NuevoP/envio/resumenEditado")
	public String resumenEdit(Model model) {
		model.addAttribute("usuario", servicioUsuario.datosUsuario((String)sesion.getAttribute("usuario1")));
		model.addAttribute("listaPedidos",servicioPedido.ultimaListaPedido());
		model.addAttribute("direccion", servicioPedido.ultimoPedido());
		model.addAttribute("total", servicioProductos.suma());
		return "resumenEnvio";
	}
	
	@PostMapping("/login/select/listaP/{id}")
	public String borrarProducto(@PathVariable Long id, Model model) {
		servicioPedido.borrarPedido(id, (String) sesion.getAttribute("usuario1"));
		return "redirect:/login/select/listaP";
	}
	
	@GetMapping("/login/select/listaP/datos/{id}")
	public String datosPedido(Model model,@PathVariable Long id) {
		model.addAttribute("usuario", servicioUsuario.datosUsuario((String)sesion.getAttribute("usuario1")));
		model.addAttribute("listaPedidos",servicioPedido.sacarListaPedido(id));
		model.addAttribute("direccion", servicioPedido.sacarPedido(id));
		model.addAttribute("total", servicioProductos.sumaConcreta(id));
		return "datosPedido";
	}
	
	@GetMapping("/cerrarSesion")
	public String cerrarSesion() {
		sesion.invalidate();
		return "redirect:/login";
	}
	

	
	
	
	
	
	
	
}