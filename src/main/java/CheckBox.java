import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CheckBox extends HttpServlet {

    // Method to handle GET method request.
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Reading Checkbox Data";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.append(docType)
                .append("<html>")
                .append("<head><title>" + title + "</title></head>")
                .append("<body bgcolor=\"#f0f0f0\">")
                .append("<h1 align=\"center\">" + title + "</h1>")
                .append("<ul><li><b>Maths Flag : </b>: ")
                .append(request.getParameter("maths"))
                .append("  <li><b>Physics Flag: </b>: ")
                .append(request.getParameter("physics"))
                .append("  <li><b>Chemistry Flag: </b>: ")
                .append(request.getParameter("chemistry"))
                .append("</ul></body></html>");
    }
    // Method to handle POST method request.
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}