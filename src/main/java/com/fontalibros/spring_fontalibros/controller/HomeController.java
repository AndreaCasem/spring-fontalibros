package com.fontalibros.spring_fontalibros.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fontalibros.spring_fontalibros.model.Libro;
import com.fontalibros.spring_fontalibros.service.LibroService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private LibroService libroService;
	
	@GetMapping("")
	public String home(Model model) {
		
		model.addAttribute("libros", libroService.findAll());
		return "usuario/home";
	}

	// Metodo para dirigir desde ver producto a la vista libroHome
	@GetMapping("libroHome/{id}")
	public String libroHome(@PathVariable Integer id, Model model) {
		log.info("Id libro enviado como parametro {}", id);
		Libro libro =  new Libro();
		Optional<Libro> libroOptional = libroService.get(id);
		libro = libroOptional.get();
		
		model.addAttribute("libro", libro);
		
		return "usuario/libroHome";
	}
}
