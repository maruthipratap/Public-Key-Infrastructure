import java.io.*;
import java.security.*;
import java.util.Base64;

public class FileVerifier {

    public static void main(String[] args) throws Exception {

        // Load public key
        PublicKey publicKey = CertificateAuthority.loadPublicKey("keys/public.key");

        // Read original file
        FileInputStream fis = new FileInputStream("message.txt");
        byte[] data = fis.readAllBytes();
        fis.close();

        // Read signature
        FileInputStream sigFis = new FileInputStream("message.sig");
        byte[] sigBytes = Base64.getDecoder().decode(sigFis.readAllBytes());
        sigFis.close();

        // Verify
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(data);

        boolean verified = signature.verify(sigBytes);

        if(verified)
            System.out.println("File is authentic. No tampering detected.");
        else
            System.out.println("File has been tampered!");
    }
}
