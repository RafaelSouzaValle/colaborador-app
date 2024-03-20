package br.com.digivalle.colaboradorappbackend.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordEncryptorServiceTest {

    @Test
    public void testEncryptPassword() {
        String password = "password123";
        String encryptedPassword = PasswordEncryptorService.encryptPassword(password);

        assertNotNull(encryptedPassword);
        assertNotEquals(password, encryptedPassword);
    }

    @Test
    public void testEncryptEmptyPassword() {
        String password = "";
        String encryptedPassword = PasswordEncryptorService.encryptPassword(password);

        assertNotNull(encryptedPassword);
        assertEquals(64, encryptedPassword.length()); // SHA-256 produces a 64-character hash
    }

    @Test
    public void testEncryptNullPassword() {
        String password = null;
        String encryptedPassword = PasswordEncryptorService.encryptPassword(password);

        assertNull(encryptedPassword);
    }
}

