package com.fontalibros.spring_fontalibros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fontalibros.spring_fontalibros.model.Libro;
import com.fontalibros.spring_fontalibros.repository.ILibroRepository;
import com.fontalibros.spring_fontalibros.service.LibroService;
import com.fontalibros.spring_fontalibros.service.LibroServiceImplement;

@SpringBootTest
public class LibroServiceImplementTest {

	// Mock del repositorio
	@Mock
	private ILibroRepository libroRepository;
	
	// clase a probar
	@InjectMocks
	private LibroService libroService = new LibroServiceImplement();
	
	@Test
	public void testSave() {
		
		// Datos de prueba
		Libro libro = new Libro();
		libro.setId(1);
		libro.setTitulo("Libro de prueba");
		libro.setAutor("Autor de prueba");
		libro.setEditorial("Editorial de prueba");
		libro.setDescripcion("Descripción");
		libro.setIsbn("1234567890");
		libro.setImagenes("");
		libro.setPrecio(10000);
		libro.setCantidad(1);
		
		// Configurando el comportamiento del mock
		when(libroRepository.save(any(Libro.class))).thenReturn(libro);
		
		// Ejecutar el método que queremos probar
        Libro result = libroService.save(libro);

        // Verificar que se llamó al método save del repositorio con el libro correcto
        verify(libroRepository, times(1)).save(libro);

        // Verificar que el resultado es el mismo libro devuelto por el repositorio
        assertEquals(libro, result);
	}
}
