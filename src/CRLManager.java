import java.io.*;

public class CRLManager {

    private static String basePath = "";
    private static final String CRL_FILE = "crl.txt";
    public static void setBasePath(String path){
        basePath = path;
    }
    public static void revokeCertificate(String owner) throws Exception {
        FileWriter fw = new FileWriter(basePath + CRL_FILE, true);
        fw.write(owner + "\n");
        fw.close();
        //System.out.println("Certificate revoked for: " + owner);
    }

    public static boolean isRevoked(String owner) throws Exception {
        File file=new File(basePath + CRL_FILE);

        if(!file.exists()){
            return false;
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
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
