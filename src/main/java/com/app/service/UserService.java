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

    public boolean registerUser(User user) {
        // Hash password before storing
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        return userDAO.createUser(user);
    }

    public User loginUser(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }
}
