/**
 * Two threads cooking soup
 */

class Chef extends Thread {
    public void run() {
        System.out.println("ChefB started & waiting for chicken to thaw...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ChefB is done cutting chicken.");
    }
}

public class ThreadLifecycleDemo {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("ChefA started & requesting ChefB's help.");
        Thread ChefB = new Chef();
        System.out.println("  ChefB state: " + ChefB.getState());

        System.out.println("ChefA tells ChefB to start.");
        ChefB.start();
        System.out.println("  ChefB state: " + ChefB.getState());

        System.out.println("ChefA continues cooking soup.");
        Thread.sleep(500);
        System.out.println("  ChefB state: " + ChefB.getState());

        System.out.println("ChefA patiently waits for ChefB to finish and join...");
        ChefB.join();
        System.out.println("  ChefB state: " + ChefB.getState());

        System.out.println("ChefA and ChefB are both done!");
    }
}