package org.example;

import java.util.Scanner;

class Plant {
    enum Color {RED, GREEN, YELLOW, BLUE, PURPLE}

    enum Type {FLOWER, TREE, SHRUB, GRASS, FERN}

    private int size;
    private Color color;
    private Type type;

    public Plant(int size, String color, String type) {

        this.color = validateColor(color);
        this.type = validateType(type);
        this.size = validateSize(size, this.type);
    }

    private static int validateSize(int size, Type type) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        boolean valid = switch (type) {
            case FLOWER -> size <= 100;
            case TREE -> size <= 1000;
            case SHRUB -> size <= 500;
            case GRASS -> size <= 50;
            case FERN -> size <= 200;
        };
        if (!valid) {
            throw new IllegalArgumentException("Size is too large for type: " + type);
        }
        return size;
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
                ", color=" + color +
                ", type=" + type +
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
            } catch (IllegalArgumentException e) {
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
