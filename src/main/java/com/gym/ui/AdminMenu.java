package com.gym.ui;

import java.util.List;
import java.util.Scanner;

import com.gym.model.Membership;
import com.gym.model.User;
import com.gym.model.WorkoutClass;
import com.gym.service.MembershipService;
import com.gym.service.UserService;
import com.gym.service.WorkoutClassService;
import com.gym.util.Logger;

public class AdminMenu {
    private final Scanner scanner;
    private final UserService userService = new UserService();
    private final WorkoutClassService workoutService = new WorkoutClassService();
    private final MembershipService membershipService = new MembershipService();

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
        List<User> users = userService.getAllUsers();
        System.out.println("\n--- All Users ---");
        for (User u : users) {
            System.out.printf("ID: %d | Username: %s | Email: %s | Role: %s%n",
                    u.getUserId(), u.getUsername(), u.getEmail(), u.getRole());
        }
    }

    private void addWorkoutClass() {
        WorkoutClass wc = new WorkoutClass();

        System.out.print("Class Type: ");
        wc.setClassType(scanner.nextLine());

        System.out.print("Class Description: ");
        wc.setClassDescription(scanner.nextLine());

        System.out.print("Trainer ID: ");
        wc.setTrainerId(getIntInput());

        System.out.print("Schedule Time (e.g., 2025-12-20 10:00): ");
        wc.setScheduleTime(scanner.nextLine());

        System.out.print("Capacity: ");
        wc.setCapacity(getIntInput());

        if (workoutService.addWorkoutClass(wc)) {
            System.out.println("✓ Workout class added successfully!");
        } else {
            System.out.println("✗ Failed to add workout class.");
        }
    }

    private void deleteWorkoutClass() {
        System.out.print("Enter Workout Class ID to delete: ");
        int id = getIntInput();
        if (workoutService.deleteWorkoutClass(id)) {
            System.out.println("✓ Workout class deleted successfully!");
        } else {
            System.out.println("✗ Failed to delete workout class.");
        }
    }

    private void viewAllMemberships() {
        List<Membership> memberships = membershipService.getAllMemberships();
        System.out.println("\n--- All Memberships ---");
        for (Membership m : memberships) {
            System.out.printf("ID: %d | Type: %s | Cost: %.2f | User ID: %d%n",
                    m.getMembershipId(), m.getMembershipType(), m.getMembershipCost(), m.getUserId());
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
}
