// File: src/main/java/com/app/service/UserService.java
package com.app.service;

import com.app.dao.UserDAO;
import com.app.model.User;
import com.app.util.Logger;
import com.app.util.PasswordUtil;

import java.util.List;

public class UserService {
    private UserDAO userDAO;
    
    public UserService() {
        this.userDAO = new UserDAO();
    }
    
    public boolean registerUser(User user) {
        // Validate input
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            Logger.getInstance().log("Registration failed: Username is empty");
            return false;
        }
        
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            Logger.getInstance().log("Registration failed: Password too short");
            return false;
        }
        
        // Hash the password before storing
        String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        
        return userDAO.createUser(user);
    }
    
    public User login(String username, String password) {
        if (username == null || password == null) {
            Logger.getInstance().log("Login failed: Null credentials");
            return null;
        }
        
        User user = userDAO.getUserByUsername(username);
        
        if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) {
            Logger.getInstance().log("User logged in: " + username);
            return user;
        }
        
        Logger.getInstance().log("Failed login attempt for: " + username);
        return null;
    }
    
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    
    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }
    
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }
    
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}