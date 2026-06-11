package org.example;

import java.util.Scanner;

public class Number {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter start of range: ");
        int start = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter end of range: ");
        int end = Integer.parseInt(scanner.nextLine());

        int[] numbers = new int[10];

        creationArrayNumbers(start, end, scanner, numbers);

        System.out.println("All numbers:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
    }

    private static void creationArrayNumbers(int start, int end, Scanner scanner, int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int value = readNumber(start, end, scanner);
            if (i > 0 && value <= numbers[i - 1]) {
                System.out.println("Number must be greater than the previous number (" + numbers[i - 1] + "). Please try again.");
                i--; // Decrement i to repeat this iteration
                continue;
            }
            numbers[i] = value;
            System.out.println("You entered: " + value);
        }
    }

    public static int readNumber(int start, int end, Scanner scanner) {
        int value;

        while (true) {
            System.out.print("Enter a number between " + start + " and " + end + ": ");
            try {
                value = Integer.parseInt(scanner.nextLine());

                if (value < start || value > end) {
                    throw new IllegalArgumentException(
                            "Number must be between " + start + " and " + end);
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}