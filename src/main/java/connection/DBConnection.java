package connection;

import exception.PostgresException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;

    public static Connection makeConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new PostgresException("Postgres class not found");
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/teacher_db",
                    "postgres",
                    "password");
            System.out.println("Connection to DB has successfully !");
        } catch (SQLException e) {
            throw new PostgresException("Can't connect to DB");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("DB connection closed successfully !");
        } catch (SQLException e) {
            throw new PostgresException("Can't close connection");
        }
    }
}
