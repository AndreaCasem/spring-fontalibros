package com.fontalibros.spring_fontalibros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libros")
public class LibroController {
	
	@GetMapping("")
	public String show() {
		return "libros/show";
	}
}
