/**
 * Two shoppers adding items to a shared notepad
 */

import java.util.concurrent.locks.*;

class Shopper extends Thread {

    private int itemsToAdd = 0; // items this shopper is waiting to add
    private static int itemsOnNotepad = 0; // total items on shared notepad
    private static ReentrantLock pencil = new ReentrantLock();

    public Shopper(String name) {
        this.setName(name);
    }

    public void run() {
        while (itemsOnNotepad <= 20){
            if (itemsToAdd > 0 && pencil.tryLock()) { // add item(s) to shared notepad
                try {
                    itemsOnNotepad += itemsToAdd;
                    System.out.println(this.getName() + " added " + itemsToAdd + " item(s) to notepad.");
                    itemsToAdd = 0;
                    Thread.sleep(300); // time spent writing
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    pencil.unlock();
                }
            } else { // look for other things to buy
                try {
                    Thread.sleep(100); // time spent searching
                    itemsToAdd++;
                    System.out.println(this.getName() + " found something to buy.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class TryLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread omar = new Shopper("Omar");
        Thread laila = new Shopper("Laila");
        long start = System.currentTimeMillis();
        omar.start();
        laila.start();
        omar.join();
        laila.join();
        long finish = System.currentTimeMillis();
        System.out.println("Elapsed Time: " + (float)(finish - start)/1000 + " seconds");
    }
}
