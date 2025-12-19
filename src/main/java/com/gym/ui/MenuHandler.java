package com.gym.ui;

import com.gym.model.User;
import com.gym.service.UserService;
import com.gym.util.Logger;

import java.util.Scanner;

public class MenuHandler {
    private final Scanner scanner;
    private final UserService userService;
    private final AdminMenu adminMenu;
    private final TrainerMenu trainerMenu;
    private final MemberMenu memberMenu;

    public MenuHandler() {
        this.scanner = new Scanner(System.in);
        this.userService = new UserService();
        this.adminMenu = new AdminMenu(scanner);
        this.trainerMenu = new TrainerMenu(scanner);
        this.memberMenu = new MemberMenu(scanner);
    }

    public void start() {
        System.out.println("\n" + repeat("=", 60));
        System.out.println("          WELCOME TO GYM MANAGEMENT SYSTEM");
        System.out.println(repeat("=", 60) + "\n");

        boolean running = true;

        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    handleRegistration();
                    break;
                case 3:
                    System.out.println("\nThank you for using Gym Management System!");
                    Logger.log("Application closed");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private void handleLogin() {
        System.out.print("\nUsername: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userService.loginUser(username, password);

        if (user != null) {
            System.out.println("\n✓ Login successful! Welcome, " + user.getUsername());
            routeToRoleMenu(user);
        } else {
            System.out.println("\n✗ Invalid credentials. Please try again.");
        }
    }

    private void handleRegistration() {
        System.out.println("\n--- User Registration ---");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.println("\nSelect Role:");
        System.out.println("1. Member");
        System.out.println("2. Trainer");
        System.out.println("3. Admin");
        System.out.print("Choice: ");
        int roleChoice = getIntInput();

        String role;
        switch (roleChoice) {
            case 1:
                role = "Member";
                break;
            case 2:
                role = "Trainer";
                break;
            case 3:
                role = "Admin";
                break;
            default:
                System.out.println("Invalid role. Defaulting to Member.");
                role = "Member";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setRole(role);

        if (userService.registerUser(user)) {
            System.out.println("\n✓ Registration successful! You can now log in.");
        } else {
            System.out.println("\n✗ Registration failed. Username or email may already exist.");
        }
    }

    private void routeToRoleMenu(User user) {
        // Use standard switch instead of arrow syntax
        String role = user.getRole();
        if ("Admin".equalsIgnoreCase(role)) {
            adminMenu.show(user);
        } else if ("Trainer".equalsIgnoreCase(role)) {
            trainerMenu.show(user);
        } else if ("Member".equalsIgnoreCase(role)) {
            memberMenu.show(user);
        } else {
            System.out.println("Unknown role. Logging out.");
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private String repeat(String s, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) sb.append(s);
        return sb.toString();
    }
}
