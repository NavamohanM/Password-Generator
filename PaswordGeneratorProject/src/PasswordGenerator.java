import java.util.*;

public class PasswordGenerator {

    public static String generate(
        int length, boolean upper, boolean lower,
        boolean number, boolean symbol, boolean excludeSimilar
    ) {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()-_=+[]{}|;:,.<>?";
        String similar = "il1Lo0O";

        StringBuilder charPool = new StringBuilder();

        if (upper) charPool.append(upperCase);
        if (lower) charPool.append(lowerCase);
        if (number) charPool.append(numbers);
        if (symbol) charPool.append(symbols);

        if (excludeSimilar) {
            for (int i = 0; i < similar.length(); i++) {
                int index;
                while ((index = charPool.indexOf(similar.substring(i, i + 1))) != -1) {
                    charPool.deleteCharAt(index);
                }
            }
        }

        if (charPool.length() == 0) {
            return "Please select at least one character type!";
        }

        Random rand = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }

        return password.toString();
    }

    public static String generatePronounceable(int length) {
        String[] consonants = { "b", "c", "d", "f", "g", "h", "j", "k", "l", "m",
                                "n", "p", "r", "s", "t", "v", "w", "x", "z" };
        String[] vowels = { "a", "e", "i", "o", "u" };

        Random rand = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length / 2; i++) {
            password.append(consonants[rand.nextInt(consonants.length)]);
            password.append(vowels[rand.nextInt(vowels.length)]);
        }

        if (password.length() > length) {
            return password.substring(0, length);
        }

        return password.toString();
    }
}

