package org.example;

import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть розмір масиву: ");
        int size = scanner.nextInt();

        if (size <= 0) {
            System.out.println("Розмір масиву має бути додатним числом!");
            scanner.close();
            return;
        }

        int[] numbers1 = new int[size];

        System.out.println("Введіть " + size + " чисел:");
        for (int i = 0; i < size; i++) {
            numbers1[i] = scanner.nextInt();
        }

        double summ = 0;
        for (int j : numbers1) {
            summ += j;
        }

        double average = summ / numbers1.length;
        System.out.println("Середнє арифметичне: " + average);

        System.out.print("Введіть число для пошуку в масиві: ");
        int value = scanner.nextInt();

        boolean found = contains(numbers1, value);

        if (found) {
            System.out.println("Є таке число: " + value);
        } else {
            System.out.println("Такого числа немає: " + value);
        }

        System.out.print("Введіть додатнє число: ");
        int positiveNumber = scanner.nextInt();

        if (positive_integer(positiveNumber)) {
            System.out.println("Число додатне і парне.");
        } else {
            if (positiveNumber <= 0) {
                System.out.println("Це не додатне число " + positiveNumber);
            } else {
                System.out.println("Число не парне.");
            }
        }

        scanner.close();
    }

    public static boolean contains(int[] array, int value) {
        for (int num : array) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean positive_integer(int number) {
        return number > 0 && number % 2 == 0;
    }
}
