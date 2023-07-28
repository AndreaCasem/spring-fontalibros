package com.fontalibros.spring_fontalibros;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fontalibros.spring_fontalibros.model.Usuario;
import com.fontalibros.spring_fontalibros.repository.IUsuarioRepository;
import com.fontalibros.spring_fontalibros.service.UsuarioServiceImplement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class UsuarioServiceImplementTest {

	@Autowired
	private UsuarioServiceImplement usuarioService;
	
	@MockBean
	private IUsuarioRepository usuarioRepository;
	
	// Ejemplos para las pruebas
	private Usuario usuario1;
	private Usuario usuario2;
	
	@BeforeEach
	void setUp() {
		usuario1 = new Usuario(1, "Usuario1", "Apellido1", "12345678", "usuario1@example.com",
				"Direccion1", "1234567890", "password1", "usuario");
		usuario2 = new Usuario(2, "Usuario2", "Apellido2", "87654321", "usuario2@example.com",
				"Direccion2", "0987654321", "password2", "usuario");
	}
	
	@Test
	void finById_deberiaRetornarUsuarioExistente() {
		// Arrange: Preparar datos de prueba
		int userId = 1;
		when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario1));
	
		// Act: Ejecutando el método findById
		Optional<Usuario> resultado = usuarioService.findById(userId);
		
		// Assert: Verificar el resultado
		assertTrue(resultado.isPresent());
		assertEquals(usuario1, resultado.get());
	}
	
	@Test
	void findById_deberiaRetornarVacioParaUsuarioInexistente() {
		// Arrange: Preparar datos de prueba
		int userId = 3;
		
		// Act: Ejecutando módulo
		Optional<Usuario> resultado = usuarioService.findById(userId);
		
		// Assert: Verificar el resultado
		assertFalse(resultado.isPresent());
	}
	
}
