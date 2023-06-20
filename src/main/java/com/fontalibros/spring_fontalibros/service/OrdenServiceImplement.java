package com.fontalibros.spring_fontalibros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fontalibros.spring_fontalibros.model.Orden;
import com.fontalibros.spring_fontalibros.repository.IOrdenRepository;

@Service
public class OrdenServiceImplement implements IOrdenService {
	
	// Para los métodos CRUD
	@Autowired
	private IOrdenRepository ordenRepository;
	
	@Override
	public Orden save(Orden orden) {
		return ordenRepository.save(orden);
	}

}
