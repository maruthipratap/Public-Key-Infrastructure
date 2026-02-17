import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ViewCertificateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String basePath = getServletContext().getRealPath("/data/");
            String path = basePath + "certificates/StudentUser.cert";

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            DigitalCertificate cert = (DigitalCertificate) in.readObject();
            in.close();

            out.println("<h2>Certificate Details</h2>");
            out.println("Owner: " + cert.getOwnerName() + "<br>");
            out.println("Issuer: " + cert.getIssuer() + "<br>");
            out.println("Valid From: " + cert.getValidFrom() + "<br>");
            out.println("Valid To: " + cert.getValidTo() + "<br>");

        } catch (Exception e) {
            out.println("Certificate not found or not issued yet.");
        }
    }
}
