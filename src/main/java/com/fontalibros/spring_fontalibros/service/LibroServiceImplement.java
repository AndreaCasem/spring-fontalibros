package com.fontalibros.spring_fontalibros.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fontalibros.spring_fontalibros.model.Libro;
import com.fontalibros.spring_fontalibros.repository.LibroRepository;

@Service
public class LibroServiceImplement implements LibroService {

	@Autowired
	private LibroRepository libroRepository;
	
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

	
}
