// File: src/main/java/com/gym/ui/TrainerMenu.java
package com.app.ui;

import com.app.model.GymMerch;
import com.app.model.Membership;
import com.app.model.User;
import com.app.model.WorkoutClass;
import com.app.service.GymMerchService;
import com.app.service.MembershipService;
import com.app.service.WorkoutClassService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class TrainerMenu {
    private Scanner scanner;
    private User trainer;
    private WorkoutClassService workoutClassService;
    private MembershipService membershipService;
    private GymMerchService gymMerchService;

    public TrainerMenu(Scanner scanner, User trainer) {
        this.scanner = scanner;
        this.trainer = trainer;
        this.workoutClassService = new WorkoutClassService();
        this.membershipService = new MembershipService();
        this.gymMerchService = new GymMerchService();
    }

    public void show() {
        while (true) {
            System.out.println("\n=== Trainer Menu ===");
            System.out.println("Welcome, " + trainer.getUsername() + "!");
            System.out.println("1. Create Workout Class");
            System.out.println("2. View My Classes");
            System.out.println("3. Update Workout Class");
            System.out.println("4. Delete Workout Class");
            System.out.println("5. Purchase Membership");
            System.out.println("6. View Merchandise");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createWorkoutClass();
                    break;
                case 2:
                    viewMyClasses();
                    break;
                case 3:
                    updateWorkoutClass();
                    break;
                case 4:
                    deleteWorkoutClass();
                    break;
                case 5:
                    purchaseMembership();
                    break;
                case 6:
                    viewMerchandise();
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void createWorkoutClass() {
        System.out.println("\n=== Create Workout Class ===");
        System.out.print("Class Type (Yoga, HIIT, Spin, etc.): ");
        String type = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Schedule (YYYY-MM-DD HH:MM): ");
        String scheduleStr = scanner.nextLine();

        try {
            Timestamp schedule = Timestamp.valueOf(scheduleStr + ":00");

            System.out.print("Capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine();

            WorkoutClass workoutClass = new WorkoutClass(0, type, description,
                    trainer.getUserId(), schedule, capacity);
            if (workoutClassService.createWorkoutClass(workoutClass)) {
                System.out.println("Workout class created successfully!");
            } else {
                System.out.println("Failed to create workout class!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format! Please use YYYY-MM-DD HH:MM");
        }
    }

    private void viewMyClasses() {
        List<WorkoutClass> classes = workoutClassService.getTrainerClasses(trainer.getUserId());
        System.out.println("\n=== My Workout Classes ===");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-30s %-20s %-10s%n",
                "ID", "Type", "Description", "Schedule", "Capacity");
        System.out.println("---------------------------------------------------------------");

        for (WorkoutClass wc : classes) {
            System.out.printf("%-5d %-15s %-30s %-20s %-10d%n",
                    wc.getWorkoutClassId(),
                    wc.getWorkoutClassType(),
                    wc.getWorkoutClassDescription(),
                    wc.getScheduleTime().toString(),
                    wc.getCapacity()
            );
        }
        System.out.println("---------------------------------------------------------------\n");
    }

    private void updateWorkoutClass() {
        System.out.print("Enter Class ID to update: ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("New Class Type: ");
        String type = scanner.nextLine();

        System.out.print("New Description: ");
        String description = scanner.nextLine();

        System.out.print("New Schedule (YYYY-MM-DD HH:MM): ");
        String scheduleStr = scanner.nextLine();

        try {
            Timestamp schedule = Timestamp.valueOf(scheduleStr + ":00");

            System.out.print("New Capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine();

            WorkoutClass workoutClass = new WorkoutClass(classId, type, description,
                    trainer.getUserId(), schedule, capacity);
            if (workoutClassService.updateWorkoutClass(workoutClass)) {
                System.out.println("Workout class updated successfully!");
            } else {
                System.out.println("Failed to update workout class!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format! Please use YYYY-MM-DD HH:MM");
        }
    }

    private void deleteWorkoutClass() {
        System.out.print("Enter Class ID to delete: ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Are you sure? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            if (workoutClassService.deleteWorkoutClass(classId)) {
                System.out.println("Workout class deleted successfully!");
            } else {
                System.out.println("Failed to delete workout class!");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private void purchaseMembership() {
        System.out.println("\n=== Purchase Membership ===");
        System.out.println("Available Membership Types:");
        System.out.println("1. Basic - $29.99/month - Access to gym facilities");
        System.out.println("2. Premium - $49.99/month - Gym access + 5 classes/month");
        System.out.println("3. VIP - $79.99/month - Unlimited access + all classes");
        System.out.print("Choose membership type: ");

        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        String type = "";
        String description = "";
        double cost = 0.0;

        switch (typeChoice) {
            case 1:
                type = "Basic";
                description = "Access to gym facilities";
                cost = 29.99;
                break;
            case 2:
                type = "Premium";
                description = "Gym access + 5 classes/month";
                cost = 49.99;
                break;
            case 3:
                type = "VIP";
                description = "Unlimited access + all classes";
                cost = 79.99;
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        Membership membership = new Membership(0, type, description, cost,
                trainer.getUserId(), null);
        if (membershipService.purchaseMembership(membership)) {
            System.out.println("Membership purchased successfully!");
            System.out.printf("You've been charged $%.2f%n", cost);
        } else {
            System.out.println("Failed to purchase membership!");
        }
    }

    private void viewMerchandise() {
        List<GymMerch> merchList = gymMerchService.getAllMerch();
        System.out.println("\n=== Available Merchandise ===");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-5s %-20s %-15s %-10s%n",
                "ID", "Name", "Type", "Price");
        System.out.println("-----------------------------------------------");

        for (GymMerch merch : merchList) {
            System.out.printf("%-5d %-20s %-15s $%-9.2f%n",
                    merch.getMerchId(),
                    merch.getMerchName(),
                    merch.getMerchType(),
                    merch.getMerchPrice()
            );
        }
        System.out.println("-----------------------------------------------\n");
    }
}