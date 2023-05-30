package com.fontalibros.spring_fontalibros.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.fontalibros.spring_fontalibros.model.DetalleOrden;
import com.fontalibros.spring_fontalibros.model.Libro;
import com.fontalibros.spring_fontalibros.model.Orden;
import com.fontalibros.spring_fontalibros.service.LibroService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private LibroService libroService;
	
	// Para almacenar los detalles de la orden, una orden puede tener uno o varios detalles
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
	
	// Se crea este objeto y va a almacenar los datos de la orden
	Orden orden = new Orden();
	
	
	@GetMapping("")
	public String home(Model model) {
		
		model.addAttribute("libros", libroService.findAll());
		return "usuario/home";
	}

	// Metodo para dirigir desde ver libro a la vista libroHome
	@GetMapping("libroHome/{id}")
	public String libroHome(@PathVariable Integer id, Model model) {
		log.info("Id libro enviado como parametro {}", id);
		Libro libro =  new Libro();
		Optional<Libro> libroOptional = libroService.get(id);
		libro = libroOptional.get();
		
		model.addAttribute("libro", libro);
		
		return "usuario/libroHome";
	}
	
	// Metodo para redireccionar al carrito de compra
	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id , @RequestParam Integer cantidad) { // Argumentos para recibir el id del libro y la cantidad
		DetalleOrden detalleOrden = new DetalleOrden();
		Libro libro = new Libro();
		double sumaTotal = 0;
		
		Optional<Libro> optionalLibro = libroService.get(id);
		
		// Prueba por consola para buscar el libro y se esté recibiendo la cantidad
		log.info("Libro añadido: {}", optionalLibro.get());
		log.info("cantidad: {}", cantidad);
		
		return "usuario/carrito";
	}
	
	
}
