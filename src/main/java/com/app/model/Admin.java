package com.app.model;

public class Admin extends User {
    public Admin(int userId, String username, String password, String email,
                 String phoneNumber, String address) {
        super(userId, username, password, email, phoneNumber, address, "ADMIN");
    }

    public void viewAllUsers() {
        System.out.println("Viewing all users...");
    }

    public void deleteUser(int userId) {
        System.out.println("Deleting user: " + userId);
    }
}