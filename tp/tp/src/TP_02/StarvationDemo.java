
/**
 * Three philosophers, thinking and eating sushi
 */

import java.util.concurrent.locks.*;

class Philosopher1 extends Thread {

    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500000;

    public Philosopher1(String name, Lock firstChopstick, Lock secondChopstick) {
        this.setName(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    public void run() {
        int sushiEaten = 0;
        while (sushiCount > 0) { // eat sushi until it's all gone

            // pick up chopsticks
            firstChopstick.lock();
            secondChopstick.lock();

            try {
                // take a piece of sushi
                if (sushiCount > 0) {
                    sushiCount--;
                    sushiEaten++;
                    System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // put down chopsticks
                secondChopstick.unlock();
                firstChopstick.unlock();
            }
        }
        System.out.println(this.getName() + " took " + sushiEaten);
    }
}

public class StarvationDemo {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        for (int i = 0; i < 5000; i++) {
            new Philosopher1("Omar", chopstickA, chopstickB).start();
            new Philosopher1("Laila", chopstickA, chopstickB).start();
            new Philosopher1("Guest", chopstickA, chopstickB).start();
        }
    }
}