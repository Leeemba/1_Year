package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseHandler {
    private static final String URL = "jdbc:postgresql://localhost:9090/studs";
    private static final String USER = "REMOVED";   //System.getenv("PG_USER");
    private static final String PASS = "REMOVED";   //System.getenv("PG_PASS");

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not found", e);
        }
    }

    /**
     * Connects to studs database
     *
     * @return connection
     * @throws SQLException if encounters one
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
