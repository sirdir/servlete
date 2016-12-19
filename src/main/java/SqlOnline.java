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

    @Override
    public void init(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:~/servlete/build/src/main/resources/temp.sqlite");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            PrintWriter writer = response.getWriter();
            writer.append("<!DOCTYPE html>")
                    .append("<html>")
                    .append("<header>")
                    .append("<meta charset=\"UTF-8\">")
                    .append("<title>Power of SQLite</title>")
                    .append("</header>")
                    .append("<body>")
                    .append("<ul>");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM table_with_names");
            while (resultSet.next()){
                writer.append("<li>")
                        .append(resultSet.getString("name"))
                        .append("</li>");
            }
            writer.append("</ul>")
                    .append("</body>")
                    .append("</html>");
        } catch (IOException|SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
