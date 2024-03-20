package br.com.digivalle.colaboradorappbackend.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordStrengthServiceTest {

    @Test
    public void testEvaluatePasswordStrength() {
        String[] result = PasswordStrengthService.evaluatePasswordStrength("Password123!");

        assertNotNull(result);
        assertEquals(2, result.length);

        int strength = Integer.parseInt(result[0]);
        assertTrue(strength >= 0 && strength <= 100);

        assertNotNull(result[1]);
    }
}

