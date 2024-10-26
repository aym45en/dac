// File: ThreadLifecycleDemo.java

class LifecycleThread extends Thread {
    public void run() {
        System.out.println("Thread is running.");
        try {
            Thread.sleep(10_000); // Simulate some work with sleep
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted.");
        }
        System.out.println("Thread is finishing.");
    }
}

public class ThreadLifecycleDemo2 {
    public static void main(String[] args) {
        System.out.println("Thread lifecycle demo started.");

        // Create a new thread
        LifecycleThread thread = new LifecycleThread();
        System.out.println("Thread state after creation and before start(): " + thread.getState());

        // Start the thread
        System.out.println("call start()");
        thread.start();
        System.out.println("Thread state after calling start(): " + thread.getState());
        try {
            Thread.sleep(5_000); // Simulate some work with sleep
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Wait for the thread to finish
        try {
            System.out.println("Thread state befor calling join() and sleep() in running in run(): " + thread.getState());
            thread.join();
            System.out.println("Thread state after calling join(): " + thread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread lifecycle demo finished.");
    }
}