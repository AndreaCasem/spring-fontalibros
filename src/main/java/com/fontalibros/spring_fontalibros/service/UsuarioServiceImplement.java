package com.fontalibros.spring_fontalibros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fontalibros.spring_fontalibros.model.Usuario;
import com.fontalibros.spring_fontalibros.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> findById(Integer id) {
		return usuarioRepository.findById(id);
	}

	// Guardando el objeto usuario
	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	// Obtener usuario por medio del correo
	@Override
	public Optional<Usuario> findByCorreo(String correo) {
		return usuarioRepository.findByCorreo(correo);
	}

	// Obtener todos los usuarios registrados
	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	

}
