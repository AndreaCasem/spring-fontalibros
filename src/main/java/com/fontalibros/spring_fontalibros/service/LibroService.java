package com.fontalibros.spring_fontalibros.service;

import java.util.Optional;

import com.fontalibros.spring_fontalibros.model.Libro;

public interface LibroService {
	public Libro save(Libro libro);
	public Optional<Libro> get(Integer id);
	public void update(Libro libro);
	public void delete(Integer id);
}
