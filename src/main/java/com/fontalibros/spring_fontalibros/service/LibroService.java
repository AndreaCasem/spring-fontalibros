package com.fontalibros.spring_fontalibros.service;

import java.util.List;
import java.util.Optional;

import com.fontalibros.spring_fontalibros.model.Libro;

/*
 Se define la interfaz LibroService que especifica los metodos 
 para realizar operaciones CRUD en la entidad Libro.
 La implementacion de estos metodos se realiza en la clase de servicio
 LibroServicioImplement,la cual interactua con la capa de persistencia.
*/
public interface LibroService {
	public Libro save(Libro libro);
	public Optional<Libro> get(Integer id);
	public void update(Libro libro);
	public void delete(Integer id);
	public List<Libro> findAll();
}
