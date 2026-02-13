import java.io.*;
import java.security.*;

public class CertificateVerifier {

    public static void main(String[] args) throws Exception {

        // Load CA public key
        PublicKey caPublicKey = CertificateAuthority.loadPublicKey("keys/public.key");

        // Load certificate
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("certificates/StudentUser.cert"));
        DigitalCertificate cert = (DigitalCertificate) in.readObject();
        in.close();

        // Verify certificate
        boolean result = cert.verifyCertificate(caPublicKey);

        if(result)
            System.out.println("Certificate is VALID. Trusted by CA.");
        else
            System.out.println("Certificate is INVALID!");
    }
}
