package org.example;

public class Main {
    public static void main(String[] args) {

        Bird[] birds = {
                new Eagle(),
                new Swallow(),
                new Penguin(),
                new Kiwi()
        };

        for (Bird b : birds) {
            System.out.println("Bird: " + b.getClass().getSimpleName());
            System.out.println("Can fly: " + b.fly());
            System.out.println();
        }
    }
}

// ====================== ABSTRACT BIRD ======================
abstract class Bird {
    protected boolean feathers;
    protected boolean layEggs;

    public Bird(boolean feathers, boolean layEggs) {
        this.feathers = feathers;
        this.layEggs = layEggs;
    }

    public abstract boolean fly();
}

// ====================== FLYING BIRD ========================
abstract class FlyingBird extends Bird {
    public FlyingBird() {
        super(true, true);
    }

    @Override
    public boolean fly() {
        return true;
    }
}

// =================== NON-FLYING BIRD =======================
abstract class NonFlyingBird extends Bird {
    public NonFlyingBird() {
        super(true, true);
    }

    @Override
    public boolean fly() {
        return false;
    }
}

// ====================== SPECIFIC BIRDS ======================
class Eagle extends FlyingBird {}
class Swallow extends FlyingBird {}

class Penguin extends NonFlyingBird {}
class Kiwi extends NonFlyingBird {}
