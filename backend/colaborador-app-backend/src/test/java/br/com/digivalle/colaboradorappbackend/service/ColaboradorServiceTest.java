package br.com.digivalle.colaboradorappbackend.service;

import br.com.digivalle.colaboradorappbackend.domain.Colaborador;
import br.com.digivalle.colaboradorappbackend.repository.ColaboradorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ColaboradorServiceTest {

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock
    private PasswordEncryptorService passwordEncryptorService;

    @InjectMocks
    private ColaboradorService colaboradorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarColaborador() {
        Colaborador colaborador = new Colaborador("John Doe", "senha123");
        when(passwordEncryptorService.encryptPassword("senha123")).thenReturn("senhaCriptografada");
        when(PasswordStrengthService.evaluatePasswordStrength("senha123")).thenReturn(new String[]{"80", "Excepcional"});

        when(colaboradorRepository.save(colaborador)).thenReturn(colaborador);

        Colaborador result = colaboradorService.criarColaborador(colaborador);

        assertNotNull(result);
        assertEquals("John Doe", result.getNome());
        assertEquals("senhaCriptografada", result.getSenha());
        assertEquals(80, result.getSenhaScore());
        assertEquals("Excepcional", result.getComplexidade());
    }

    @Test
    public void testAtualizarColaborador() {
        Colaborador colaboradorExistente = new Colaborador("John Doe", "senha123");
        Colaborador colaboradorAtualizado = new Colaborador("Jane Doe", "novaSenha");

        when(colaboradorRepository.findById(1L)).thenReturn(Optional.of(colaboradorExistente));
        when(passwordEncryptorService.encryptPassword("novaSenha")).thenReturn("novaSenhaCriptografada");
        when(colaboradorRepository.save(colaboradorExistente)).thenReturn(colaboradorExistente);

        Colaborador result = colaboradorService.atualizarColaborador(1L, colaboradorAtualizado);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getNome());
        assertEquals("novaSenhaCriptografada", result.getSenha());
    }

    @Test
    public void testDeletarColaborador() {
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.of(new Colaborador()));

        colaboradorService.deletarColaborador(1L);

        verify(colaboradorRepository, times(1)).delete(any());
    }

    @Test
    public void testBuscarColaboradorPorId() {
        Colaborador colaborador = new Colaborador("John Doe", "senha123");
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.of(colaborador));

        Colaborador result = colaboradorService.buscarColaboradorPorId(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getNome());
    }

    @Test
    public void testBuscarTodosColaboradores() {
        List<Colaborador> colaboradores = new ArrayList<>();
        colaboradores.add(new Colaborador("John Doe", "senha123"));
        colaboradores.add(new Colaborador("Jane Doe", "outraSenha"));

        when(colaboradorRepository.findAllColaboradores()).thenReturn(colaboradores);

        List<Colaborador> result = colaboradorService.buscarTodosColaboradores();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
