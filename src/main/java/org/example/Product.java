package org.example;

public class Product {
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName() {return name;}
    public double getPrice() {return price;}
    public int getQuantity() {return quantity;}


    /*Метод для пошуку продукту з більшою ціною
    public static Product maxByPrice(Product a, Product b) {
        return a.getPrice() >= b.getPrice() ? a : b;
    }

    // Метод для пошуку продукту з більшою кількістю
    public static Product maxByQuantity(Product a, Product b) {
        return a.getQuantity() >= b.getQuantity() ? a : b;
    }

    public static void main(String[] args) {
        Product p1 = new Product("Laptop", 1200.50, 10);
        Product p2 = new Product("Phone", 800.00, 15);
        Product p3 = new Product("Tablet", 400.99, 5);
        Product p4 = new Product("Headphones", 150.25, 123);

        // Знаходимо найдорожчий продукт
        Product mostExpensive = maxByPrice(maxByPrice(p1, p2), maxByPrice(p3, p4));
        System.out.println("Найдорожчий продукт: " + mostExpensive.getName() +
                " - " + mostExpensive.getQuantity() + " шт, ціна: $" + mostExpensive.getPrice());

        // Знаходимо продукт з найбільшою кількістю
        Product biggestQuantity = maxByQuantity(maxByQuantity(p1, p2), maxByQuantity(p3, p4));
        System.out.println("Продукт з найбільшою кількістю: " + biggestQuantity.getName() +
                " - " + biggestQuantity.getQuantity() + " шт");
    }
}*/

    public static void main(String[] args) {
        // створюємо 4 інстанси (об’єкти) класу Product
        Product p1 = new Product("Laptop", 1200.50, 10);
        Product p2 = new Product("Phone", 800.00, 15);
        Product p3 = new Product("Tablet", 400.99, 5);
        Product p4 = new Product("Headphones", 150.25, 123);

        Product maxProduct = p1;
        if (p2.getPrice() > p1.getPrice()) {
            maxProduct = p2;
        }
        if (p3.getPrice() > p2.getPrice()) {
            maxProduct = p3;
        };
        if (p4.getPrice() > p3.getPrice()) {
            maxProduct = p4;
        };
        System.out.println("Найбільш дорожчий продукт : " + maxProduct.getName() + " - " + maxProduct.getQuantity()
        + " шт");

        if (p2.getQuantity() > p1.getQuantity()) {
            maxProduct = p2;
        }
        if (p3.getQuantity() > p2.getQuantity()) {
            maxProduct = p3;
        };
        if (p4.getQuantity() > p3.getQuantity()) {
            maxProduct = p4;
        };
        System.out.println("Найбільшу кількість має продукт : " + maxProduct.getName() + " - " + maxProduct.getQuantity()
                + " шт");
    };
}
