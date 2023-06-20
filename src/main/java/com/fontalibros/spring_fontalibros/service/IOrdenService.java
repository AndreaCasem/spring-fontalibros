package com.fontalibros.spring_fontalibros.service;

import com.fontalibros.spring_fontalibros.model.Orden;

public interface IOrdenService {
	// Recibiendo un objeto de tipo orden para hacer la persistencia en la base de datos
	Orden save (Orden orden);
}
