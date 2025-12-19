package com.gym;

import com.gym.ui.MenuHandler;
import com.gym.util.DBUtil;
import com.gym.util.Logger;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize logger
            Logger.log("=".repeat(60));
            Logger.log("Gym Management System Started");
            Logger.log("=".repeat(60));
            
            // Test database connection
            DBUtil.testConnection();
            
            // Start the application
            MenuHandler menuHandler = new MenuHandler();
            menuHandler.start();
            
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            Logger.logError("Fatal error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}