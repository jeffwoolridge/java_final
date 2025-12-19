# Gym Management System

## Overview
The **Gym Management System** is a console-based application that allows users to manage gym memberships, workout classes, and merchandise. It supports three roles:

- **Member**: Browse classes, purchase memberships, view memberships, and view merchandise.
- **Trainer**: View assigned classes, add notes, and view class participants.
- **Admin**: Manage users, classes, and system settings (future features).

The application is built using **Java** and **PostgreSQL**.

---

## Features

### Member
- Browse available workout classes.
- Purchase memberships (Basic, Premium, VIP).
- View purchased memberships.
- View total membership expenses.
- Browse gym merchandise.

### Trainer
- View assigned workout classes.
- Add class notes (coming soon).
- View class participants (coming soon).

### Admin
- Manage users (create, delete, view) — to be implemented.
- Manage classes and merchandise — to be implemented.

---

## Classes & Interactions

### Key Classes
| Class | Purpose |
|-------|---------|
| `User` | Represents a user (Member, Trainer, Admin). |
| `Membership` | Represents a gym membership. |
| `WorkoutClass` | Represents a workout class. |
| `GymMerch` | Represents gym merchandise. |
| `UserDAO` | Handles database operations for users. |
| `MembershipDAO` | Handles database operations for memberships. |
| `WorkoutClassService` | Handles business logic for workout classes. |
| `GymMerchService` | Handles business logic for gym merchandise. |
| `MemberMenu` | Displays member menu and handles member interactions. |
| `TrainerMenu` | Displays trainer menu and handles trainer interactions. |
| `MenuHandler` | Routes login/registration and role-based menus. |
| `Main` | Entry point of the application. |

### Class Diagram
User 1 --- * Membership
User 1 --- * WorkoutClass (trainer)
MembershipService --> MembershipDAO
WorkoutClassService --> WorkoutClassDAO
MemberMenu --> MembershipService, WorkoutClassService, GymMerchService
TrainerMenu --> WorkoutClassService


---

## Getting Started

### Prerequisites
- Java 17+  
- PostgreSQL  
- PostgreSQL JDBC Driver (include in `lib` folder)

### Clone Project
'''
git clone https://github.com/jeffwoolridge/java_final.git
'''

Database Setup

Open PostgreSQL and create the database:

'''
CREATE DATABASE gym_db;
'''

Run the SQL script to create tables and insert sample data:

'''
psql -U postgres -d gym_db -f sql/gym_db.sql
'''

Compile & Run

Compile the Java files:

'''
javac -d target/classes src/main/java/com/gym/**/*.java
'''

Run the application:

'''
java -cp target/classes com.gym.Main
'''
Usage Instructions
Main Menu
1. Login
2. Register
3. Exit

Member Menu
1. Browse Workout Classes
2. Purchase Membership
3. View My Memberships
4. View Total Membership Expenses
5. View Merchandise
6. Logout

Trainer Menu
1. View My Classes
2. Add Class Notes
3. View Participants
4. Logout

Development Documentation
Project Structure
GymManagementSystem/
├── README.md
├── src/
│   ├── main/java/com/gym/
│   │   ├── Main.java
│   │   ├── dao/
│   │   ├── model/
│   │   ├── service/
│   │   └── ui/
├── sql/
│   └── gym_db.sql
└── lib/ 

Build Process

Java compiler (javac) compiles the source code.

Classes are output to target/classes.

Run the main class com.gym.Main.

Javadoc

Generate Javadoc for all packages:

'''
javadoc -d docs -sourcepath src/main/java -subpackages com.gym
'''

Dependencies

PostgreSQL JDBC Driver

Java standard libraries