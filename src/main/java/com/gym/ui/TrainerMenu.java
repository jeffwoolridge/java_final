package com.gym.ui;

import java.util.Scanner;

import com.gym.model.User;
import com.gym.service.WorkoutClassService;

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
            System.out.println("\n--- Trainer Menu ---");
            System.out.println("1. Add Workout Class");
            System.out.println("2. Delete Workout Class");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addWorkoutClass();
                    break;
                case 2:
                    deleteWorkoutClass();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void addWorkoutClass() {
        // implement class creation input here
        System.out.println("Add Workout Class feature coming soon.");
    }

    private void deleteWorkoutClass() {
        // implement delete class by ID here
        System.out.println("Delete Workout Class feature coming soon.");
    }

    private int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }
}
