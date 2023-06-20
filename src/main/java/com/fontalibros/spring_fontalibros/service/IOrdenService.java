package com.fontalibros.spring_fontalibros.service;

import java.util.List;

import com.fontalibros.spring_fontalibros.model.Orden;

public interface IOrdenService {
	// Obteniendo la lista de ordenes
	List<Orden> findAll();
	
	// Recibiendo un objeto de tipo orden para hacer la persistencia en la base de datos
	Orden save (Orden orden);
}
