import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GoalTracker {

    static class Goal {
        String description;
        boolean completed;

        Goal(String description) {
            this.description = description;
            this.completed = false;
        }
    }

    static ArrayList<Goal> goals = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showWelcomeMessage();

        while (true) {
            showMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    addGoal();
                    break;
                case 2:
                    viewGoals();
                    break;
                case 3:
                    markGoalCompleted();
                    break;
                case 4:
                    System.out.println("Exiting... Have a great day!");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter again.");
            }
        }
    }

    static void showWelcomeMessage() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        String greeting = (time.getHour() < 12) ? "Good morning!" : "Good evening!";
        System.out.println("======================================");
        System.out.println(greeting);
        System.out.println("Today is " + date);
        System.out.println("Current time: " + time.format(timeFormat));
        System.out.println("Welcome to Goal Tracker!");
        System.out.println("======================================");
    }

    static void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add a new goal");
        System.out.println("2. View today's goals");
        System.out.println("3. Mark a goal as completed");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    static int getChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            // ignore and return -1
        }
        return choice;
    }

    static void addGoal() {
        System.out.print("Enter your goal description: ");
        String desc = scanner.nextLine();
        goals.add(new Goal(desc));
        System.out.println("Goal added successfully!");
    }

    static void viewGoals() {
        if (goals.isEmpty()) {
            System.out.println("No goals for today.");
        } else {
            System.out.println("Today's Goals:");
            for (int i = 0; i < goals.size(); i++) {
                Goal goal = goals.get(i);
                String status = goal.completed ? "✓ Completed" : "Pending";
                System.out.println((i + 1) + ". " + goal.description + " [" + status + "]");
            }
        }
    }

    static void markGoalCompleted() {
        viewGoals();
        if (goals.isEmpty()) {
            return;
        }
        System.out.print("Enter the goal number to mark as completed: ");
        int num = -1;
        try {
            num = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
            return;
        }
        if (num < 1 || num > goals.size()) {
            System.out.println("Goal number out of range.");
        } else {
            Goal goal = goals.get(num - 1);
            goal.completed = true;
            System.out.println("Goal marked as completed!");
        }
    }
}
