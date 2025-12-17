// === Main.java ===
package com.app;

import com.app.model.User;
import com.app.service.UserService;
import com.app.ui.AdminMenu;
import com.app.ui.TrainerMenu;
import com.app.ui.MemberMenu;

import java.util.Scanner;

public class Main {

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserService();

    boolean running = true;

    while (running) {
        System.out.println("\n=== Gym Management System ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            continue;
        }

        switch (choice) {
            case 1:
                register(scanner, userService);
                break;
            case 2:
                login(scanner, userService);
                break;
            case 3:
                running = false;
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }
}

private static void register(Scanner scanner, UserService userService) {
    System.out.print("Username: ");
    String username = scanner.nextLine();

    System.out.print("Password: ");
    String password = scanner.nextLine();

    System.out.print("Email: ");
    String email = scanner.nextLine();

    System.out.print("Phone: ");
    String phone = scanner.nextLine();

    System.out.print("Address: ");
    String address = scanner.nextLine();

    System.out.print("Role (ADMIN / TRAINER / MEMBER): ");
    String role = scanner.nextLine().toUpperCase();

    User user = new User(0, username, password, email, phone, address, role);

    if (userService.registerUser(user)) {
        System.out.println("User registered successfully!");
    } else {
        System.out.println("Registration failed.");
    }
}

private static void login(Scanner scanner, UserService userService) {
    System.out.print("Username: ");
    String username = scanner.nextLine();

    System.out.print("Password: ");
    String password = scanner.nextLine();

    User user = userService.loginUser(username, password);

    if (user == null) {
        System.out.println("Invalid username or password.");
        return;
    }

    System.out.println("Login successful! Welcome " + user.getUsername());

    switch (user.getRole().toUpperCase()) {
        case "ADMIN":
            AdminMenu.show(scanner, user);
            break;
        case "TRAINER":
            TrainerMenu.show(scanner, user);
            break;
        case "MEMBER":
            MemberMenu.show(scanner, user);
            break;
        default:
            System.out.println("Unknown role.");
            break;
    }
}


}






