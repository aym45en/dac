
/*
 * Group 2
 * Rabah ROUISSA
 * Mohamed Haythem Abderrahmane BRAHMIA
 * Dibadj BELMOKHI
 */

import java.util.concurrent.CountDownLatch;

class Students extends Thread {

    private static final CountDownLatch mallStation = new CountDownLatch(2);
    private static final CountDownLatch townHallStation = new CountDownLatch(3);
    private static final CountDownLatch subwayStation = new CountDownLatch(5);

    public Students(String name) {
        super(name);
    }

    @Override
    public void run() {
        if (getName().equals("S1") || getName().equals("S2")) {
            System.out.println(getName() + " has arrived at the Mall.");
            try {
                mallStation.countDown();
                mallStation.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(getName() + " has arrived at the Town Hall.");
            try {
                townHallStation.countDown();
                townHallStation.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName() + " has arrived at the Subway Station.");
        try {
            subwayStation.countDown();
            subwayStation.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " is ready to go to the university.");
    }
}

public class CountDownLatchV2 {

    public static void main(String[] args) throws InterruptedException {
        Students[] students = new Students[5];
        for (int i = 0; i < students.length; i++)
            students[i] = new Students("S" + (i + 1));

        for (Students s : students)
            s.start();

        for (Students s : students)
            s.join();
    }
}
