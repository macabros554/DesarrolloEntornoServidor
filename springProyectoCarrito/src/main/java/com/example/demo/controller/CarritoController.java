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

import com.example.demo.model.Productos;
import com.example.demo.model.Usuario;
import com.example.demo.service.PedidosService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;

@Controller
public class CarritoController {

	@Autowired
	private UsuarioService servicioUsuario;
	
	@Autowired
	private PedidosService servicioPedido;
	
	@Autowired
	private HttpSession sesion;
	
	@Autowired
	private ProductoService sevicioProductos;
	
	@GetMapping({"/","login"})
	public String logearUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/login/submit")
	@RequestMapping (value="/login/submit", method=RequestMethod.POST)
	public String logearUsuarioSubmit(@Validated @ModelAttribute("usuario") Usuario intentoDeLogin,BindingResult bindingResult ) {
		if (!servicioUsuario.confirmarUsuario(intentoDeLogin)) {
			return "login";
		} else {
			sesion.setAttribute("usuario1", intentoDeLogin);
			sesion.setAttribute("usuario1NickName", intentoDeLogin.getNickName());
			return "redirect:/login/select";
		}
	}
	
	@GetMapping("/login/select")
	public String seleccionarAccion1(Model model) {
		model.addAttribute("Usuario", sesion.getAttribute("usuario1NickName"));
		return "select";
	}

	
	@GetMapping("/login/select/listaP")
	public String listaDeProductos(Model model) {
		model.addAttribute("Usuario", sesion.getAttribute("usuario1NickName"));
		model.addAttribute("pedidos",servicioPedido.getListaProductos());
		return "listaDeProductos";
	}
	
	@GetMapping("/login/select/NuevoP")
	public String nuevoPedido(Model model) {
		model.addAttribute("Usuario", sesion.getAttribute("usuario1NickName"));
		model.addAttribute("Productos",sevicioProductos.getListaProductos());
		return "pedidoNuevo";
	}
	
	@PostMapping("/login/select/NuevoP/submit")
	public String nuevoPedidoSubmit(@RequestParam(name="cantidades") Integer[] nuevoProducto) {
		if (!servicioPedido.meterPedidos(nuevoProducto)) {
			return "pedidoNuevo";
		} else {
			return "redirect:/login/select/NuevoP/envio";
		}
	}
	
	@GetMapping("/login/select/NuevoP/envio")
	public String envio(Model model) {
		return "envio";
	}
	
	@GetMapping("/cerrarSesion")
	public String cerrarSesion(Model model) {
		sesion.invalidate();
		return "redirect:/login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
