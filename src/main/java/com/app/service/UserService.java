// File: src/main/java/com/app/service/UserService.java
package com.app.service;

import com.app.dao.UserDAO;
import com.app.model.User;
import com.app.util.PasswordUtil;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    /**
     * Register a new user
     * Hashes the password before saving
     */
    public boolean registerUser(User user) {
        // Hash the password
        String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userDAO.createUser(user);
    }

    /**
     * Login user
     * Checks password using BCrypt
     */
    public User loginUser(String username, String plainPassword) {
        User user = userDAO.getUserByUsername(username);
        if (user != null) {
            boolean matched = PasswordUtil.checkPassword(plainPassword, user.getPassword());
            if (matched) {
                return user;
            }
        }
        return null;
    }

    /**
     * Get all users (for Admin)
     */
    public java.util.List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Update user info
     */
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    /**
     * Delete user by ID
     */
    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }
}
