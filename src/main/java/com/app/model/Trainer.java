package com.app.model;

public class Trainer extends User {
    public Trainer(int userId, String username, String password, String email,
                   String phoneNumber, String address) {
        super(userId, username, password, email, phoneNumber, address, "TRAINER");
    }

    public void createWorkoutClass() {
        System.out.println("Creating workout class...");
    }

    public void viewMyClasses() {
        System.out.println("Viewing my classes...");
    }
}