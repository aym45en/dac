/**
 * Two shoppers adding garlic and potatoes to a shared notepad
 */

import java.util.concurrent.locks.*;

class Shopper extends Thread {

    static int garlicCount, potatoCount = 0;
    static ReentrantLock pencil = new ReentrantLock();

    private void addGarlic() {
        pencil.lock();
        System.out.println("Hold count: " + pencil.getHoldCount());
        garlicCount++;
        pencil.unlock();
    }

    private void addPotato() {
        pencil.lock();
        potatoCount++;
        addGarlic();
        pencil.unlock();
    }
    private void addPotat() {
        pencil.lock();
        potatoCount++;
        addPotato();
        pencil.unlock();
    }

    public void run() {
        for (int i=0; i<10_000; i++) {
            addGarlic();
            addPotato();
            addPotat();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread omar = new Shopper();
        Thread laila = new Shopper();
        omar.start();
        laila.start();
        omar.join();
        laila.join();
        System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
        System.out.println("We should buy " + Shopper.potatoCount + " potatoes.");
    }
}