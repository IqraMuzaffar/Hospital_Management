import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // JDBC URL, username, and password of MySQL server
        String url = "jdbc:mysql://localhost:3306/Hospital";
        String user = "root";
        String password = "W7301@jqir#";

        // JDBC variable for opening, closing, and managing connection
        Connection connection = null;

        try {
            // Open a connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");

            // Do something with the connection (e.g., execute SQL queries)
            // ...

        } catch (SQLException e) {
            System.err.println("Connection failed" + e.getMessage());
        }

    }
}
