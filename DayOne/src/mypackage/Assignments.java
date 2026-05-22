package mypackage;
import java.util.Scanner;

public class Assignments {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int assignmentNumber;

        do {
            System.out.println("\n===== ASSIGNMENTS MENU =====");
            System.out.println("1. Blackjack");
            System.out.println("2. Days of the Week");
            System.out.println("3. Loops");
            System.out.println("4. Pattern Matching");
            System.out.println("5. Exit");

            System.out.print("What assignment do you want to run? (1-5): ");
            assignmentNumber = scanner.nextInt();

            switch (assignmentNumber) {

                case 1:
                    runBlackjack();
                    break;

                case 2:
                    daysOfTheWeek();
                    break;

                case 3:
                    loops();
                    break;

                case 4:
                    patternMatching();
                    break;

                case 5:
                    System.out.println("Program ended.");
                    break;

                default:
                    System.out.println("Invalid assignment number. Please enter 1-5.");
            }

        } while (assignmentNumber != 5);

        scanner.close();
    }

    // =========================
    // BLACKJACK
    // =========================
    public static void runBlackjack() {

        System.out.println("\n=== Blackjack ===");

        System.out.println(blackJack(1, 22));
        System.out.println(blackJack(21, 22));
        System.out.println(blackJack(22, 22));
        System.out.println(blackJack(2, 10));
    }

    public static int blackJack(int a, int b) {

        if (a > 21 && b > 21) {
            return 0;
        } else if (a > 21) {
            return b;
        } else if (b > 21) {
            return a;
        } else {
            return Math.max(a, b);
        }
    }

    // =========================
    // DAYS OF THE WEEK
    // =========================
    public static void daysOfTheWeek() {

        int day;

        do {

            System.out.print("Hello! Please enter a number from 1-7: ");
            day = scanner.nextInt();

            switch(day) {

                case 1:
                    System.out.println("Monday");
                    break;

                case 2:
                    System.out.println("Tuesday");
                    break;

                case 3:
                    System.out.println("Wednesday");
                    break;

                case 4:
                    System.out.println("Thursday");
                    break;

                case 5:
                    System.out.println("Friday");
                    break;

                case 6:
                    System.out.println("Saturday");
                    break;

                case 7:
                    System.out.println("Sunday");
                    break;

                default:
                    System.out.println("Invalid day! Please try again!");
            }

        } while(day > 7 || day < 1);
    }

    // =========================
    // LOOPS
    // =========================
    public static void loops() {

        int n;
        int i=0;
        int j=0;
        boolean restart;

        do {

            do {
                System.out.print("Enter a number (1-20): ");
                n = scanner.nextInt();

                if (n < 1 || n > 20) {
                    System.out.println("Invalid. Please try again!");
                }

            } while (n < 1 || n > 20);

            // Pattern
            for ( i = 0; i < n; i++) {

                for ( j = 1; j <= i + 1; j++) {
                    System.out.print(j + " ");
                }

                System.out.println();
            }

            System.out.print("Do you want to try again? (true/false): ");
            restart = scanner.nextBoolean();

        } while(restart);
    }

    // =========================
    // PATTERN MATCHING
    // =========================
    public static void patternMatching() {

    int day;

    do {

        System.out.print("Hello! Please enter a number from 1-7: ");
        day = scanner.nextInt();

        switch (day) {

            case 1 -> System.out.println("Monday");
            case 2 -> System.out.println("Tuesday");
            case 3 -> System.out.println("Wednesday");
            case 4 -> System.out.println("Thursday");
            case 5 -> System.out.println("Friday");
            case 6 -> System.out.println("Saturday");
            case 7 -> System.out.println("Sunday");

            default -> System.out.println("Invalid day! Please try again!");
        }

    } while (day < 1 || day > 7);
}}