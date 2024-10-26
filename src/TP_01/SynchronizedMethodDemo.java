/**
 * Two shoppers adding items to a shared notepad
 */
class Shopper extends Thread {

    static int garlicCount = 0;

    private static synchronized void addGarlic() {
        garlicCount++;
    }

    public void run() {
        for (int i=0; i<10_000; i++)
            addGarlic();
    }
}

public class SynchronizedMethodDemo {
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