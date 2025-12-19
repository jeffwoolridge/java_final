package com.gym.ui;

import com.gym.model.GymMerch;
import com.gym.model.Membership;
import com.gym.model.User;
import com.gym.model.WorkoutClass;
import com.gym.service.GymMerchService;
import com.gym.service.MembershipService;
import com.gym.service.WorkoutClassService;
import com.gym.util.Logger;

import java.util.List;
import java.util.Scanner;

public class MemberMenu {
    private final Scanner scanner;
    private final WorkoutClassService classService;
    private final MembershipService membershipService;
    private final GymMerchService gymMerchService;

    public MemberMenu(Scanner scanner) {
        this.scanner = scanner;
        this.classService = new WorkoutClassService();
        this.membershipService = new MembershipService();
        this.gymMerchService = new GymMerchService();
    }

    public void show(User member) {
        boolean running = true;
        
        while (running) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("                    MEMBER MENU");
            System.out.println("=".repeat(60));
            System.out.println("1. Browse Workout Classes");
            System.out.println("2. Purchase Membership");
            System.out.println("3. View My Memberships");
            System.out.println("4. View Total Membership Expenses");
            System.out.println("5. View Merchandise");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    browseClasses();
                    break;
                case 2:
                    purchaseMembership(member);
                    break;
                case 3:
                    viewMyMemberships(member);
                    break;
                case 4:
                    viewTotalExpenses(member);
                    break;
                case 5:
                    viewMerchandise();
                    break;
                case 6:
                    System.out.println("\nLogging out...");
                    Logger.logUserAction(member.getUsername(), "Logged out");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void browseClasses() {
        List<WorkoutClass> classes = classService.getAllClasses();
        
        System.out.println("\n" + "=".repeat(100));
        System.out.println("                                AVAILABLE WORKOUT CLASSES");
        System.out.println("=".repeat(100));
        System.out.printf("%-5s %-20s %-30s %-12s %-25s %-10s%n",
                         "ID", "Type", "Description", "Trainer ID", "Schedule", "Capacity");
        System.out.println("-".repeat(100));
        
        for (WorkoutClass wc : classes) {
            System.out.printf("%-5d %-20s %-30s %-12d %-25s %-10d%n",
                            wc.getClassId(),
                            wc.getClassType(),
                            wc.getClassDescription(),
                            wc.getTrainerId(),
                            wc.getScheduleTime(),
                            wc.getCapacity());
        }
        
        System.out.println("=".repeat(100));
        System.out.println("Total Classes Available: " + classes.size());
    }

    private void purchaseMembership(User member) {
        System.out.println("\n--- Purchase Membership ---");
        System.out.println("1. Basic Membership - $29.99/month");
        System.out.println("   • Access to gym equipment");
        System.out.println("   • Locker room access");
        System.out.println();
        System.out.println("2. Premium Membership - $49.99/month");
        System.out.println("   • All Basic benefits");
        System.out.println("   • Unlimited group classes");
        System.out.println("   • Free towel service");
        System.out.println();
        System.out.println("3. VIP Membership - $79.99/month");
        System.out.println("   • All Premium benefits");
        System.out.println("   • 2 personal training sessions/month");
        System.out.println("   • Guest passes");
        System.out.println();
        System.out.print("Choose membership type: ");
        
        int choice = getIntInput();
        
        String type;
        String description;
        double cost;
        
        switch (choice) {
            case 1:
                type = "Basic";
                description = "Access to gym equipment and locker rooms";
                cost = 29.99;
                break;
            case 2:
                type = "Premium";
                description = "Gym equipment + Unlimited group classes + Free towels";
                cost = 49.99;
                break;
            case 3:
                type = "VIP";
                description = "All access + 2 Personal training sessions + Guest passes";
                cost = 79.99;
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        System.out.println("\nYou selected: " + type + " Membership");
        System.out.println("Cost: $" + cost + "/month");
        System.out.print("Confirm purchase? (yes/no): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            Membership membership = new Membership();
            membership.setMembershipType(type);
            membership.setMembershipDescription(description);
            membership.setMembershipCost(cost);
            membership.setUserId(member.getUserId());
            
            if (membershipService.purchaseMembership(membership)) {
                System.out.println("\n✓ Membership purchased successfully!");
                System.out.println("Welcome to " + type + " membership!");
            } else {
                System.out.println("\n✗ Failed to purchase membership. Please try again.");
            }
        } else {
            System.out.println("Purchase cancelled.");
        }
    }

    private void viewMyMemberships(User member) {
        List<Membership> memberships = membershipService.getMembershipsByUserId(member.getUserId());
        
        if (memberships.isEmpty()) {
            System.out.println("\nYou don't have any memberships yet.");
            System.out.println("Purchase a membership to get started!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(90));
        System.out.println("                              MY MEMBERSHIPS");
        System.out.println("=".repeat(90));
        System.out.printf("%-5s %-20s %-40s %-10s %-15s%n",
                         "ID", "Type", "Description", "Cost", "Purchased");
        System.out.println("-".repeat(90));
        
        for (Membership m : memberships) {
            System.out.printf("%-5d %-20s %-40s $%-9.2f %-15s%n",
                            m.getMembershipId(),
                            m.getMembershipType(),
                            m.getMembershipDescription(),
                            m.getMembershipCost(),
                            m.getCreatedAt().toString().substring(0, 10));
        }
        
        System.out.println("=".repeat(90));
        System.out.println("Total Memberships: " + memberships.size());
    }

    private void viewTotalExpenses(User member) {
        double total = membershipService.getTotalExpensesByUserId(member.getUserId());
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                MEMBERSHIP EXPENSE REPORT");
        System.out.println("=".repeat(60));
        System.out.printf("Total Membership Expenses: $%.2f%n", total);
        System.out.println("=".repeat(60));
        
        if (total > 0) {
            System.out.println("\nTip: Premium and VIP memberships offer better value");
            System.out.println("     for frequent gym users!");
        }
    }

    private void viewMerchandise() {
        List<GymMerch> merchList = gymMerchService.getAllMerch();
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                        GYM MERCHANDISE");
        System.out.println("=".repeat(80));
        System.out.printf("%-5s %-25s %-15s %-12s %-15s%n",
                         "ID", "Name", "Type", "Price", "Availability");
        System.out.println("-".repeat(80));
        
        for (GymMerch merch : merchList) {
            String availability = merch.getQuantityInStock() > 0 ? "In Stock (" + merch.getQuantityInStock() + ")" : "Out of Stock";
            System.out.printf("%-5d %-25s %-15s $%-11.2f %-15s%n",
                            merch.getMerchId(),
                            merch.getMerchName(),
                            merch.getMerchType(),
                            merch.getMerchPrice(),
                            availability);
        }
        
        System.out.println("=".repeat(80));
        System.out.println("\nVisit the gym shop to purchase merchandise!");
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