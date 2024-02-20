package org.example;

import java.util.Scanner;

public class VigenereCipher {
    private static final String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZĄČĘĖĮŠŲŪŽ0123456789";

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pasirinkite algoritma:\n1. Uzsifravimas\n2. Desifravimas");
        int pasirinkimas = scanner.nextInt();

        System.out.println("Pasirinkite kaip norite atlikti algoritma:\n1. Naudojant abecele.\n2. Naudojant ASCII.");
        int algoritmas = scanner.nextInt();
        System.out.println("Iveskite teksta:");
        String zinute = scanner.next().toUpperCase();
        System.out.println("Iveskite rakta:");
        String raktas = scanner.next().toUpperCase();

        String result = "";
        if (pasirinkimas == 1) {
            result = (algoritmas == 1) ? uzsifruotiabc(zinute, raktas) : uzsifruotiAscii(zinute, raktas);
        } else if (pasirinkimas == 2) {
            result = (algoritmas == 1) ? desifruotiabc(zinute, raktas) : desifruotiAscii(zinute, raktas);
        } else {
            System.out.println("Neleistinas pasirinkimas!");
            System.exit(0);
        }

        System.out.println((pasirinkimas == 1 ? "Uzsifruotas" : "Desifruotas") + " tekstas: " + result);
        scanner.close();
    }

    private static String uzsifruotiabc(String zinute, String raktas) {
        StringBuilder uzsifruotas = new StringBuilder();
        for (int i = 0; i < zinute.length(); i++) {
            int charIndex = abc.indexOf(zinute.charAt(i));
            int keyIndex = abc.indexOf(raktas.charAt(i % raktas.length()));
            int newIndex = (charIndex + keyIndex) % abc.length();
            uzsifruotas.append(abc.charAt(newIndex));
        }
        return uzsifruotas.toString();
    }

    private static String desifruotiabc(String zinute, String raktas) {
        StringBuilder desifruotas = new StringBuilder();
        for (int i = 0; i < zinute.length(); i++) {
            int charIndex = abc.indexOf(zinute.charAt(i));
            int keyIndex = abc.indexOf(raktas.charAt(i % raktas.length()));
            int newIndex = (charIndex - keyIndex + abc.length()) % abc.length();
            desifruotas.append(abc.charAt(newIndex));
        }
        return desifruotas.toString();
    }

    private static String uzsifruotiAscii(String zinute, String raktas) {
        StringBuilder uzsifruotas = new StringBuilder();
        for (int i = 0; i < zinute.length(); i++) {
            int newChar = (zinute.charAt(i) + raktas.charAt(i % raktas.length())) % 256;
            uzsifruotas.append((char) newChar);
        }
        return uzsifruotas.toString();
    }

    private static String desifruotiAscii(String zinute, String raktas) {
        StringBuilder desifruotas = new StringBuilder();
        for (int i = 0; i < zinute.length(); i++) {
            int newChar = (zinute.charAt(i) - raktas.charAt(i % raktas.length()) + 256) % 256;
            desifruotas.append((char) newChar);
        }
        return desifruotas.toString();
    }
}
