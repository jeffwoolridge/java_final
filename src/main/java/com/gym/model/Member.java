package com.gym.model;

public class Member extends User {

    public Member() {
        super();
        setRole("MEMBER");
    }

    public Member(int userId, String username, String passwordHash, String email,
                  String phoneNumber, String address) {
        super(userId, username, passwordHash, email, phoneNumber, address, "MEMBER");
    }

    public Member(String username, String passwordHash, String email,
                  String phoneNumber, String address) {
        super(0, username, passwordHash, email, phoneNumber, address, "MEMBER");
    }
}
