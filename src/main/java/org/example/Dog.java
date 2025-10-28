package org.example;

public class Dog {
    String name;
    Breed breed;
    int age;

    public Dog(String name, Breed breed, int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Breed getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public enum Breed {
        French_Bulldog, Labrador_Retriever, Golden_Retriever, German_Shepherd_Dog
    }

    @Override
    public String toString() {
        return name + ", " + breed + ", " + age + " років";
    }

    public static Dog maxByAge(Dog a, Dog b) {
        return a.getAge() >= b.getAge() ? a : b;
    }

    public static void main(String[] args) {
        // створюємо 4 інстанси (об’єкти) класу Dog
        Dog d1 = new Dog("Arch", Breed.German_Shepherd_Dog, 7);
        Dog d2 = new Dog("Charlie", Breed.French_Bulldog, 12);
        Dog d3 = new Dog("Arch", Breed.Golden_Retriever, 10);

        // Знаходимо найстарішу собаку
        Dog oldest = maxByAge(maxByAge(d1, d2), d3);
        System.out.println("Найбільший вік має: " + oldest.toString());

        // Порівнюємо імена собак
        if (!d1.getName().equals(d2.getName()) &&
                !d1.getName().equals(d3.getName()) &&
                !d2.getName().equals(d3.getName())) {
            System.out.println("Усі імена різні");
        } else {
            System.out.println("Є однакові імена");
        };
    };
};
