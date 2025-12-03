// File: src/main/java/com/gym/dao/UserDAO.java
package com.app.dao;

import com.app.model.User;
import com.app.util.DatabaseConnection;
import com.app.util.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public boolean createUser(User user) {
        String sql = "INSERT INTO users (username, password, email, phone_number, address, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getRole());

            int rowsAffected = pstmt.executeUpdate();
            Logger.getInstance().log("User created: " + user.getUsername());
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getInstance().log("Error creating user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            Logger.getInstance().log("Error retrieving user: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            Logger.getInstance().log("Error retrieving all users: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            int rowsAffected = pstmt.executeUpdate();
            Logger.getInstance().log("User deleted: ID " + userId);
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getInstance().log("Error deleting user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE users SET username = ?, email = ?, phone_number = ?, address = ? WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPhoneNumber());
            pstmt.setString(4, user.getAddress());
            pstmt.setInt(5, user.getUserId());

            int rowsAffected = pstmt.executeUpdate();
            Logger.getInstance().log("User updated: ID " + user.getUserId());
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.getInstance().log("Error updating user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}