package com.fontalibros.spring_fontalibros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fontalibros.spring_fontalibros.model.Orden;
import com.fontalibros.spring_fontalibros.model.Usuario;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer>{
	// Método para obtener todas las ordenes del usuario logueado para la vista de compras
	List<Orden> findByUsuario (Usuario usuario);
}
