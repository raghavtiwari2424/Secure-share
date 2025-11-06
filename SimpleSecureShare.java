import java.io.*;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SimpleSecureShare {

    private static final String KEY_FILE = "encryption.key"; // where key will be stored

    // Generate a new AES key and save it
    private static void generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // AES-256
        SecretKey secretKey = keyGen.generateKey();

        try (FileOutputStream fos = new FileOutputStream(KEY_FILE)) {
            fos.write(secretKey.getEncoded());
        }

        System.out.println("🔑 New AES-256 key generated and saved to: " + KEY_FILE);
    }

    // Load AES key from file
    private static SecretKey loadKey() throws Exception {
        byte[] keyBytes = new byte[(int) new File(KEY_FILE).length()];
        try (FileInputStream fis = new FileInputStream(KEY_FILE)) {
            fis.read(keyBytes);
        }
        return new SecretKeySpec(keyBytes, "AES");
    }

    // Encrypt a file
    public static void encryptFile(String inputFile, String outputFile) throws Exception {
        // Generate a new key before every encryption
        generateKey();

        SecretKey key = loadKey();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            byte[] inputBytes = fis.readAllBytes();
            byte[] outputBytes = cipher.doFinal(inputBytes);
            fos.write(outputBytes);
        }

        System.out.println("✅ File Encrypted Successfully: " + outputFile);
    }

    // Decrypt a file
    public static void decryptFile(String inputFile, String outputFile) throws Exception {
        // Load the same key that was used for encryption
        SecretKey key = loadKey();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            byte[] inputBytes = fis.readAllBytes();
            byte[] outputBytes = cipher.doFinal(inputBytes);
            fos.write(outputBytes);
        }

        System.out.println("✅ File Decrypted Successfully: " + outputFile);
    }

    // Main program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Secure File Sharing Prototype ===");
            System.out.println("1. Encrypt a File");
            System.out.println("2. Decrypt a File");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                if (choice == 1) {
                    System.out.print("Enter input file path: ");
                    String inFile = scanner.nextLine();
                    System.out.print("Enter output encrypted file name: ");
                    String outFile = scanner.nextLine();
                    encryptFile(inFile, outFile);

                } else if (choice == 2) {
                    System.out.print("Enter input encrypted file path: ");
                    String inFile = scanner.nextLine();
                    System.out.print("Enter output decrypted file name: ");
                    String outFile = scanner.nextLine();
                    decryptFile(inFile, outFile);

                } else if (choice == 3) {
                    System.out.println("👋 Exiting... Bye!");
                    break;

                } else {
                    System.out.println("❌ Invalid choice, try again.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
