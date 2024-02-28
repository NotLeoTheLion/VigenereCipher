package org.example;

import java.util.Scanner;

public class testkitas {
    public static String encrypt(String plainText, String key) {

        StringBuilder encryptedText = new StringBuilder();
        int keyIndex = 0;
        for (char plainChar : plainText.toCharArray()) {
            char encryptedChar;
            if (Character.isLetter(plainChar)) {
                char base = Character.isUpperCase(plainChar) ? 'A' : 'a';
                int plainIndex = Character.toUpperCase(plainChar) - base;
                char keyChar = key.charAt(keyIndex % key.length());
                encryptedChar = (char) ((plainIndex + (Character.toUpperCase(keyChar) - 'A')) % 26 + base);
                keyIndex++;
            } else {
                encryptedChar = plainChar;
            }
            encryptedText.append(encryptedChar);
        }
        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyIndex = 0;
        for (char encryptedChar : encryptedText.toCharArray()) {
            char decryptedChar;
            if (Character.isLetter(encryptedChar)) {
                char base = Character.isUpperCase(encryptedChar) ? 'A' : 'a';
                int encryptedIndex = Character.toUpperCase(encryptedChar) - base;
                char keyChar = key.charAt(keyIndex % key.length());
                decryptedChar = (char) ((encryptedIndex - (Character.toUpperCase(keyChar) - 'A') + 26) % 26 + base);
                keyIndex++;
            } else {
                decryptedChar = encryptedChar;
            }
            decryptedText.append(decryptedChar);
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the plain text: ");
        String plainText = scanner.nextLine();
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        String encryptedText = encrypt(plainText, key);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}
