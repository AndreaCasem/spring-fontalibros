package com.fontalibros.spring_fontalibros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fontalibros.spring_fontalibros.model.Libro;
import com.fontalibros.spring_fontalibros.service.IOrdenService;
import com.fontalibros.spring_fontalibros.service.IUsuarioService;
import com.fontalibros.spring_fontalibros.service.LibroService;

/*
 Controlador para manejar las solicitudes Get a la ruta base /administrador 
 y mostrar la vista administrador/home
*/

@Controller
@RequestMapping("administrador")
public class AdministradorController {
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IOrdenService ordenService;
	
	@GetMapping("")
	public String home(Model model) {
		
		List<Libro> libros = libroService.findAll();
		model.addAttribute("libros", libros);
		
		return "administrador/home";
	}
	
	// Metodo para obtener la lista de usuarios
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("usuarios", usuarioService.findAll());
		return "administrador/usuarios";
	}
	
	// Metodo para obtener una lista con las ordenes
	@GetMapping("/ordenes")
	public String ordenes(Model model) {
		model.addAttribute("ordenes", ordenService.findAll());
		return "administrador/ordenes";
	}
}
