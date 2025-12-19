package com.gym.service;

import java.util.List;

import com.gym.dao.UserDAO;
import com.gym.model.User;
import com.gym.util.PasswordUtil;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {
        String hashed = PasswordUtil.hashPassword(user.getPasswordHash());
        user.setPassword(hashed);
        return userDAO.createUser(user);
    }

    public User loginUser(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && PasswordUtil.checkPassword(password, user.getPasswordHash())) {
            return user;
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }
}
