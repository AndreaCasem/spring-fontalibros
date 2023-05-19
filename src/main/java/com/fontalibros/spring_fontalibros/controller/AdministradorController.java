package com.fontalibros.spring_fontalibros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 Controlador para manejar las solicitudes Get a la ruta base /administrador 
 y mostrar la vista administrador/home
*/

@Controller
@RequestMapping("administrador")
public class AdministradorController {
	
	@GetMapping("")
	public String home() {
		return "administrador/home";
	}
}
