package cpl.airline_booking_backend.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/airline_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
