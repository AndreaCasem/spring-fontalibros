package com.fontalibros.spring_fontalibros.service;

import java.util.Optional;

import com.fontalibros.spring_fontalibros.model.Usuario;

public interface IUsuarioService {
	Optional<Usuario> findById(Integer id);
	
}
