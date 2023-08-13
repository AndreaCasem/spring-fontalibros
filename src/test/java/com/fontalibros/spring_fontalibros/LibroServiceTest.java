package com.fontalibros.spring_fontalibros;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fontalibros.spring_fontalibros.model.Libro;
import com.fontalibros.spring_fontalibros.repository.ILibroRepository;
import com.fontalibros.spring_fontalibros.service.LibroServiceImplement;

@SpringBootTest
public class LibroServiceTest {
	
	@Mock
	private ILibroRepository libroRepository;
	
	@InjectMocks
	private LibroServiceImplement libroService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveLibro() {
		Libro libro = new Libro(1, "Libro de Prueba", "Autor de Prueba", "Editorial de prueba", "Descripción", "1234567890", null, 10000, 1, null, null);
	
		when(libroRepository.save(any(Libro.class))).thenReturn(libro);
		Libro savedLibro = libroService.save(libro);
		
		Assertions.assertNotNull(savedLibro);
		Assertions.assertEquals(libro.getTitulo(), savedLibro.getTitulo());
		Assertions.assertEquals(libro.getAutor(), savedLibro.getAutor());
		Assertions.assertEquals(libro.getEditorial(), savedLibro.getEditorial());
		Assertions.assertEquals(libro.getDescripcion(), savedLibro.getDescripcion());
		Assertions.assertEquals(libro.getIsbn(), savedLibro.getIsbn());
		Assertions.assertEquals(libro.getImagenes(), savedLibro.getImagenes());
		Assertions.assertEquals(libro.getPrecio(), savedLibro.getPrecio());
		Assertions.assertEquals(libro.getCantidad(), savedLibro.getCantidad());
		Assertions.assertEquals(libro.getCalidad(), savedLibro.getCalidad());
		
	}
	
	@Test
	public void testGetLibro() {
		 int libroId = 1;
	     Libro libro = new Libro(libroId, "Libro de Prueba", "Autor de Prueba", "Editorial de prueba", "Descripción", "1234567890", null, 10000, 1, null, null );

	     when(libroRepository.findById(libroId)).thenReturn(Optional.of(libro));
	     Optional<Libro> recuperarLibro = libroService.get(libroId);
	     
	     Assertions.assertTrue(recuperarLibro.isPresent());
	     Assertions.assertEquals(libro.getTitulo(), recuperarLibro.get().getTitulo());
	     Assertions.assertEquals(libro.getAutor(), recuperarLibro.get().getAutor());
	     Assertions.assertEquals(libro.getEditorial(), recuperarLibro.get().getEditorial());
		 Assertions.assertEquals(libro.getDescripcion(), recuperarLibro.get().getDescripcion());
		 Assertions.assertEquals(libro.getIsbn(), recuperarLibro.get().getIsbn());
		 Assertions.assertEquals(libro.getImagenes(), recuperarLibro.get().getImagenes());
		 Assertions.assertEquals(libro.getPrecio(), recuperarLibro.get().getPrecio());
		 Assertions.assertEquals(libro.getCantidad(), recuperarLibro.get().getCantidad());
		 Assertions.assertEquals(libro.getCalidad(), recuperarLibro.get().getCalidad());   
	     
	}
	
	@Test
	public void testUpdateLibro() {

        Libro libro = new Libro(1, "Libro de Prueba", "Autor de Prueba", "Editorial de prueba", "Descripción", "1234567890", null, 10000, 1, null, null );


        libroService.update(libro);


        verify(libroRepository, times(1)).save(libro);
    }
	
	@Test
	public void testDeleteLibro() {

        int libroId = 1;


        libroService.delete(libroId);


        verify(libroRepository, times(1)).deleteById(libroId);
    }
}
