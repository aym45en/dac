
import java.util.concurrent.locks.*;
class ArrayModifierThread extends Thread {

    private static int[] array = { 0, 0, 0, 0, 0 };
    private int index;
    private static ReentrantLock lock = new ReentrantLock();

    public ArrayModifierThread(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100_000_000; i++) {
            lock.lock();
            array[index]++;   
            lock.unlock();        
        }
        System.out.println(Thread.currentThread().getName() + " finished");


    }

    public static void printArray() {
        System.out.print("Final array state: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

public class DataRaceExample {
    public static void main(String[] args) {

        ArrayModifierThread t1 = new ArrayModifierThread(0);
        ArrayModifierThread t2 = new ArrayModifierThread(0);
        ArrayModifierThread t3 = new ArrayModifierThread(1);
        ArrayModifierThread t4 = new ArrayModifierThread(2);

        // Start the threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Wait for all threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final state of the array
        ArrayModifierThread.printArray();
    }
}
