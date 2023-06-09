package com.fontalibros.spring_fontalibros.controller;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fontalibros.spring_fontalibros.model.Libro;
import com.fontalibros.spring_fontalibros.model.Usuario;
import com.fontalibros.spring_fontalibros.service.IUsuarioService;
import com.fontalibros.spring_fontalibros.service.LibroService;

import jakarta.servlet.http.HttpSession;



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
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
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
	public String save(Libro libro, HttpSession session) {
		LOGGER.info("este es el objeto libro de la vista {}",libro);
		
		// Cuando el administrador inicie sesión 
		Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString() )).get();
		
		libro.setUsuario(u);
		libroService.save(libro);
		return "redirect:/libros";
	}
	
	// Metodo para editar que busque el registro con el id que le pasemos
	// La anotacion @PathVariable mapea la variable que le pasamos, en este caso el id y la pasamos al Integer id
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		Libro libro = new Libro();
		Optional<Libro> optionalLibro = libroService.get(id);
		libro = optionalLibro.get();
		
		LOGGER.info("Libro buscado: {}", libro);
		model.addAttribute("libro", libro);
		
		return "libros/editar";
	}
	
	// Metodo para actualizar los datos del libro
	
	@PostMapping("/actualizar")
	public String actualizar(Libro libro) {
		
		// Obtener el usuario del libro cuando se edita el libro
		Libro l = new Libro();
		l = libroService.get(libro.getId()).get();
		
		libro.setUsuario(l.getUsuario());
		libroService.update(libro);
		return "redirect:/libros";
	}
	
	// Metodo para eliminar un libro
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {
		libroService.delete(id);
		return "redirect:/libros";
	}
}
