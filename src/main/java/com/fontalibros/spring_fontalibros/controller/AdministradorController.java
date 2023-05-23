package com.fontalibros.spring_fontalibros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fontalibros.spring_fontalibros.model.Libro;
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
	
	@GetMapping("")
	public String home(Model model) {
		
		List<Libro> libros = libroService.findAll();
		model.addAttribute("libros", libros);
		
		return "administrador/home";
	}
}
