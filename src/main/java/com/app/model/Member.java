package com.app.model;

public class Member extends User {
    public Member(int userId, String username, String password, String email,
                  String phoneNumber, String address) {
        super(userId, username, password, email, phoneNumber, address, "MEMBER");
    }

    public void browseClasses() {
        System.out.println("Browsing workout classes...");
    }

    public void viewMembershipExpenses() {
        System.out.println("Viewing membership expenses...");
    }
}