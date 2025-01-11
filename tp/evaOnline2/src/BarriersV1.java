
/*
 * Group 2
 * Rabah ROUISSA
 * Mohamed Haythem Abderrahmane BRAHMIA
 * Dibadj BELMOKHI
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Student extends Thread {

    private CyclicBarrier nextStation;
    private CyclicBarrier subwayStation;

    public Student(String name, CyclicBarrier nextStation, CyclicBarrier subwayStation) {
        this.setName(name);
        this.nextStation = nextStation;
        this.subwayStation = subwayStation;
    }

    @Override
    public void run() {
        if (this.getName().equals("S1") || this.getName().equals("S2")) {
            System.out.println(getName() + " has arrived at the Mall.");
            try {
                nextStation.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(getName() + " has arrived at the Town Hall.");
            try {
                nextStation.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        System.out.println(getName() + " has arrived at the Subway Station.");

        try {
            subwayStation.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println(getName() + " is ready to go to the university.");
    }
}

public class BarriersV1 {
    public static void main(String args[]) throws InterruptedException {

        Student[] student = new Student[5];
        CyclicBarrier mallStation = new CyclicBarrier(2);
        CyclicBarrier townHallStation = new CyclicBarrier(3);
        CyclicBarrier subwayStation = new CyclicBarrier(5);
        for (int i = 0; i < student.length; i++) {
            if (i < 2)
                student[i] = new Student("S" + (i + 1), mallStation, subwayStation);
            else
                student[i] = new Student("S" + (i + 1), townHallStation, subwayStation);
        }
        for (Student s : student)
            s.start();

        for (Student s : student)
            s.join();
    }
}