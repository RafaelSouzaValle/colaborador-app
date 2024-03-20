package br.com.digivalle.colaboradorappbackend.controller;

import br.com.digivalle.colaboradorappbackend.service.PasswordStrengthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordStrengthController {

    @PostMapping("/validate-password")
    public ResponseEntity<PasswordValidationResult> validatePassword(@RequestBody PasswordRequest request) {
        String[] result = PasswordStrengthService.evaluatePasswordStrength(request.getPassword());
        PasswordValidationResult validationResult = new PasswordValidationResult(result[0], result[1]);
        return new ResponseEntity<>(validationResult, HttpStatus.OK);
    }

    static class PasswordRequest {
        private String password;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    static class PasswordValidationResult {
        private final String strength;
        private final String legend;

        public PasswordValidationResult(String strength, String legend) {
            this.strength = strength;
            this.legend = legend;
        }

        public String getStrength() {
            return strength;
        }

        public String getLegend() {
            return legend;
        }
    }
}
