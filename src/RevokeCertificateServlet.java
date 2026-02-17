import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RevokeCertificateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            String basePath = getServletContext().getRealPath("/data/");

            CRLManager.setBasePath(basePath);

            CRLManager.revokeCertificate("StudentUser");

            out.println("<h2 style='color:red'>Certificate Revoked Successfully</h2>");
            out.println("<br><a href='/pki'>Back to Home</a>");

        } catch(Exception e) {
            out.println("<h2>Error revoking certificate</h2>");
        }
    }
}
