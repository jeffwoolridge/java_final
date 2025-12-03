// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        Logger logger = Logger.getInstance();

        logger.log("Application started");

        while (true) {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser(scanner, userService);
                    break;
                case 2:
                    loginUser(scanner, userService);
                    break;
                case 3:
                    logger.log("Application terminated");
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void registerUser(Scanner scanner, UserService userService) {
        System.out.println("\n=== User Registration ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.println("Select Role:");
        System.out.println("1. Admin");
        System.out.println("2. Trainer");
        System.out.println("3. Member");
        System.out.print("Choice: ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine();

        String role = "";
        switch (roleChoice) {
            case 1: role = "ADMIN"; break;
            case 2: role = "TRAINER"; break;
            case 3: role = "MEMBER"; break;
            default:
                System.out.println("Invalid role!");
                return;
        }

        User user = new User(0, username, password, email, phone, address, role);
        if (userService.registerUser(user)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed!");
        }
    }

    private static void loginUser(Scanner scanner, UserService userService) {
        System.out.println("\n=== Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userService.login(username, password);

        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUsername());
            showRoleMenu(scanner, user);
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private static void showRoleMenu(Scanner scanner, User user) {
        switch (user.getRole()) {
            case "ADMIN":
                new AdminMenu(scanner, user).show();
                break;
            case "TRAINER":
                new TrainerMenu(scanner, user).show();
                break;
            case "MEMBER":
                new MemberMenu(scanner, user).show();
                break;
        }
    }
}