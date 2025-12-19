package com.gym.ui;

import java.util.Scanner;

import com.gym.model.User;
import com.gym.util.Logger;

public class AdminMenu {
    private final Scanner scanner;

    public AdminMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void show(User admin) {
        boolean running = true;

        while (running) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("                     ADMIN MENU");
            System.out.println("=".repeat(60));
            System.out.println("1. View All Users");
            System.out.println("2. Add Workout Class");
            System.out.println("3. Delete Workout Class");
            System.out.println("4. View All Memberships");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    viewAllUsers();
                    break;
                case 2:
                    addWorkoutClass();
                    break;
                case 3:
                    deleteWorkoutClass();
                    break;
                case 4:
                    viewAllMemberships();
                    break;
                case 5:
                    System.out.println("\nLogging out...");
                    Logger.logUserAction(admin.getUsername(), "Logged out");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewAllUsers() {
        // Placeholder: call UserService to get all users
        System.out.println("Viewing all users (functionality to implement)");
    }

    private void addWorkoutClass() {
        // Placeholder: call WorkoutClassService to add class
        System.out.println("Adding workout class (functionality to implement)");
    }

    private void deleteWorkoutClass() {
        // Placeholder: call WorkoutClassService to delete class
        System.out.println("Deleting workout class (functionality to implement)");
    }

    private void viewAllMemberships() {
        // Placeholder: call MembershipService to view all memberships
        System.out.println("Viewing all memberships (functionality to implement)");
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
}
