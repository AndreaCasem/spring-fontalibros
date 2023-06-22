package com.fontalibros.spring_fontalibros.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fontalibros.spring_fontalibros.model.Usuario;
import java.util.List;


@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
	// Metodo para obtener el inicio de sesión por medio del correo, por ahora
	Optional<Usuario> findByCorreo(String correo);
}
