package org.example;

import java.util.Scanner;

public class VignereSifravimas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pasirinkite:\n1. Užšifravimas\n2. Dešifravimas");
        int choice = scanner.nextInt();

        System.out.println("Pasirinkite šifravimo/dešifravimo būdą:\n1. Pagal abėcėlę\n2. Pagal ASCII lentelę");
        int metodas = scanner.nextInt();
        System.out.println("Įveskite tekstą:");
        String tekstas = scanner.next().toUpperCase();
        System.out.println("Įveskite raktą:");
        String raktas = scanner.next().toUpperCase();

        String result = "";
        if (choice == 1) {
            result = (metodas == 1) ? uzsifruotiabc(tekstas, raktas) : uzsifruotiAscii(tekstas, raktas);
        } else if (choice == 2) {
            result = (metodas == 1) ? desifruotiabc(tekstas, raktas) : desifruotiAscii(tekstas, raktas);
        } else {
            System.out.println("Negalimas pasirinkimas!");
            System.exit(0);
        }

        System.out.println((choice == 1 ? "Užšifruotas" : "Dešifruotas") + " tekstas: " + result);
        scanner.close();
    }

    private static String uzsifruotiabc(String tekstas, String raktas) {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZĄČĘĖĮŠŲŪŽ0123456789";
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < tekstas.length(); i++) {
            char currentChar = tekstas.charAt(i);
            int charIndex = abc.indexOf(currentChar);
            if (charIndex != -1) {
                int keyIndex = abc.indexOf(raktas.charAt(i % raktas.length()));
                int newIndex = (charIndex + keyIndex) % abc.length();
                encrypted.append(abc.charAt(newIndex));
            } else {
                encrypted.append(currentChar);
            }
        }
        return encrypted.toString();
    }

    private static String desifruotiabc(String tekstas, String raktas) {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZĄČĘĖĮŠŲŪŽ0123456789";
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < tekstas.length(); i++) {
            char currentChar = tekstas.charAt(i);
            int charIndex = abc.indexOf(currentChar);
            if (charIndex != -1) {
                int keyIndex = abc.indexOf(raktas.charAt(i % raktas.length()));
                int newIndex = (charIndex - keyIndex + abc.length()) % abc.length();
                decrypted.append(abc.charAt(newIndex));
            } else {
                decrypted.append(currentChar);
            }
        }
        return decrypted.toString();
    }
    private static String uzsifruotiAscii(String tekstas, String raktas) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < tekstas.length(); i++) {
            char letter = tekstas.charAt(i);
            char keyChar = raktas.charAt(i % raktas.length());

            if (keyChar >= 32 && keyChar <= 127) {
                keyChar -= 32;
            }

            if (letter >= 32 && letter <= 127) {
                int shifted = ((letter - 32) + (keyChar - 32)) % (127 - 32 + 1) + 32;
                encrypted.append((char) shifted);
            } else {
                encrypted.append(letter);
            }
        }
        return encrypted.toString();
    }
    private static String desifruotiAscii(String tekstas, String raktas) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < tekstas.length(); i++) {
            char letter = tekstas.charAt(i);
            char keyChar = raktas.charAt(i % raktas.length());

            if (letter >= 32 && letter <= 127) {
                int shifted = (letter - keyChar);
                if (shifted < 32) {
                    shifted += (127 - 32 + 1);
                }
                decrypted.append((char) shifted);
            } else {
                decrypted.append(letter);
            }
        }
        return decrypted.toString();
    }
}