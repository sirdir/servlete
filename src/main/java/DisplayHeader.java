import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Extend HttpServlet class
public class DisplayHeader extends HttpServlet {

    // Method to handle GET method request.
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "HTTP Header Request Example";

        String docType = "<!DOCUMENT html>";

        out.append(docType)
                .append("<html>")
                .append("<head><title>" + title + "</title></head>")
                .append("<body bgcolor=\"#f0f0f0\">")
                .append("<h1 align=\"center\">" + title + "</h1>")
                .append("<table width=\"100%\" border=\"1\" align=\"center\">")
                .append("<tr bgcolor=\"#949494\">")
                .append("<th>Header Name</th><th>Header Value(s)</th>")
                .append("</tr>");

        Enumeration headerNames = request.getHeaderNames();

        while(headerNames.hasMoreElements()) {
            String paramName = (String)headerNames.nextElement();
            out.append("<tr><td>" + paramName + "</td>");
            String paramValue = request.getHeader(paramName);
            out.append("<td> " + paramValue + "</td></tr>");
        }

        out.append("</table></body></html>");
    }

    // Method to handle POST method request.
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}