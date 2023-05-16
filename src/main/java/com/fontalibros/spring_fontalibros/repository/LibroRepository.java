package com.fontalibros.spring_fontalibros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fontalibros.spring_fontalibros.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer>{

}
