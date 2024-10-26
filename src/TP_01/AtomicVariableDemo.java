/**
 * Two shoppers adding items to a shared notepad
 */

import java.util.concurrent.atomic.*;

class Shopper extends Thread {

    static AtomicInteger garlicCount = new AtomicInteger(0);

    public void run() {
        for (int i=0; i<10_000_000; i++)
            garlicCount.incrementAndGet();
    }
}

public class AtomicVariableDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread omar = new Shopper();
        Thread laila = new Shopper();
        omar.start();
        laila.start();
        omar.join();
        laila.join();
        System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
    }
}