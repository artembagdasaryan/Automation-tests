package org.example;
import java.util.Scanner;

public class oddNumbers {

    public static boolean isOdd(int number) {
        return number % 2 == 1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введи перше число: ");
        int number1 = scanner.nextInt();

        System.out.print("Введи друге число: ");
        int number2= scanner.nextInt();

        System.out.print("Введи третє число: ");
        int number3 = scanner.nextInt();

        int count = 0;

        if (isOdd(number1)) count ++;
        if (isOdd(2)) count ++;
        if (isOdd(number3)) count ++;

        System.out.println("Кількість парних чисел:" + count);

        scanner.close();
    }
}


