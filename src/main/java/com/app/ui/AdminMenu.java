// === AdminMenu.java ===
package com.app.ui;

import java.util.Scanner;

import com.app.model.User;

public class AdminMenu {


public static void show(Scanner scanner, User user) {
    boolean running = true;

    while (running) {
        System.out.println("\n=== ADMIN MENU ===");
        System.out.println("1. View All Users");
        System.out.println("2. View Membership Revenue");
        System.out.println("3. Manage Merchandise");
        System.out.println("4. Logout");
        System.out.print("Choice: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println("Viewing all users...");
                break;
            case "2":
                System.out.println("Viewing revenue...");
                break;
            case "3":
                System.out.println("Managing merchandise...");
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
