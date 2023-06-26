package com.fontalibros.spring_fontalibros.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fontalibros.spring_fontalibros.model.Orden;
import com.fontalibros.spring_fontalibros.model.Usuario;
import com.fontalibros.spring_fontalibros.repository.IOrdenRepository;

// Clase de servicio para la orden
@Service
public class OrdenServiceImplement implements IOrdenService {
	
	// Para los métodos CRUD
	@Autowired
	private IOrdenRepository ordenRepository;
	
	@Override
	public Orden save(Orden orden) {
		return ordenRepository.save(orden);
	}

	// Metodo para obtener todas las ordenes y nos permite mirar cuál es el ultimo numero secuencial de la orden
	@Override
	public List<Orden> findAll() {
		return ordenRepository.findAll();
	}
	
	// Metodo para generar el número de orden
	public String generarNumeroOrden() {
		int numero = 0;
		String numeroConcatenado = "";
		
		// Obtener todas las ordenes y el ultimo numero ingresado de esa orden
		List<Orden> ordenes = findAll();
		
		List<Integer> numeros = new ArrayList<Integer>();
		
		ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero()))); // Convirtiendo el numero que viene como String de la bd a entero
		
		if (ordenes.isEmpty()) {
			numero = 1;
		} else {
			numero = numeros.stream().max(Integer::compare).get(); // Obtiene el numero mayor de toda la lista de numeros de ordenes
			numero++;
		}
		
		/*
		Formato para el numero de orden:
		Cuando es menor a 10 se antepone los 9 ceros y el numero de orden, Ej: 0000000001, 0000000002, ... 0000000009
		Cuando es mayor a 9 se quita un cero y se pone el numero 10, Ej: 0000000010, 0000000011...
		Cuando se llegue a 100 se quitan dos ceros, Ej: 0000000100, 00000000101...
		Y así sucesivamente.
		 */
		if (numero < 10) {
			numeroConcatenado = "000000000" + String.valueOf(numero);
		} else if (numero < 100) {
			numeroConcatenado = "00000000" + String.valueOf(numero);
		} else if (numero < 1000) {
			numeroConcatenado = "000000" + String.valueOf(numero);
		} else if (numero < 10000) {
			numeroConcatenado = "00000" + String.valueOf(numero);
		}
		
		return numeroConcatenado; // Nos devuelve el String con el secuencial del numero de la orden
		
	}

	// Obteniendo la lista de ordenes realizadas por el usuario logueado para la vista de compras
	@Override
	public List<Orden> findByUsuario(Usuario usuario) {
		return ordenRepository.findByUsuario(usuario);
	}

}
