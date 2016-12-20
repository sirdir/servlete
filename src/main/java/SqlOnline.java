import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by tku on 12/19/2016.
 */
public class SqlOnline extends HttpServlet {

    private Connection con;
    private Statement st;

    public void doGet(HttpServletRequest request, HttpServletResponse response){

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String path = "jdbc:sqlite:/usr/local/tomcat/webapps/ROOT/temp.sqlite";
        try (Connection con = DriverManager.getConnection(path);
        Statement st = con.createStatement();
        PrintWriter writer = response.getWriter()) {
            writer.append("<!DOCTYPE html>")
                    .append("<html>")
                    .append("<head>")
                    .append("<meta charset=\"UTF-8\">")
                    .append("<title>Power of SQLite</title>")
                    .append("</head>")
                    .append("<body>")
                    .append("<ul>");
            ResultSet resultSet = st.executeQuery("SELECT * FROM list_of_names");
            while (resultSet.next()){
                writer.append("<li>")
                        .append(resultSet.getString("name"))
                        .append("</li>");
            }
            writer.append("</ul>")
                    .append("</body>")
                    .append("</html>");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
