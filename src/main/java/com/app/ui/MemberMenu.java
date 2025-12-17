// === MemberMenu.java ===
package com.app.ui;

import java.util.Scanner;

import com.app.model.User;

public class MemberMenu {

public static void show(Scanner scanner, User user) {
    boolean running = true;

    while (running) {
        System.out.println("\n=== MEMBER MENU ===");
        System.out.println("1. View Workout Classes");
        System.out.println("2. View Membership Expenses");
        System.out.println("3. Logout");
        System.out.print("Choice: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.println("Viewing workout classes...");
                break;
            case "2":
                System.out.println("Viewing membership expenses...");
                break;
            case "3":
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