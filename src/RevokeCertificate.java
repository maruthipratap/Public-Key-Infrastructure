public class RevokeCertificate {

    public static void main(String[] args) throws Exception {
        CRLManager.revokeCertificate("StudentUser");
    }
}
