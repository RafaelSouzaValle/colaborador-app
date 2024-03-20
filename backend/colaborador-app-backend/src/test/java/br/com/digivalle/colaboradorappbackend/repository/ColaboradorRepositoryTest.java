package br.com.digivalle.colaboradorappbackend.repository;

import br.com.digivalle.colaboradorappbackend.domain.Colaborador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ColaboradorRepositoryTest {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Test
    public void testCrudOperations() {
        colaboradorRepository.criarColaborador("John Doe", "DIRETOR", "senha123", 10, "complexa");

        List<Colaborador> colaboradores = colaboradorRepository.findAll();
        assertFalse(colaboradores.isEmpty());

        Colaborador colaborador = colaboradores.get(0);
        colaborador.setNome("Jane Doe");
        colaboradorRepository.save(colaborador);

        Colaborador updatedColaborador = colaboradorRepository.findById(colaborador.getId()).orElse(null);
        assertNotNull(updatedColaborador);
        assertEquals("Jane Doe", updatedColaborador.getNome());

        colaboradorRepository.delete(colaborador);

        assertFalse(colaboradorRepository.existsById(colaborador.getId()));
    }
}

