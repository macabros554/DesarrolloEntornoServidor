package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		//añadimos el model usuario para poder usarlo y recogerlo en el post
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/login/submit")
	@RequestMapping (value="/login/submit", method=RequestMethod.POST)
	public String logearUsuarioSubmit(@Validated @ModelAttribute("usuario") Usuario intentoDeLogin,BindingResult bindingResult ) {
		
		if (servicioUsuario.sacarUsuario(intentoDeLogin)==null) {
			return "login";
		}else {
			sesion.setAttribute("usuario1", servicioUsuario.sacarUsuario(intentoDeLogin));
			return "redirect:/login/select";
		}
		
	}
	
	@GetMapping("/login/select")
	public String seleccionarAccion1(Model model) {
		model.addAttribute("usuario", sesion.getAttribute("usuario1"));
		return "select";
	}

	
	@GetMapping("/login/select/listaP")
	public String listaDeProductos(Model model) {
		model.addAttribute("usuario", sesion.getAttribute("usuario1"));		
		model.addAttribute("pedidos",servicioUsuario.listaPedidos((Usuario) sesion.getAttribute("usuario1")));
		return "listaDeProductos";
	}
	
	
	@GetMapping("/login/select/NuevoP")
	public String nuevoPedido(Model model) {
		model.addAttribute("usuario", sesion.getAttribute("usuario1"));
		model.addAttribute("productos",servicioProductos.listaProductos());
		return "pedidoNuevo";
	}
	
	@PostMapping("/login/select/NuevoP/submit")
	public String nuevoPedidoSubmit(Model model, @RequestParam(name="cantidades") Integer[] nuevoProducto) {
		if(servicioProductos.addProducto(nuevoProducto)==null) {
			//model.addAttribute("pedido",servicioProductos.addProducto(nuevoProducto));
			return "redirect:/login/select/NuevoP";
		}else {
			return "redirect:/login/select/NuevoP/envio";
		}
	}
	
	@GetMapping("/login/select/NuevoP/envio")
	public String envio(Model model) {
		model.addAttribute("usuario", sesion.getAttribute("usuario1"));
		model.addAttribute("listaPedidos",servicioProductos.getUltimoPedido().getListaLineaPedidos());
		model.addAttribute("direccion", servicioProductos.getUltimoPedido());
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
			Pedidos pedidoGuardado;
			pedidoGuardado=servicioProductos.getUltimoPedido();
			pedidoGuardado.setCorreoElectronico(email);
			pedidoGuardado.setDireccion(direccion);
			pedidoGuardado.setTelefono(telefono);
			servicioProductos.setUltimoPedido(pedidoGuardado);
			return "redirect:/login/select/NuevoP/envio/resumen";
		}
	}
	
	@GetMapping("/login/select/NuevoP/envio/resumen")
	public String resumen(Model model) {
		model.addAttribute("usuario", sesion.getAttribute("usuario1"));
		model.addAttribute("listaPedidos",servicioProductos.getUltimoPedido().getListaLineaPedidos());
		model.addAttribute("direccion", servicioProductos.getUltimoPedido());
		model.addAttribute("total", servicioProductos.suma());
		servicioUsuario.guardarUsuario(servicioProductos.getUltimoPedido());
		

		return "resumenEnvio";
	}
	
	@GetMapping("/cerrarSesion")
	public String cerrarSesion() {
		sesion.invalidate();
		return "redirect:/login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
