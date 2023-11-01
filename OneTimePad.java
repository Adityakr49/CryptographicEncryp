import java.util.Scanner;

public class OneTimePad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext message: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the one-time pad key (binary): ");
        String key = scanner.nextLine();

        String ciphertext = encrypt(plaintext, key);
        String decryptedText = decrypt(ciphertext, key);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Ciphertext: " + ciphertext);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }

    // Encrypt the plaintext using the one-time pad key
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = key.charAt(i % key.length()); // Reuse key if it's shorter
            char encryptedChar = (char) (plainChar ^ keyChar);
            ciphertext.append(encryptedChar);
        }
        return ciphertext.toString();
    }

    // Decrypt the ciphertext using the one-time pad key
    public static String decrypt(String ciphertext, String key) {
        return encrypt(ciphertext, key); // XOR operation is its own inverse
    }
}
