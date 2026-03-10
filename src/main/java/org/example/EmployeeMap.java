package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Employee {
    String  position;
    String name;
    double salary;
    String birthDate;


    public Employee(String position, String name, double salary, String birthDate) {
        this.position = position;
        this.name = name;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "position='" + position + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}

public class EmployeeMap {
    public static void main(String[] args) {

        Map<Integer, Employee> employeeMap = new HashMap<>();

        employeeMap.put(1, new Employee("Manager", "John Doe", 50000, "01.01.1980"));
        employeeMap.put(2, new Employee("Developer", "Jane Smith", 60000, "15.05.1990"));
        employeeMap.put(3, new Employee("Designer", "Alice Johnson", 55000, "20.10.1985"));
        employeeMap.put(4, new Employee("Tester", "Bob Brown", 45000, "30.03.1992"));
        employeeMap.put(5, new Employee("HR", "Charlie Davis", 48000, "12.12.1988"));
        employeeMap.put(6, new Employee("Support", "Eve Wilson", 40000, "25.07.1995"));
        employeeMap.put(7, new Employee("Sales", "Frank Miller", 52000, "05.09.1983"));


        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Position: ");
        String position = scanner.nextLine();

        System.out.println("Enter Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter Birth Date: ");
        String birthDate = scanner.nextLine();

        boolean isDuplicate = false;
        for (var emp: employeeMap.values()) {
            if (emp.name.equalsIgnoreCase(name)) {
                isDuplicate = true;
                break;
            }
        }
        if (employeeMap.containsKey(id)) {
            System.out.println("Employee with this ID already exists.");
        } else if (isDuplicate) {
            System.out.println("Ім'я співробітника вже існує в колекції.");
        } else {
            employeeMap.put (id, new Employee(position, name, salary, birthDate));
            System.out.println("Employee added successfully.");
        }

        System.out.println("Enter ID to edit: ");
        int editId = scanner.nextInt();
        scanner.nextLine();
        if (employeeMap.containsKey(editId)) {
            Employee emp = employeeMap.get(editId);
            System.out.println("Enter new Name: ");
            emp.name = scanner.nextLine();

            System.out.print("Enter new Position: ");
            emp.position = scanner.nextLine();

            System.out.println("Enter new Salary: ");
            emp.salary = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter new Birth Date: ");
            emp.birthDate = scanner.nextLine();

        } else {
            System.out.println("Employee with ID " + editId + " not found.");
        }
        for (var entry : employeeMap.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
        scanner.close();
    }
}