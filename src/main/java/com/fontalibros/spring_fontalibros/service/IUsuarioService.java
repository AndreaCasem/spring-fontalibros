package com.fontalibros.spring_fontalibros.service;

import java.util.Optional;

import com.fontalibros.spring_fontalibros.model.Usuario;

public interface IUsuarioService {
	Optional<Usuario> findById(Integer id);
	// Guardar usuario
	Usuario save (Usuario usuario);
	
	// Metodo para filtrar el correo para iniciar sesión
	Optional<Usuario> findByCorreo(String correo);
	
}
