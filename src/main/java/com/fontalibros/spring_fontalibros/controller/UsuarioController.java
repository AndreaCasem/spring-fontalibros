package com.fontalibros.spring_fontalibros.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fontalibros.spring_fontalibros.model.Orden;
import com.fontalibros.spring_fontalibros.model.Usuario;
import com.fontalibros.spring_fontalibros.service.IOrdenService;
import com.fontalibros.spring_fontalibros.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IOrdenService ordenService;
	
	// Metodo para mostrar el formulario de registro /usuario/registro
	@GetMapping("/registro")
	public String crear() {
		return "usuario/registro";
	}
	
	// Metodo para guardar el registro de usuario
	@PostMapping("/save")
	public String save(Usuario usuario) {
		logger.info("Usuario registro: {}", usuario);
		
		usuario.setTipo("USER");
		usuarioService.save(usuario);
		
		return "redirect:/";
	}
	
	// Metodo para mostrar la vista de login
	@GetMapping("login")
	public String login() {
		return "usuario/login";
	}
	
	// Metodo para acceder sin Spring Security de momento
	@PostMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) {
		logger.info("Accesos: {}", usuario);
		
		// Obteniendo usuario que contenga dicho correo en inicio de sesión
		Optional<Usuario> user = usuarioService.findByCorreo(usuario.getCorreo());
		//logger.info("Usuario de db: {}", user.get());
		
		if (user.isPresent()) {
			// Guardando el id del usuario en esta sesión y ese id poder utilizarlo en el resto de lugares donde se necesite  
			session.setAttribute("idusuario", user.get().getId());
			
			// Validando el tipo de usuario/administrador
			if (user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			} else {
				return "redirect:/";
			}
		} else {
			logger.info("Usuario no existe");
		}
		
		return "redirect:/";
	}
	
	// Método para obtener la información de la compra
	@GetMapping("/compras")
	public String obtenerCompras(Model model,HttpSession session) {
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		// Obteniendo el id del usuario que realiza la orden
		Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		// Definimos una lista para recibir las ordenes hechas por el usuario
		List<Orden> ordenes = ordenService.findByUsuario(usuario);
		
		model.addAttribute("ordenes", ordenes);
		
		return "usuario/compras";
	}
	
	// Método para obtener la información de los detalles de la compra/orden, el id nos indica cuáles detalles pertenecen a una orden
	@GetMapping("/detalle/{id}")
	public String detalleCompr(@PathVariable Integer id, HttpSession session, Model model) {
		logger.info("id de la orden: {}", id);
		
		// Obtener la orden por su id
		Optional<Orden> orden = ordenService.findById(id);
		
		model.addAttribute("detalles", orden.get().getDetalle());
		
		// Session
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		return "usuario/detalleCompra";
	}
}
