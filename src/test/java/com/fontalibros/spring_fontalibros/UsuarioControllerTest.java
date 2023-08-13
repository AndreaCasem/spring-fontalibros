package com.fontalibros.spring_fontalibros;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fontalibros.spring_fontalibros.controller.UsuarioController;
import com.fontalibros.spring_fontalibros.model.Usuario;
import com.fontalibros.spring_fontalibros.service.IUsuarioService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@SpringBootTest
public class UsuarioControllerTest {
	@Mock
    private IUsuarioService usuarioService;

    @Mock
    private Model model;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin_ValidUser() {
        // Dado
        HttpSession session = mock(HttpSession.class);
        Usuario usuario = new Usuario();
        usuario.setCorreo("usuario@prueba.com");
        usuario.setPassword("password");
        Optional<Usuario> user = Optional.of(usuario);
        when(usuarioService.findByCorreo("usuario@prueba.com")).thenReturn(user);

        // Cuando
        String result = usuarioController.acceder(usuario, session);

        // Entonces
        verify(usuarioService).findByCorreo("usuario@prueba.com");
        verify(session).setAttribute("idusuario", user.get().getId());
        Assertions.assertEquals("usuario/homein", result);
 
    }

    @Test
    public void testLogin_InvalidUser() {
        // Dado
        HttpSession session = mock(HttpSession.class);
        Usuario usuario = new Usuario();
        usuario.setCorreo("usuario@prueba.com");
        usuario.setPassword("password");
        when(usuarioService.findByCorreo("usuario@prueba.com")).thenReturn(Optional.empty());

        // Cuando
        String result = usuarioController.acceder(usuario, session);

        // Entonces
        verify(usuarioService).findByCorreo("usuario@prueba.com");
        verifyNoMoreInteractions(session);
        Assertions.assertEquals("redirect:/", result);
        
    }

    @Test
    public void testSave() {
        // Dado
        Usuario usuario = new Usuario();
        usuario.setCorreo("usuario@prueba.com");
        usuario.setPassword("password");

        // Cuando
        String result = usuarioController.save(usuario);

        // Entonces
        verify(usuarioService).save(usuario);
        Assertions.assertEquals("redirect:/", result);
    
    }

    @Test
    public void testCerrarSesion() {
        // Dado
        HttpSession session = mock(HttpSession.class);

        // Cuando
        String result = usuarioController.cerrarSesion(session);

        // Entonces
        verify(session).removeAttribute("idusuario");
        Assertions.assertEquals("redirect:/", result);
        
    }
}
