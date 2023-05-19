package com.fontalibros.spring_fontalibros.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fontalibros.spring_fontalibros.model.Libro;
import com.fontalibros.spring_fontalibros.model.Usuario;
import com.fontalibros.spring_fontalibros.service.LibroService;



// Controlador para la gestion de libros
@Controller
@RequestMapping("/libros")
public class LibroController {
	
	/*
	  Se crea un objeto de registro de eventos privado y no modificable que solo puede ser
	  accedida desde esta clase, llamado LOGGER, este registrará eventos importantes en cuanto a la ejecucion de un
	  metodo o la aparicion de un error. 
	*/
	private final Logger LOGGER = LoggerFactory.getLogger(LibroController.class);
	
	
	@Autowired
	private LibroService libroService;
	
	
	// Recibe la solicitud Get a la ruta base /libros y muestra la vista libros/show
	// Pasamos como parametro un objeto de tipo model que nos va a llevar informacion del backend a la vista
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("libros", libroService.findAll());
		return "libros/show"; 
	}
	
	@GetMapping("/crear")
	public String crear() {
		return "libros/crear";
	}
	
	// Hacemos una peticion directamente al controlador libros para que nos cargue la vista show
	@PostMapping("/save")
	public String save(Libro libro) {
		LOGGER.info("este es el objeto libro de la vista {}",libro);
		Usuario u = new Usuario(1, "", "", "", "", "", "", "", "");
		libro.setUsuario(u);
		
		libroService.save(libro);
		return "redirect:/libros";
	}
}