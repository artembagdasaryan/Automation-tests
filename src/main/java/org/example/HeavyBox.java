package org.example;

import java.util.ArrayList;
import java.util.List;
    class Main1 {
        public static void main(String[] args) {
            List<HeavyBox> heavyBoxes = new ArrayList<>(List.of(
                new HeavyBox(10, "Books"),
                new HeavyBox(20, "Clothes"),
                new HeavyBox(15, "Toys")
            ));
            for (HeavyBox box : heavyBoxes) {
                System.out.println("Weight: " + box.getWeight() + ", Content: " + box.getContent());
            }
            //heavyBoxes.forEach(box ->
            //        System.out.println("Weight: " + box.getWeight() + ", Content: " + box.getContent())
            //);

            heavyBoxes.get(0).setWeight(11);
            System.out.println("After updating the box at index 0:");
            System.out.println(heavyBoxes);

            heavyBoxes.removeLast();
            System.out.println("After removing the box at index 2:");
            System.out.println(heavyBoxes);

            heavyBoxes.clear();
            System.out.println("After clearing the list:");
            System.out.println(heavyBoxes);

        }

        public static class HeavyBox {
            private int weight;
            private String content;

            public HeavyBox(int weight, String content) {
                this.weight = weight;
                this.content = content;
            }

            @Override
            public String toString() {
                return "HeavyBox{" +
                        "weight=" + weight +
                        ", content='" + content + '\'' +
                        '}';
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
