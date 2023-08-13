package com.fontalibros.spring_fontalibros;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.fontalibros.spring_fontalibros.controller.HomeController;
import com.fontalibros.spring_fontalibros.model.DetalleOrden;
import com.fontalibros.spring_fontalibros.model.Libro;
import com.fontalibros.spring_fontalibros.model.Orden;
import com.fontalibros.spring_fontalibros.model.Usuario;
import com.fontalibros.spring_fontalibros.service.IDetalleOrdenService;
import com.fontalibros.spring_fontalibros.service.IOrdenService;
import com.fontalibros.spring_fontalibros.service.IUsuarioService;
import com.fontalibros.spring_fontalibros.service.LibroService;

import jakarta.servlet.http.HttpSession;



@SpringBootTest
public class HomeControllerTest {
	
	@Mock
    private LibroService libroService;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private IOrdenService ordenService;

    @Mock
    private IDetalleOrdenService detalleOrdenService;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCart() {
        // Given
        int libroId = 1;
        int cantidad = 3;
        Libro libro = new Libro(libroId, "Libro de Prueba", "Autor de Prueba", "Editorial de prueba", "Descripción", "1234567890", null, 10000, 1, null, null);
        Optional<Libro> optionalLibro = Optional.of(libro);
        when(libroService.get(libroId)).thenReturn(optionalLibro);

        // When
        String result = homeController.addCart(libroId, cantidad, model);

        // Then
        verify(libroService, times(1)).get(libroId);
        verify(model, times(2)).addAttribute(any(String.class), any());
 
        Assertions.assertEquals("usuario/carrito", result);
    }
    
    @Test
    public void testBuscarLibro() {
    	// Dado
        String search = "prueba";
        // String searchLowercase = search.toLowerCase();
        Libro libro1 = new Libro(1, "Libro de Prueba", "Autor de Prueba", "Editorial de prueba", "Descripción", "1234567890", null, 10000, 1, null, null);
        Libro libro2 = new Libro(2, "Libro de Prueba", "Autor de Prueba", "Editorial de prueba", "Descripción", "1234567890", null, 20000, 1, null, null);
        List<Libro> libros = new ArrayList<>();
        libros.add(libro1);
        libros.add(libro2);

        when(libroService.findAll()).thenReturn(libros);

        // Cuando
        String result = homeController.buscarLibro(search, model);

        // Entonces
        Assertions.assertEquals("usuario/libros", result);
        verify(libroService).findAll();
        verify(model).addAttribute("libros", libros);
    }
    
    @Test
    public void testSaveOrder() {
    	// Dado
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("idusuario")).thenReturn("1");
        
        int libroId = 1;
        int cantidad = 3;
        double precio = 100.0;
        Libro libro = new Libro(libroId, "Libro de Prueba", "Autor de Prueba", "Editorial de prueba", "Descripción", "1234567890", null, 10000, 1, null, null);
        Optional<Libro> optionalLibro = Optional.of(libro);
        when(libroService.get(libroId)).thenReturn(optionalLibro);
        
        List<DetalleOrden> detallesOrden = new ArrayList<>();
        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(precio);
        detalleOrden.setNombre(libro.getTitulo());
        detalleOrden.setTotal(precio * cantidad);
        detalleOrden.setLibro(libro);
        detallesOrden.add(detalleOrden);
        
        Orden orden = new Orden();
        orden.setFechaCreacion(new Date());
        //orden.setNumero(1);
        Usuario usuario = new Usuario(1, "Usuario1", "Apellido1", "12345678", "usuario1@example.com",
				"Direccion1", "1234567890", "password1", "usuario");
        orden.setUsuario(usuario);
        when(usuarioService.findById(1)).thenReturn(Optional.of(usuario));
        //when(ordenService.generarNumeroOrden()).thenReturn(1);
        when(ordenService.save(orden)).thenReturn(orden);
        
        // Cuando
        String result = homeController.saveOrder(session);

        // Entonces
        verify(session).getAttribute("idusuario");
        verify(libroService).get(libroId);
        verify(usuarioService).findById(1);
        verify(ordenService).generarNumeroOrden();
        verify(ordenService).save(orden);
        verify(detalleOrdenService, times(1)).save(detalleOrden);
        Assertions.assertEquals("usuario/libros", result);
    }
    
    @Test
    public void testSaveOrder_NoUserInSession() {
        // Dado
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("idusuario")).thenReturn(null);

        // Cuando
        String result = homeController.saveOrder(session);

        // Entonces
        verify(session).getAttribute("idusuario");
        verifyNoInteractions(libroService, usuarioService, ordenService, detalleOrdenService);
        Assertions.assertEquals("usuario/libros", result);
    }
}
