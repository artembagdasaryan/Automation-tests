package org.example;

public class Rectangle {

    private double width;
    private double height;
    private final double angle = 90.0;

    public Rectangle() {
        this(1.0, 1.0); // Викликаємо інший конструктор
    }

    public Rectangle(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive");
        }
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.height = height;
    }

    public double getAngle() {
        return angle;
    }

    public double calculateArea() {
        return width * height;
    }

    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    public double getDiagonal() {
        return Math.sqrt(width * width + height * height);
    }

    @Override
    public String toString() {
        return "Rectangle{width=" + width + ", height=" + height + ", angle=" + angle + "}";
    }
}