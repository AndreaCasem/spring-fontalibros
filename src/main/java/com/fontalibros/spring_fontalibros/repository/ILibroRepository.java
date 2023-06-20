package com.fontalibros.spring_fontalibros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fontalibros.spring_fontalibros.model.Libro;

/*
 Se define la interfaz LibroRepository que hereda metodos
 para realizar operaciones CRUD en la entidad Libro y utiliza
 un identificador de tipo Integer
*/
@Repository
public interface ILibroRepository extends JpaRepository<Libro, Integer>{

}
