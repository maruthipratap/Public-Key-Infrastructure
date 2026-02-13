import java.io.*;
import java.util.*;

public class CRLManager {

    private static final String CRL_FILE = "crl.txt";

    public static void revokeCertificate(String owner) throws Exception {
        FileWriter fw = new FileWriter(CRL_FILE, true);
        fw.write(owner + "\n");
        fw.close();
        System.out.println("Certificate revoked for: " + owner);
    }

    public static boolean isRevoked(String owner) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(CRL_FILE));
        String line;
        while((line = br.readLine()) != null) {
            if(line.equals(owner)) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }
}
