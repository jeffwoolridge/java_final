// File: src/main/java/com/gym/ui/MemberMenu.java
package com.app.ui;

import com.app.model.GymMerch;
import com.app.model.Membership;
import com.app.model.User;
import com.app.model.WorkoutClass;
import com.app.service.GymMerchService;
import com.app.service.MembershipService;
import com.app.service.WorkoutClassService;
import java.util.List;
import java.util.Scanner;

public class MemberMenu {
    private Scanner scanner;
    private User member;
    private WorkoutClassService workoutClassService;
    private MembershipService membershipService;
    private GymMerchService gymMerchService;

    public MemberMenu(Scanner scanner, User member) {
        this.scanner = scanner;
        this.member = member;
        this.workoutClassService = new WorkoutClassService();
        this.membershipService = new MembershipService();
        this.gymMerchService = new GymMerchService();
    }

    public void show() {
        while (true) {
            System.out.println("\n=== Member Menu ===");
            System.out.println("Welcome, " + member.getUsername() + "!");
            System.out.println("1. Browse Workout Classes");
            System.out.println("2. View My Memberships");
            System.out.println("3. View Total Membership Expenses");
            System.out.println("4. Purchase New Membership");
            System.out.println("5. View Available Merchandise");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    browseWorkoutClasses();
                    break;
                case 2:
                    viewMyMemberships();
                    break;
                case 3:
                    viewTotalExpenses();
                    break;
                case 4:
                    purchaseMembership();
                    break;
                case 5:
                    viewMerchandise();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void browseWorkoutClasses() {
        List<WorkoutClass> classes = workoutClassService.getAllWorkoutClasses();
        System.out.println("\n=== Available Workout Classes ===");
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

    private void viewMyMemberships() {
        List<Membership> memberships = membershipService.getUserMemberships(member.getUserId());

        if (memberships.isEmpty()) {
            System.out.println("\nYou don't have any memberships yet!");
            System.out.println("Would you like to purchase one? (Go to option 4)\n");
            return;
        }

        System.out.println("\n=== My Memberships ===");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-30s %-10s %-20s%n",
                "ID", "Type", "Description", "Cost", "Purchase Date");
        System.out.println("---------------------------------------------------------------");

        for (Membership m : memberships) {
            System.out.printf("%-5d %-20s %-30s $%-9.2f %-20s%n",
                    m.getMembershipId(),
                    m.getMembershipType(),
                    m.getMembershipDescription(),
                    m.getMembershipCost(),
                    m.getPurchaseDate().toString()
            );
        }
        System.out.println("---------------------------------------------------------------\n");
    }

    private void viewTotalExpenses() {
        double totalExpenses = membershipService.getUserTotalExpenses(member.getUserId());
        System.out.println("\n=== My Total Membership Expenses ===");
        System.out.printf("Total Spent on Memberships: $%.2f%n\n", totalExpenses);
    }

    private void purchaseMembership() {
        System.out.println("\n=== Purchase Membership ===");
        System.out.println("Available Membership Types:");
        System.out.println("1. Basic - $29.99/month");
        System.out.println("   • Access to gym facilities");
        System.out.println("   • Use of cardio and strength equipment");
        System.out.println();
        System.out.println("2. Premium - $49.99/month");
        System.out.println("   • All Basic features");
        System.out.println("   • 5 workout classes per month");
        System.out.println("   • Free locker rental");
        System.out.println();
        System.out.println("3. VIP - $79.99/month");
        System.out.println("   • All Premium features");
        System.out.println("   • Unlimited workout classes");
        System.out.println("   • Personal training session (1/month)");
        System.out.println("   • Guest passes (2/month)");
        System.out.println();
        System.out.print("Choose membership type (1-3): ");

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

        System.out.print("\nConfirm purchase of " + type + " membership for $" + cost + "? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            Membership membership = new Membership(0, type, description, cost,
                    member.getUserId(), null);
            if (membershipService.purchaseMembership(membership)) {
                System.out.println("\n✓ Membership purchased successfully!");
                System.out.printf("You've been charged $%.2f%n", cost);
                System.out.println("Enjoy your " + type + " membership benefits!\n");
            } else {
                System.out.println("\n✗ Failed to purchase membership!");
            }
        } else {
            System.out.println("\nPurchase cancelled.");
        }
    }

    private void viewMerchandise() {
        List<GymMerch> merchList = gymMerchService.getAllMerch();

        if (merchList.isEmpty()) {
            System.out.println("\nNo merchandise available at the moment.\n");
            return;
        }

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