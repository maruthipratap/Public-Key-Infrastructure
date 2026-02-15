import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class IssueCertificateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            CertificateAuthority.main(null);
            out.println("<h2>Certificate Issued Successfully!</h2>");
            out.println("<a href='/pki'>Go Back</a>");
        } catch (Exception e) {
            out.println("<h2>Error issuing certificate</h2>");
            out.println(e.getMessage());
        }
    }
}
