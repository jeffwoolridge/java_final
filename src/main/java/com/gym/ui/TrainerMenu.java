package com.gym.ui;

import java.util.List;
import java.util.Scanner;

import com.gym.model.User;
import com.gym.model.WorkoutClass;
import com.gym.service.WorkoutClassService;
import com.gym.util.Logger;

public class TrainerMenu {
    private final Scanner scanner;
    private final WorkoutClassService classService;

    public TrainerMenu(Scanner scanner) {
        this.scanner = scanner;
        this.classService = new WorkoutClassService();
    }

    public void show(User trainer) {
        boolean running = true;

        while (running) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("                     TRAINER MENU");
            System.out.println("=".repeat(60));
            System.out.println("1. View My Classes");
            System.out.println("2. Add Workout Class");
            System.out.println("3. Add Class Notes");
            System.out.println("4. View Participants");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    viewMyClasses(trainer);
                    break;
                case 2:
                    addWorkoutClass(trainer);
                    break;
                case 3:
                    addClassNotes(trainer);
                    break;
                case 4:
                    viewParticipants(trainer);
                    break;
                case 5:
                    System.out.println("\nLogging out...");
                    Logger.logUserAction(trainer.getUsername(), "Logged out");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewMyClasses(User trainer) {
        List<WorkoutClass> classes = classService.getAllClasses(); // Optionally filter by trainerId
        System.out.println("\n--- My Assigned Classes ---");
        System.out.printf("%-5s %-20s %-30s %-25s %-10s%n",
                "ID", "Type", "Description", "Schedule", "Capacity");
        System.out.println("-".repeat(90));
        for (WorkoutClass wc : classes) {
            if (wc.getTrainerId() == trainer.getUserId()) {
                System.out.printf("%-5d %-20s %-30s %-25s %-10d%n",
                        wc.getClassId(), wc.getClassType(), wc.getClassDescription(),
                        wc.getScheduleTime(), wc.getCapacity());
            }
        }
    }

    private void addWorkoutClass(User trainer) {
        System.out.println("\n--- Add Workout Class ---");

        System.out.print("Class Type: ");
        String type = scanner.nextLine();

        System.out.print("Class Description: ");
        String description = scanner.nextLine();

        System.out.print("Schedule Time (e.g., Mon/Wed/Fri 8AM): ");
        String schedule = scanner.nextLine();

        System.out.print("Capacity: ");
        int capacity = getIntInput();

        WorkoutClass wc = new WorkoutClass();
        wc.setClassType(type);
        wc.setClassDescription(description);
        wc.setTrainerId(trainer.getUserId());
        wc.setScheduleTime(schedule);
        wc.setCapacity(capacity);

        if (classService.addWorkoutClass(wc)) {
            System.out.println("\n✓ Workout class added successfully!");
        } else {
            System.out.println("\n✗ Failed to add workout class. Please try again.");
        }
    }

    private void addClassNotes(User trainer) {
        System.out.println("\nFeature coming soon: Add Class Notes");
        // Could prompt for class ID and notes, then save to DB
    }

    private void viewParticipants(User trainer) {
        System.out.println("\nFeature coming soon: View Class Participants");
        // Could fetch memberships or bookings for trainer's classes
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }
}
