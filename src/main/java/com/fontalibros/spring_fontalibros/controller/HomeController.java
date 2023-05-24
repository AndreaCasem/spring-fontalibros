package com.fontalibros.spring_fontalibros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fontalibros.spring_fontalibros.service.LibroService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private LibroService libroService;
	
	@GetMapping("")
	public String home(Model model) {
		
		model.addAttribute("libros", libroService.findAll());
		return "usuario/home";
	}

}
