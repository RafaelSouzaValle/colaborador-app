package br.com.digivalle.colaboradorappbackend.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordStrengthService {

    public static String[] evaluatePasswordStrength(String password) {
        int strength = 0;
        int length = password.length();

        strength += calculateFlatBonus(length, 4);
        strength += calculateIncrementalBonus(length, countUppercaseLetters(password), 2);
        strength += calculateIncrementalBonus(length, countLowercaseLetters(password), 2);
        strength += calculateConditionalBonus(countNumbers(password), 4);
        strength += calculateFlatBonus(countSymbols(password), 6);
        strength += calculateFlatBonus(countMiddleNumbersOrSymbols(password), 2);

        int requirements = calculateRequirements(length, password);
        strength += calculateFlatBonus(requirements, 2);

        strength -= calculateDeductions(length, password);

        strength = Math.max(0, Math.min(strength, 100));

        String legend;
        if (strength >= 80) {
            legend = "Excepcional - Excede os padrões mínimos. Bônus adicionais são aplicados.";
        } else if (strength >= 60) {
            legend = "Suficiente - Atende aos padrões mínimos. Bônus adicionais são aplicados.";
        } else if (strength >= 40) {
            legend = "Aviso - Advertência contra o uso de práticas inadequadas. A pontuação geral é reduzida.";
        } else {
            legend = "Falha - Não atende aos padrões mínimos. A pontuação geral é reduzida.";
        }

        return new String[] {String.valueOf(strength), legend};
    }

    private static int calculateFlatBonus(int n, int rate) {
        return n * rate;
    }

    private static int calculateIncrementalBonus(int len, int n, int rate) {
        return (len - n) * rate;
    }

    private static int calculateConditionalBonus(int n, int rate) {
        return n > 0 ? n * rate : 0;
    }

    private static int countUppercaseLetters(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                count++;
            }
        }
        return count;
    }

    private static int countLowercaseLetters(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                count++;
            }
        }
        return count;
    }

    private static int countNumbers(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }

    private static int countSymbols(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                count++;
            }
        }
        return count;
    }

    private static int countMiddleNumbersOrSymbols(String password) {
        int count = 0;
        String middle = password.substring(1, password.length() - 1);
        for (char c : middle.toCharArray()) {
            if (Character.isDigit(c) || !Character.isLetterOrDigit(c)) {
                count++;
            }
        }
        return count;
    }

    private static int calculateRequirements(int length, String password) {
        int requirements = 0;
        if (length >= 8) {
            if (countUppercaseLetters(password) > 0) {
                requirements++;
            }
            if (countLowercaseLetters(password) > 0) {
                requirements++;
            }
            if (countNumbers(password) > 0) {
                requirements++;
            }
            if (countSymbols(password) > 0) {
                requirements++;
            }
        }
        return requirements;
    }

    private static int calculateDeductions(int length, String password) {
        int deductions = 0;
        deductions += (length == countLetters(password)) ? length : 0;
        deductions += (length == countNumbers(password)) ? length : 0;
        deductions += countRepeatedCharacters(password);
        deductions += countConsecutiveUppercaseLetters(password) * 2;
        deductions += countConsecutiveLowercaseLetters(password) * 2;
        deductions += countConsecutiveNumbers(password) * 2;
        deductions += countSequentialLetters(password) * 3;
        deductions += countSequentialNumbers(password) * 3;
        deductions += countSequentialSymbols(password) * 3;
        return deductions;
    }

    private static int countLetters(String password) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                count++;
            }
        }
        return count;
    }

    private static int countRepeatedCharacters(String password) {
        int count = 0;
        String lowerCasePassword = password.toLowerCase();
        for (int i = 0; i < password.length(); i++) {
            char c = lowerCasePassword.charAt(i);
            if (i != lowerCasePassword.lastIndexOf(c)) {
                count++;
            }
        }
        return count;
    }

    private static int countConsecutiveUppercaseLetters(String password) {
        int count = 0;
        for (int i = 0; i < password.length() - 1; i++) {
            if (Character.isUpperCase(password.charAt(i)) && Character.isUpperCase(password.charAt(i + 1))) {
                count++;
            }
        }
        return count;
    }

    private static int countConsecutiveLowercaseLetters(String password) {
        int count = 0;
        for (int i = 0; i < password.length() - 1; i++) {
            if (Character.isLowerCase(password.charAt(i)) && Character.isLowerCase(password.charAt(i + 1))) {
                count++;
            }
        }
        return count;
    }

    private static int countConsecutiveNumbers(String password) {
        int count = 0;
        for (int i = 0; i < password.length() - 1; i++) {
            if (Character.isDigit(password.charAt(i)) && Character.isDigit(password.charAt(i + 1))) {
                count++;
            }
        }
        return count;
    }

    private static int countSequentialLetters(String password) {
        int count = 0;
        for (int i = 0; i < password.length() - 2; i++) {
            if (Character.isLetter(password.charAt(i)) &&
                    Character.isLetter(password.charAt(i + 1)) &&
                    Character.isLetter(password.charAt(i + 2))) {
                count++;
            }
        }
        return count;
    }

    private static int countSequentialNumbers(String password) {
        int count = 0;
        for (int i = 0; i < password.length() - 2; i++) {
            if (Character.isDigit(password.charAt(i)) &&
                    Character.isDigit(password.charAt(i + 1)) &&
                    Character.isDigit(password.charAt(i + 2))) {
                count++;
            }
        }
        return count;
    }

    private static int countSequentialSymbols(String password) {
        int count = 0;
        for (int i = 0; i < password.length() - 2; i++) {
            if (!Character.isLetterOrDigit(password.charAt(i)) &&
                    !Character.isLetterOrDigit(password.charAt(i + 1)) &&
                    !Character.isLetterOrDigit(password.charAt(i + 2))) {
                count++;
            }
        }
        return count;
    }
}
