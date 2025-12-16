// File: src/main/java/com/gym/util/DatabaseConnection.java
package com.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/gym_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "your_password";
    
    private static Connection connection = null;
    
    /**
     * Get database connection (Singleton pattern)
     * @return Connection object
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load PostgreSQL JDBC Driver
                Class.forName("org.postgresql.Driver");
                
                // Establish connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully!");
                Logger.getInstance().log("Database connection established");
                
            } catch (ClassNotFoundException e) {
                System.err.println("PostgreSQL Driver not found!");
                Logger.getInstance().log("ERROR: PostgreSQL Driver not found - " + e.getMessage());
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Connection failed!");
                Logger.getInstance().log("ERROR: Database connection failed - " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }
    
    /**
     * Close database connection
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Database connection closed.");
                Logger.getInstance().log("Database connection closed");
            } catch (SQLException e) {
                System.err.println("Error closing connection!");
                Logger.getInstance().log("ERROR: Failed to close connection - " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Test database connection
     * @return true if connection is successful
     */
    public static boolean testConnection() {
        try {
            Connection conn = getConnection();
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}