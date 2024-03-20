package br.com.digivalle.colaboradorappbackend.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColaboradorTest {

    @Test
    public void testEquals() {
        Colaborador colaborador1 = new Colaborador();
        colaborador1.setId(1L);
        Colaborador colaborador2 = new Colaborador();
        colaborador2.setId(1L);
        Colaborador colaborador3 = new Colaborador();
        colaborador3.setId(2L);

        assertEquals(colaborador1, colaborador2);
        assertNotEquals(colaborador1, colaborador3);
    }

    @Test
    public void testHashCode() {
        Colaborador colaborador1 = new Colaborador();
        colaborador1.setId(1L);
        Colaborador colaborador2 = new Colaborador();
        colaborador2.setId(1L);
        Colaborador colaborador3 = new Colaborador();
        colaborador3.setId(2L);

        assertEquals(colaborador1.hashCode(), colaborador2.hashCode());
        assertNotEquals(colaborador1.hashCode(), colaborador3.hashCode());
    }
}

