This project demonstrates a secure file-sharing mechanism by encrypting files before storage or transfer.
The core objective is to minimize unauthorized access and data leakage by applying AES-256 encryption to files and allowing only authorized decryption.

This prototype is built as the foundational step of a larger system called:
“Designing Secure File Sharing in Cloud Storage.”

---Features---
AES-256 Encryption of any file
AES-256 Decryption to restore original data
Displays:
Original content (for text files)\
Encrypted content (unreadable byte data)
Decrypted content (restored original)
Saves encrypted and decrypted files in separate folders
Simulated cloud storage structure
Fully functional in VS Code using Java (JDK 17+)
Simple and clean code, beginner friendly
No external libraries required (uses Java’s built-in crypto)

---Technologies Used---
Language: Java
IDE: VS Code / IntelliJ
Encryption Library: JavaX Crypto (AES)
JDK Version: 17 or above

---How to Run the Program---
Install Java JDK 17+
Clone the repository
Open the folder in VS Code
Compile the program
Run it
for encryption-
1.enter the input path name(e.g in this message.pdf)
2.enter the output path name(name can be anything but its extension should be .enc)
-*It will generate encrypted file and key*-
for decryption-
1.enter the input encrypted file path(it shoud be of extension of .enc)
2. enter the output decrypted file path.

---things to remember---
When you encrypt a file (for example: file.pdf) and later decrypt it,
you must save the decrypted output with the SAME file extension.

If original file = .pdf
→ decrypted file must also be saved as .pdf

 If you save it as .txt or .jpg,
the decryption will still happen,
but the file content will appear in binary form and will not open properly.

This happens because encryption protects the bytes,
but does not change or detect the file format.

