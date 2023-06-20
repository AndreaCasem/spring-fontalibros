package com.fontalibros.spring_fontalibros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fontalibros.spring_fontalibros.model.Libro;
import com.fontalibros.spring_fontalibros.repository.ILibroRepository;

/*
 Definimos la implementación del servicio LibroServiceImplement
 que utiliza un repositorio de libros llamado LibroRepository
 para realizar las operaciones de persistencia en la base de datos
*/
@Service
public class LibroServiceImplement implements LibroService {

	/*
	 Spring proporciona automáticamente una instancia de LibroRepository
	 cuando se crea una instancia de LibroServiceImplement.
	*/
	@Autowired
	private ILibroRepository libroRepository;
	
	/*
	 El metodo save utiliza el metodo save de la interfaz LibroRepository 
	 para guardar el libro que se está pasando como argumento en la base
	 de datos
	*/
	@Override
	public Libro save(Libro libro) {
		return libroRepository.save(libro);
	}

	@Override
	public Optional<Libro> get(Integer id) {
		return libroRepository.findById(id);
	}

	@Override
	public void update(Libro libro) {
		libroRepository.save(libro);
		
	}

	@Override
	public void delete(Integer id) {
		libroRepository.deleteById(id);
		
	}

	@Override
	public List<Libro> findAll() {
		return libroRepository.findAll();
	}

	
}
