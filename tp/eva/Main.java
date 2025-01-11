/*
 * rouissa rabah g2
 * Brahmia Mohamed haythem abderrahmane g2
 * Belmokhi Dibadj g2
 */

/*
 *
 * 
 *       qst 1
 * the most suitable lock for this situation is ReentrantReadWriteLock
 * because ReentrantReadWriteLock is give us a 2 methods readLock writeLock for creating 2 lock
 * when we craete lock by writeLock only one writer can update the value of shared resource 
 * and all locks that create by readLock can read at the same time normally
 * so by this mechanism we controle the library system who can read and write and when so we don't fall in deadlock or othr problems;
 * 
 *      qst 2
 * the difference between ReadWriteLock and ReentrantLock is that ReadWriteLock give as 2 locks so we can control who 
 * can update resource and who can read and when 
 * so all raeders can read until one writer start to writer and only that one can write 
 * but it but in ReentrantLock we don't differ between readers and writers 
 * all thread had to wait for each lock not like ReadWriteLock all readers can read in same time 
 * 
 * 
 *      qst3
 * the  mechanisme we can use to ensure it works correctly is the tryLock methode
 * 
 */



import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Library extends Thread {
    public static String book = "-1";
    public static int co = 0;

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static Lock readLock = lock.readLock();
    private static Lock writeLock = lock.writeLock();

    public Library(String name) {
        this.setName(name);
    }

    @Override
    public void run() {

        if (this.getName().contains("writer")) {
            writeLock.lock();
            try {
                co++;
                Random random = new Random();
                book = "book name " + random.nextInt(300);
                System.out.println(this.getName() + "I write the " + book);
                try {
                    Thread.sleep(random.nextInt(300));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                writeLock.unlock();
            }
        } else {
            readLock.lock();
            try {
                System.out.println(this.getName() + "I read the " + book);
                Random random = new Random();
                try {
                    Thread.sleep(random.nextInt(300));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                readLock.unlock();
            }
        }

    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        for (int index = 0; index < 6; index++) {
            new Library("reader" + index).start();

        }
        for (int index = 0; index < 2; index++) {
            new Library("writer" + index).start();
        }
       
    }
}
