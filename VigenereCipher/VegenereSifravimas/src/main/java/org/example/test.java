import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        System.out.println("----Vigenere Cipher Encryptor/Decryptor----\n");
        Scanner sc = new Scanner(System.in);
        System.out.println("- Press 1 to take number|letter from array / Press 2 to take symbol|number|letters from ASCII table -");
        String inputChoice = sc.nextLine();

        if (inputChoice.equals("1")) {
            System.out.println("- Press 1 to encrypt a message / Press 2 to decrypt a message -");
            String input = sc.nextLine();


            if (input.equals("1")) {
                System.out.println("Enter the message");
                String Emessage = sc.nextLine();
                System.out.println("Enter the key");
                String key = sc.nextLine();
                String EncryptMessage = encryptFromArray(Emessage, key);
                System.out.println("The encrypted message is: " + EncryptMessage);


            } else if (input.equals("2")) {
                System.out.println("Enter the message");
                String Dmessage = sc.nextLine();
                System.out.println("Enter the key");
                String key = sc.nextLine();
                String DecryptMessage = decryptFromArray(Dmessage, key);
                System.out.println("The decrypted message is: " + DecryptMessage);


            } else {
                System.out.println("Wrong Input!");
            }
        } else if (inputChoice.equals("2")) {
            System.out.println("- Press 1 to encrypt a message / Press 2 to decrypt a message -");
            String input = sc.nextLine();
            if (input.equals("1")) {
                System.out.println("Enter the message");
                String Emessage = sc.nextLine();
                System.out.println("Enter the key");
                String key = sc.nextLine();
                String EncryptMessage = encryptFromASCII(Emessage, key);
                System.out.println("The encrypted message is: " + EncryptMessage);
            } else if (input.equals("2")) {
                System.out.println("Enter the message");
                String Dmessage = sc.nextLine();
                System.out.println("Enter the key");
                String key = sc.nextLine();
                String DecryptMessage = decryptFromASCII(Dmessage, key);
                System.out.println("The decrypted message is: " + DecryptMessage);
            } else {
                System.out.println("Wrong Input!");
            }
        } else {
            System.out.println("Wrong Input!");
        }
        sc.close();
    }

    public static String encryptFromArray(String Message, String key) {
        String EMessage = "";
        char[] abc = {
                'a', 'ą', 'b', 'c', 'č', 'd', 'e', 'ę', 'ė', 'f', 'g', 'h', 'i', 'į', 'y', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 'š', 't', 'u', 'ų', 'ū', 'v', 'z', 'ž',
                'A', 'Ą', 'B', 'C', 'Č', 'D', 'E', 'Ę', 'Ė', 'F', 'G', 'H', 'I', 'Į', 'Y', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'Š', 'T', 'U', 'Ų', 'Ū', 'V', 'Z', 'Ž',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };

        key = key.toUpperCase();

        for (int i = 0, j = 0; i < Message.length(); i++) {
            char letter = Message.charAt(i);
            if (Character.isLetter(letter) || Character.isDigit(letter)) { // tikrinam ar tai raidė ar skaičius
                int index = indexOf(abc, letter);
                int keyIndex = indexOf(abc, key.charAt(j));
                if (index != -1 && keyIndex != -1) { // tikrinam ar radome raides/skaičius masyve
                    EMessage += abc[(index + keyIndex) % abc.length];
                } else {
                    // jei simbolis nebuvo rastas masyve, pridedame jį tiesiogiai
                    EMessage += letter;
                }
            } else {
                // jei tai ne raidė ar skaičius, pridedame simbolį tiesiogiai
                EMessage += letter;
            }
            j = ++j % key.length();
        }
        return EMessage;
    }

    public static String decryptFromArray(String Message, String key) {
        String DMessage = "";
        char[] abc = {
                'a', 'ą', 'b', 'c', 'č', 'd', 'e', 'ę', 'ė', 'f', 'g', 'h', 'i', 'į', 'y', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 'š', 't', 'u', 'ų', 'ū', 'v', 'z', 'ž',
                'A', 'Ą', 'B', 'C', 'Č', 'D', 'E', 'Ę', 'Ė', 'F', 'G', 'H', 'I', 'Į', 'Y', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'Š', 'T', 'U', 'Ų', 'Ū', 'V', 'Z', 'Ž',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };
        key = key.toUpperCase();

        for (int i = 0, j = 0; i < Message.length(); i++) {
            char letter = Message.charAt(i);
            if (Character.isLetter(letter) || Character.isDigit(letter)) {
                int index = indexOf(abc, letter);
                int keyIndex = indexOf(abc, key.charAt(j));
                if (index != -1 && keyIndex != -1) { // tikrinam ar radome raides/skaičius masyve
                    int decryptedIndex = (index - keyIndex) % abc.length;
                    if (decryptedIndex < 0) {
                        decryptedIndex += abc.length;
                    }
                    DMessage += abc[decryptedIndex];
                } else {
                    // jei simbolis nebuvo rastas masyve, pridedame jį tiesiogiai
                    DMessage += letter;
                }
            } else {
                // jei tai ne raidė ar skaičius, pridedame simbolį tiesiogiai
                DMessage += letter;
            }
            j = ++j % key.length();
        }
        return DMessage;
    }

    public static int indexOf(char[] arr, char target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static String encryptFromASCII(String Message, String key) {
        String ASCIIEMessage = "";
        int keyLength = key.length();
        int messageLength = Message.length();

        for (int i = 0; i < messageLength; i++) {
            char letter = Message.charAt(i);
            char keyChar = key.charAt(i % keyLength);
            if (letter >= 32 && letter <= 126) { // Patikriname, ar simbolis yra tarp 32 ir 126 ASCII ribų
                int shifted = ((letter - 32) + (keyChar - 32)) % 95 + 32; // Slenkame simbolį į ASCII ribų
                ASCIIEMessage += (char) shifted;
            } else {
                ASCIIEMessage += letter; // Jei simbolis nepatenka į ASCII ribas, pridedame tiesiogiai
            }
        }

        return ASCIIEMessage;
    }

    public static String decryptFromASCII(String Message, String key) {
        String ASCIIDMessage = "";
        int keyLength = key.length();
        int messageLength = Message.length();

        for (int i = 0; i < messageLength; i++) {
            char letter = Message.charAt(i);
            char keyChar = key.charAt(i % keyLength);
            if (letter >= 32 && letter <= 126) { // Patikriname, ar simbolis yra tarp 32 ir 126 ASCII ribų
                int shifted = ((letter - 32) - (keyChar - 32) + 95) % 95 + 32; // Slenkame simbolį į ASCII ribų
                ASCIIDMessage += (char) shifted;
            } else {
                ASCIIDMessage += letter; // Jei simbolis nepatenka į ASCII ribas, pridedame tiesiogiai
            }
        }

        return ASCIIDMessage;
    }

}