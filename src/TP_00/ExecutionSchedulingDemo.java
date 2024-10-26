/**
 * Two threads chopping vegetables
 */

class VegetableChopper extends Thread {

    public static boolean chopping = true;
    public int vegetable_count = 0;

    public VegetableChopper(String name) {
        this.setName(name);
    }

    public void run() {
        while (chopping) {
            System.out.println(this.getName() + " chopped a vegetable!");
            vegetable_count++;
        }
    }
}

public class ExecutionSchedulingDemo {
    public static void main(String[] args) throws InterruptedException {
        VegetableChopper A = new VegetableChopper("A");
        VegetableChopper B = new VegetableChopper("B");

        A.start(); // A start chopping
        B.start(); // B start chopping
        Thread.sleep(1000); // continue chopping for 1 second
        VegetableChopper.chopping = false; // stop chopping

        A.join();
        B.join();
        System.out.format("A chopped %d vegetables.\n", A.vegetable_count);
        System.out.format("B chopped %d vegetables.\n", B.vegetable_count);
    }
}