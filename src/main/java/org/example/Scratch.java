package org.example;

public class Scratch {
    public  static void main(String[] args) {
        Car[] cars = new Car[3];
        cars[0] = new Truck("Volvo", 120, 2015);
        cars[1] = new Sedan("Toyota", 180, 2018);
        cars[2] = new Truck("Scania", 110, 2012);

        for (var car : cars) {
            System.out.println(car);
        }
        for (var car : cars) {
            car.run();
        }
        for (var car : cars) {
            car.stop();
        }
    }
}
abstract class Car {
    private String model;
    private int maxSpeed;
    private int yearOfProduction;

    public Car(String model, int maxSpeed, int yearOfProduction) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public String toString() {
        return "{" +
                "yearOfProduction=" + yearOfProduction +
                ", maxSpeed=" + maxSpeed +
                ", model='" + model + '\'' +
                '}';
    }

    public String getModel() {
        return model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    abstract void run();
    abstract void stop();

}

class Truck extends Car {
    public Truck (String model, int maxSpeed, int yearOfProduction) {
        super(model, maxSpeed, yearOfProduction);
    }

    @Override
    void run() {
        System.out.println("Truck " + getModel() + " runs at max speed of " + getMaxSpeed() + " km/h.");
    }

    @Override
    void stop() {
        System.out.println("Truck " + getModel() + " has stopped.");
    }

    @Override
    public String toString() {
        return "Truck " + super.toString();
    }
}

class Sedan extends Car {
    public Sedan(String model, int maxSpeed, int yearOfProduction) {
        super(model, maxSpeed, yearOfProduction);
    }

    @Override
    void run() {
        System.out.println("Sedan " + getModel() + " is running.");
    }

    @Override
    void stop() {
        System.out.println("Sedan " + getModel() + " has stopped.");
    }

    @Override
    public String toString() {
        return "Sedan " + super.toString();
    }
}