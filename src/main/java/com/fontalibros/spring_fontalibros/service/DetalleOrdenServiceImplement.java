package com.fontalibros.spring_fontalibros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fontalibros.spring_fontalibros.model.DetalleOrden;
import com.fontalibros.spring_fontalibros.repository.IDetalleOrdenRepository;

// Clase de servicio para el detalle de la orden
@Service
public class DetalleOrdenServiceImplement implements IDetalleOrdenService {

	@Autowired
	private IDetalleOrdenRepository detalleOrdenRepository;
	
	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden);
	}

}
