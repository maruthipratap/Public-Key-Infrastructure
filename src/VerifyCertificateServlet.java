import java.io.*;
import java.security.PublicKey;
import javax.servlet.*;
import javax.servlet.http.*;

public class VerifyCertificateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            String basePath = getServletContext().getRealPath("/data/");
            CertificateAuthority.setBasePath(basePath);
            CRLManager.setBasePath(basePath);

            // Load certificate
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(basePath + "certificates/StudentUser.cert"));
            DigitalCertificate cert = (DigitalCertificate) in.readObject();
            in.close();

            // Check CRL
            if (CRLManager.isRevoked(cert.getOwnerName())) {
                out.println("<h2 style='color:red'>Certificate REVOKED</h2>");
                return;
            }

            // Load CA public key
            PublicKey caPublicKey = CertificateAuthority.loadPublicKey(basePath + "keys/public.key");

            // Verify
            boolean valid = cert.verifyCertificate(caPublicKey);

            if(valid)
                out.println("<h2 style='color:green'>Certificate VALID & Trusted</h2>");
            else
                out.println("<h2 style='color:red'>Certificate INVALID</h2>");

        } catch (Exception e) {
            out.println("<h2>Certificate not issued yet</h2>");
        }
    }
}
