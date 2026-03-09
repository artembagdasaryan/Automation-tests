package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeMap {
    public static void main(String[] args) {

        Map<Integer, String> employeeMap = new HashMap<>();

        employeeMap.put(1, "John Doe");
        employeeMap.put(2, "Jane Smith");
        employeeMap.put(3, "Alice Johnson");
        employeeMap.put(4, "Bob Brown");
        employeeMap.put(5, "Charlie Davis");
        employeeMap.put(6, "Eve Wilson");
        employeeMap.put(7, "Frank Miller");

        for (var entry : employeeMap.entrySet()) {
            System.out.println("Employee ID: " + entry.getKey() + ", Name: " + entry.getValue());
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ID: ");
        int id = scanner.nextInt();

        if (employeeMap.containsKey(id)) {
            System.out.println("Ім'я співробітника: " + employeeMap.get(id));
        } else {
            System.out.println("Співробітник з таким ID не знайдений.");
        }

        scanner.nextLine();

        System.out.print("Введіть ім'я: ");
        String name = scanner.nextLine();

        if (employeeMap.containsValue(name)) {

            for (var entry : employeeMap.entrySet()) {
                if (entry.getValue().equals(name)) {
                    System.out.println("ID співробітника: " + entry.getKey());
                }
            }

        } else {
            System.out.println("Співробітник з таким ім'ям не знайдений.");
        }

        scanner.close();
    }
}