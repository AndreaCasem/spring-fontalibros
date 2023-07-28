package com.fontalibros.spring_fontalibros.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.fontalibros.spring_fontalibros.model.Usuario;
import com.fontalibros.spring_fontalibros.service.IDetalleOrdenService;
import com.fontalibros.spring_fontalibros.service.IOrdenService;
import com.fontalibros.spring_fontalibros.service.IUsuarioService;
import com.fontalibros.spring_fontalibros.service.LibroService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IOrdenService ordenService;
	
	@Autowired
	private IDetalleOrdenService detalleOrdenService;
	
	
	// Para almacenar los detalles de la orden, una orden puede tener uno o varios detalles
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
	
	// Se crea este objeto y va a almacenar los datos de la orden
	Orden orden = new Orden();
	
	/*
	@GetMapping("")
	public String home(Model model, HttpSession session) {
		// Ver cuál es el id del usuario que está en una sesion
		log.info("Sesion del usuario: {}", session.getAttribute("idusuario"));
		
		model.addAttribute("libros", libroService.findAll());
		
		// session, indicamos si el usuario inició sesión, así mostramos la vista de usuario logueado
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		return "usuario/home";
	}*/
	
	@GetMapping("")
	public String home(Model model, HttpSession session) {
		
		log.info("Sesion del usuario: {}", session.getAttribute("idusuario"));
		
		model.addAttribute("libros", libroService.findAll());
		
		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));

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
	public String addCart(@RequestParam Integer id , @RequestParam Integer cantidad, Model model) { // Argumentos para recibir el id del libro y la cantidad
		DetalleOrden detalleOrden = new DetalleOrden();
		Libro libro = new Libro();
		double sumaTotal = 0;
		
		Optional<Libro> optionalLibro = libroService.get(id);
		
		// Prueba por consola para buscar el libro y se esté recibiendo la cantidad
		log.info("Libro añadido: {}", optionalLibro.get());
		log.info("cantidad: {}", cantidad);
		libro = optionalLibro.get();
		
		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(libro.getPrecio());
		detalleOrden.setNombre(libro.getTitulo());
		detalleOrden.setTotal(libro.getPrecio() * cantidad);
		detalleOrden.setLibro(libro); // Poner el id del libro
		
		// Validando que un mismo libro no se añada dos veces
		Integer idLibro = libro.getId();
		boolean ingresado = detalles.stream().anyMatch(l -> l.getLibro().getId() == idLibro);
		
		if (! ingresado) {
			detalles.add(detalleOrden); 	// Añadiendo cada detalleOrden que sería cada libro al carrito de compra
		}
		

		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		
		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		return "usuario/carrito";
	}
	
	// Eliminar un libro del carrito de compra
	@GetMapping("/delete/cart/{id}")
	public String deleteBookCart(@PathVariable Integer id, Model model) {
		
		// Lista nueva de libros en el carrito
		List<DetalleOrden> ordenNueva = new ArrayList<DetalleOrden>();
		
		// Eliminar libro de la lista global detalles
		for (DetalleOrden detalleOrden: detalles) {
			if (detalleOrden.getLibro().getId() != id) { // Si se encuentra un id que ya esté en la lista global detalles entonces ese id no lo añade a la orden nueva
				ordenNueva.add(detalleOrden);
			}
		}
		
		// asignamos la nueva lista con los libros restantes
		detalles = ordenNueva;
		
		// Recalculando el total de los libros
		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		
		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		return "usuario/carrito";
	}

	// Accediendo al carrito de compra desde cualquier parte de la página
	@GetMapping("/getCart")
	public String getCart(Model model, HttpSession session) {
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		// Sesion: el carrito de compra se muestra diferente cuando el usuario inicia sesión
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		return "/usuario/carrito";
	}
	
	
	// Metodos para dirigir desde el menu de navegacion a sus respectivas paginas
	@GetMapping("/libro")
	public String showLibro(Model model) {
		model.addAttribute("libros", libroService.findAll());
		return "/usuario/libros";
	}
	
	
	@GetMapping("/comoFunciona")
	public String showComoFunciona(Model model) {
		return "/usuario/comoFunciona";
	}
	
	@GetMapping("/contacto")
	public String showContacto(Model model) {
		return "/usuario/contacto";
	}
	
	
	// Metodo para redirigir la informacion a la vista resumen orden desde carrito de compra
	@GetMapping("/orden")
	public String orden(Model model, HttpSession session) {
		// Obteniendo el usuario para la orden con la sesión que se guardó cuando el usuario se loguea
		Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", usuario);
		
		return "usuario/resumenOrden";
	}
	
	// Metodo para guardar la orden
	@GetMapping("/saveOrder")
	public String saveOrder(HttpSession session) {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(ordenService.generarNumeroOrden());
		
		// Usuario que inició sesión que está haciendo la orden
		Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		orden.setUsuario(usuario);
		ordenService.save(orden);
		
		// Guardando los detalles
		for (DetalleOrden dt:detalles) {
			dt.setOrden(orden);
			detalleOrdenService.save(dt);
		}
		
		// Limpiando lista y orden
		orden = new Orden();
		detalles.clear();
		
		return "usuario/libros";
	}
	
	// Metodo para buscar los libros
	@PostMapping("/buscar")
	public String buscarLibro(@RequestParam String search, Model model) {
		log.info("Nombre del libro: {}", search);
		
		// Convertir la cadena de búsqueda a minúsculas para que no distinga entre mayúsculas y minúsculas
		String searchLowercase = search.toLowerCase();
		
		// Obtenemos una lista de todos los libros y se hace el filtro bien sea por nombre o autor del libro pasando en el contains la variable String search
		List<Libro> libros = libroService.findAll().stream().filter(l -> l.getTitulo().toLowerCase().contains(searchLowercase) || l.getAutor().toLowerCase().contains(searchLowercase)).collect(Collectors.toList());
		
		// Aqui utilizamos la implementacion de la lista de libros utilizada en la pagina de home y libros ${libros}
		model.addAttribute("libros", libros);  
		
		return "usuario/libros";
	}
	
	
	
}
