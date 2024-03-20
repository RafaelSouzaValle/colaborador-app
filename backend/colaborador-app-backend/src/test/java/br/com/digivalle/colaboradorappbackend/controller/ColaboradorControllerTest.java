package br.com.digivalle.colaboradorappbackend.controller;

import br.com.digivalle.colaboradorappbackend.domain.Colaborador;
import br.com.digivalle.colaboradorappbackend.service.ColaboradorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ColaboradorControllerTest {

    @Mock
    private ColaboradorService colaboradorService;

    @InjectMocks
    private ColaboradorController colaboradorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarColaborador() {
        Colaborador colaborador = new Colaborador("John Doe", "senha123");
        when(colaboradorService.criarColaborador(colaborador)).thenReturn(colaborador);

        ResponseEntity<Colaborador> response = colaboradorController.criarColaborador(colaborador);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getNome());
        assertEquals("senha123", response.getBody().getSenha());
    }

    @Test
    public void testAtualizarColaborador() {
        Colaborador colaborador = new Colaborador("Jane Doe", "novaSenha");
        when(colaboradorService.atualizarColaborador(1L, colaborador)).thenReturn(colaborador);

        ResponseEntity<Colaborador> response = colaboradorController.atualizarColaborador(1L, colaborador);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().getNome());
        assertEquals("novaSenha", response.getBody().getSenha());
    }

    @Test
    public void testDeletarColaborador() {
        ResponseEntity<Void> response = colaboradorController.deletarColaborador(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testBuscarColaboradorPorId() {
        Colaborador colaborador = new Colaborador("John Doe", "senha123");
        when(colaboradorService.buscarColaboradorPorId(1L)).thenReturn(colaborador);

        ResponseEntity<Colaborador> response = colaboradorController.buscarColaboradorPorId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getNome());
        assertEquals("senha123", response.getBody().getSenha());
    }

    @Test
    public void testBuscarTodosColaboradores() {
        List<Colaborador> colaboradores = new ArrayList<>();
        colaboradores.add(new Colaborador("John Doe", "senha123"));
        colaboradores.add(new Colaborador("Jane Doe", "outraSenha"));
        when(colaboradorService.buscarTodosColaboradores()).thenReturn(colaboradores);

        ResponseEntity<List<Colaborador>> response = colaboradorController.buscarTodosColaboradores();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }
}
