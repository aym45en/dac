/**
 * Two threads calclate sum 1+...+100
 */

class Sum extends Thread {

    public int count = 0;
    public int start;

    public Sum(String name, int start) {
        this.setName(name);
        this.start = start;
    }

    public void run() {
        for (int index = start; index < start + 50; index++) {
            count += index;
        }
    }
}

public class Cal_sum {
    public static void main(String[] args) throws InterruptedException {
        Sum A = new Sum("A", 1);
        Sum B = new Sum("B", 51);

        A.start();
        B.start();

        A.join();
        B.join();

        System.out.println("A count: " + A.count);
        System.out.println("B count: " + B.count);
        System.out.println("sum A + B: " + (A.count + B.count));
    }
}