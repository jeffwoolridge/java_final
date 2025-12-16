// File: src/main/java/com/app/Main.java
package com.app;

import java.util.Scanner;

import com.app.model.User;
import com.app.service.UserService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        System.out.println("Register a new user:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User newUser = new User(0, username, password, "email@example.com", "1234567890", "123 Street", "user");
        if (userService.registerUser(newUser)) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Registration failed.");
        }

        System.out.println("\nLogin:");
        System.out.print("Username: ");
        String loginUser = scanner.nextLine();
        System.out.print("Password: ");
        String loginPass = scanner.nextLine();

        User loggedIn = userService.loginUser(loginUser, loginPass);
        if (loggedIn != null) {
            System.out.println("Login successful! Welcome " + loggedIn.getUsername());
        } else {
            System.out.println("Login failed.");
        }

        scanner.close();
    }
}
