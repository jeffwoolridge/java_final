package com.gym.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
private static final String URL = "jdbc:postgresql://localhost:5432/gym_db";
private static final String USER = "postgres";
private static final String PASSWORD = "password";


public static Connection getConnection() throws SQLException {
return DriverManager.getConnection(URL, USER, PASSWORD);
}

public static void testConnection() {
    try (Connection conn = getConnection()) {
        System.out.println("Database connection successful!");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
