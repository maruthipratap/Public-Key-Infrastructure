import java.security.*;
import java.util.Base64;
import java.io.FileOutputStream;

public class KeyPairGeneratorUtil {

    public static void main(String[] args) throws Exception {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);

        KeyPair pair = keyGen.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        // Save Public Key
        FileOutputStream pubOut = new FileOutputStream("keys/public.key");
        pubOut.write(Base64.getEncoder().encode(publicKey.getEncoded()));
        pubOut.close();

        // Save Private Key
        FileOutputStream privOut = new FileOutputStream("keys/private.key");
        privOut.write(Base64.getEncoder().encode(privateKey.getEncoded()));
        privOut.close();

        System.out.println("RSA Key Pair Generated Successfully!");
    }
}
