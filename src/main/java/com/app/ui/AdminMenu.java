// File: src/main/java/com/gym/ui/AdminMenu.java
package com.app.ui;

import com.app.model.GymMerch;
import com.app.model.Membership;
import com.app.model.User;
import com.app.service.GymMerchService;
import com.app.service.MembershipService;
import com.app.service.UserService;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private Scanner scanner;
    private User admin;
    private UserService userService;
    private MembershipService membershipService;
    private GymMerchService gymMerchService;

    public AdminMenu(Scanner scanner, User admin) {
        this.scanner = scanner;
        this.admin = admin;
        this.userService = new UserService();
        this.membershipService = new MembershipService();
        this.gymMerchService = new GymMerchService();
    }

    public void show() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("Welcome, " + admin.getUsername() + "!");
            System.out.println("1. View All Users");
            System.out.println("2. Delete User");
            System.out.println("3. View All Memberships");
            System.out.println("4. View Total Revenue");
            System.out.println("5. Add Merchandise");
            System.out.println("6. View Merchandise Report");
            System.out.println("7. View Stock Value");
            System.out.println("8. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllUsers();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    viewAllMemberships();
                    break;
                case 4:
                    viewTotalRevenue();
                    break;
                case 5:
                    addMerchandise();
                    break;
                case 6:
                    viewMerchandiseReport();
                    break;
                case 7:
                    viewStockValue();
                    break;
                case 8:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void viewAllUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println("\n=== All Users ===");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-25s %-15s %-10s%n",
                "ID", "Username", "Email", "Phone", "Role");
        System.out.println("---------------------------------------------------------------");

        for (User user : users) {
            System.out.printf("%-5d %-15s %-25s %-15s %-10s%n",
                    user.getUserId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getRole()
            );
        }
        System.out.println("---------------------------------------------------------------\n");
    }

    private void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Are you sure? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            if (userService.deleteUser(userId)) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("Failed to delete user!");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private void viewAllMemberships() {
        List<Membership> memberships = membershipService.getAllMemberships();
        System.out.println("\n=== All Memberships ===");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-30s %-10s %-10s%n",
                "ID", "Type", "Description", "Cost", "Member ID");
        System.out.println("---------------------------------------------------------------");

        for (Membership m : memberships) {
            System.out.printf("%-5d %-20s %-30s $%-9.2f %-10d%n",
                    m.getMembershipId(),
                    m.getMembershipType(),
                    m.getMembershipDescription(),
                    m.getMembershipCost(),
                    m.getMemberId()
            );
        }
        System.out.println("---------------------------------------------------------------\n");
    }

    private void viewTotalRevenue() {
        double revenue = membershipService.getTotalRevenue();
        System.out.println("\n=== Total Annual Revenue ===");
        System.out.printf("Total Revenue from Memberships: $%.2f%n\n", revenue);
    }

    private void addMerchandise() {
        System.out.println("\n=== Add Merchandise ===");
        System.out.print("Merchandise Name: ");
        String name = scanner.nextLine();

        System.out.print("Merchandise Type (Gear/Drink/Food): ");
        String type = scanner.nextLine();

        System.out.print("Price: $");
        double price = scanner.nextDouble();

        System.out.print("Quantity in Stock: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        GymMerch merch = new GymMerch(0, name, type, price, quantity);
        if (gymMerchService.addMerch(merch)) {
            System.out.println("Merchandise added successfully!");
        } else {
            System.out.println("Failed to add merchandise!");
        }
    }

    private void viewMerchandiseReport() {
        gymMerchService.printMerchReport();
    }

    private void viewStockValue() {
        double stockValue = gymMerchService.getTotalStockValue();
        System.out.println("\n=== Total Stock Value ===");
        System.out.printf("Total Value of Merchandise in Stock: $%.2f%n\n", stockValue);
    }
}