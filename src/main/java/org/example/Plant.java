package org.example;

import java.util.Scanner;

class Plant {
    enum Color {RED, GREEN, YELLOW, BLUE, PURPLE}

    enum Type {FLOWER, TREE, SHRUB, GRASS, FERN}

    private int size;
    private Color color;
    private Type type;

    public Plant(int size, String color, String type) {

        this.size = size;
        this.color = validateColor(color);
        this.type = validateType(type);
    }

    private static Type validateType(String type) {
        try {
           return Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TypeException("Invalid type: " + type);
        }
    }

    private static Color validateColor(String color) {
        try {
            return Color.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ColorException("Invalid color: " + color);
        }
    }

    @Override
    public String toString() {
        return "Plant{" +
                "size=" + size +
                ", Color=" + color +
                ", Type=" + type +
                '}';
    }

    public static void main(String[] args) {
        Plant[] plants = new Plant[2];
        var scanner = new Scanner(System.in);
        for (int i = 0; i < plants.length; i++) {
            try {
                System.out.println("Enter size:");
                int size = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter color:");
                String color = scanner.nextLine();
                System.out.println("Enter type:");
                String type = scanner.nextLine();
                plants[i] = new Plant(size, color, type);
            } catch (NumberFormatException e) {
                System.out.println("Invalid size. Please enter an integer");
                i--;
            } catch (ColorException | TypeException e) {
                System.out.println(e.getMessage());
                i--;
            }
        }
        for (Plant plant : plants) {
            System.out.println(plant);
        }
        scanner.close();
    }
}
class ColorException extends RuntimeException {
    public ColorException(String message) {
        super(message);
    }
}

class TypeException extends RuntimeException {
    public TypeException(String message) {
        super(message);
    }
}
