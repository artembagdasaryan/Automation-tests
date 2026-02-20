package org.example;

public class Main {

    public static void main(String[] args) {

        Departament departament1 =
                new Departament("HR",
                        new Departament.Address("Kyiv", "Shevchenko", 10));

        Departament departamentClone = departament1.clone();

        System.out.println("Original: " + departament1);
        System.out.println("Clone: " + departamentClone);

        departamentClone.address.city = "Lviv";

        System.out.println("Original: " + departament1);
        System.out.println("Clone: " + departamentClone);
    }

    static class Departament implements Cloneable {

        String name;
        Address address;

        public Departament(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        static class Address implements Cloneable {
            String city;
            String street;
            int buildingNumber;

            public Address(String city, String street, int buildingNumber) {
                this.city = city;
                this.street = street;
                this.buildingNumber = buildingNumber;
            }

            @Override
            public Address clone() {
                try {
                    return (Address) super.clone();
                } catch (CloneNotSupportedException e) {
                    throw new AssertionError();
                }
            }

            @Override
            public String toString() {
                return "Address{" +
                        "city='" + city + '\'' +
                        ", street='" + street + '\'' +
                        ", buildingNumber=" + buildingNumber +
                        '}';
            }
        }

        @Override
        public Departament clone() {
            try {
                Departament result = (Departament) super.clone();
                result.address = address.clone(); // deep copy
                return result; // ✅ повертаємо result, а не новий clone
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }

        @Override
        public String toString() {
            return "Departament{" +
                    "name='" + name + '\'' +
                    ", address=" + address +
                    '}';
        }
    }
}