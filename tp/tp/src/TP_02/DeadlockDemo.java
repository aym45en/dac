
/**
 * Three philosophers, thinking and eating sushi
 */

import java.util.concurrent.locks.*;

class Philosopher2 extends Thread {

    private Lock firstChopstick, secondChopstick;
    private static int sushiCount = 500000;

    public Philosopher2(String name, Lock firstChopstick, Lock secondChopstick) {
        this.setName(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    public void run() {
        while (sushiCount > 0) { // eat sushi until it's all gone
            try {
                // pick up chopsticks
                firstChopstick.lock();
                secondChopstick.lock();

                // take a piece of sushi
                if (sushiCount > 0) {
                    sushiCount--;
                    System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
                }

            } finally {
                // put down chopsticks
                secondChopstick.unlock();
                firstChopstick.unlock();
            }
        }
    }
}

public class DeadlockDemo {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        new Philosopher2("Omar", chopstickA, chopstickB).start();
        new Philosopher2("Laila", chopstickB, chopstickC).start();
        new Philosopher2("Guest", chopstickC, chopstickA).start();
    }
}