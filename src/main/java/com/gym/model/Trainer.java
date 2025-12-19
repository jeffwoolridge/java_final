package com.gym.model;

public class Trainer extends User {
    public Trainer() { super(); }
    public Trainer(int userId, String username, String passwordHash, String email, String phoneNumber, String address) {
        super(userId, username, passwordHash, email, phoneNumber, address, "TRAINER");
    }
}
