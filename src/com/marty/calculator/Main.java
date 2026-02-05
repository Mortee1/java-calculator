package com.marty.calculator;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        int lastResult = 0;

        while (true) {
            printMenu();
            int choice = readMenuChoice();

            if (choice == 0) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }

            int a = 0, b = 0;
            if (choice >= 1 && choice <= 4) {
                a = readNumber("Number 1: ");
                b = readNumber("Number 2: ");
            }

            try {
                switch (choice) {
                    case 1 -> {
                        lastResult = calculator.add(a, b);
                        System.out.println("Result: " + lastResult);
                    }
                    case 2 -> {
                        lastResult = calculator.subtract(a, b);
                        System.out.println("Result: " + lastResult);
                    }
                    case 3 -> {
                        lastResult = calculator.multiply(a, b);
                        System.out.println("Result: " + lastResult);
                    }
                    case 4 -> {
                        lastResult = calculator.divide(a, b);
                        System.out.println("Result: " + lastResult);
                    }
                    default -> System.out.println("Unexpected choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            if (!askToContinue()) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }
        }

        input.close();
    }

    private static void printMenu() {
        System.out.println("\nSimple Calculator Program");
        System.out.println("---------------------------");
        System.out.println("""
                [1] Add
                [2] Subtract
                [3] Multiply
                [4] Divide
                [0] Exit Program
                """);
        System.out.println("---------------------------");
    }

    private static int readMenuChoice() {
        while (true) {
            try {
                System.out.print("Select option: ");
                int choice = input.nextInt();
                input.nextLine();

                if (choice < 0 || choice > 4) {
                    System.out.println("Selected option is unavailable. Try again.");
                    continue;
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }
    }

    private static int readNumber(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int number = input.nextInt();
                input.nextLine();
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                input.nextLine();
            }
        }
    }

    private static boolean askToContinue() {
        System.out.print("Start again? [Y]/[N]: ");
        String choice = input.nextLine().trim();

        if (choice.equalsIgnoreCase("N")) {
            return false;
        }

        if (!choice.equalsIgnoreCase("Y")) {
            System.out.println("Invalid input. Continuing program.");
        }

        return true;
    }
}
