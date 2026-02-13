import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class CertificateAuthority {

    public static PublicKey loadPublicKey(String filename) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(readFile(filename));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }

    public static PrivateKey loadPrivateKey(String filename) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(readFile(filename));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }

    private static String readFile(String path) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {

        // Load CA Keys
        PrivateKey caPrivateKey = loadPrivateKey("keys/private.key");
        PublicKey caPublicKey = loadPublicKey("keys/public.key");

        // Create certificate for user
        String userName = "StudentUser";
        PublicKey userPublicKey = caPublicKey; // demo purpose

        DigitalCertificate cert = new DigitalCertificate(userName, userPublicKey, "MiniPKI-CA");

        // Sign certificate
        cert.signCertificate(caPrivateKey);

        // Save certificate
        cert.saveCertificate("certificates/" + userName + ".cert");

        System.out.println("Certificate issued successfully for " + userName);
    }
}
