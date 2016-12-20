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

    private Connection connection;
    private Statement statement;

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            PrintWriter writer = response.getWriter();
            writer.append("<!DOCTYPE html>")
                    .append("<html>")
                    .append("<head>")
                    .append("<meta charset=\"UTF-8\">")
                    .append("<title>Power of SQLite</title>")
                    .append("</head>")
                    .append("<body>")
                    .append("<ul>");
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/usr/local/tomcat/webapps/ROOT/temp.sqlite");
            statement = connection.createStatement();
//            statement.executeUpdate("CREATE table table_with_names (id int, name text)");
//            statement.executeUpdate("INSERT INTO table_with_names VALUES (1, 'fill')");
//            statement.executeUpdate("INSERT INTO table_with_names VALUES (2, 'bill')");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM list_of_names");
            while (resultSet.next()){
                writer.append("<li>")
                        .append(resultSet.getString("name"))
                        .append("</li>");
            }
            writer.append("</ul>")
                    .append("</body>")
                    .append("</html>");
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
