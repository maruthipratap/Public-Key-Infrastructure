import java.io.*;
import java.security.*;
import java.util.Base64;

public class FileSigner {

    public static void main(String[] args) throws Exception {

        // Load private key
        PrivateKey privateKey = CertificateAuthority.loadPrivateKey("keys/private.key");

        // Read file
        FileInputStream fis = new FileInputStream("message.txt");
        byte[] data = fis.readAllBytes();
        fis.close();

        // Create signature
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        byte[] digitalSignature = signature.sign();

        // Save signature
        FileOutputStream sigOut = new FileOutputStream("message.sig");
        sigOut.write(Base64.getEncoder().encode(digitalSignature));
        sigOut.close();

        System.out.println("File signed successfully!");
    }
}
