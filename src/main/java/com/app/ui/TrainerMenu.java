// === TrainerMenu.java ===
package com.app.ui;

import java.util.Scanner;

import com.app.model.User;

public class TrainerMenu {

public static void show(Scanner scanner, User user) {
    boolean running = true;

    while (running) {
        System.out.println("\n=== TRAINER MENU ===");
        System.out.println("1. Create Workout Class");
        System.out.println("2. View My Classes");
        System.out.println("3. Purchase Membership");
        System.out.println("4. Logout");
        System.out.print("Choice: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println("Creating class...");
                break;
            case "2":
                System.out.println("Viewing classes...");
                break;
            case "3":
                System.out.println("Purchasing membership...");
                break;
            case "4":
                System.out.println("Logging out...");
                running = false;
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }
}

}