package evaliation;
/*
 * 
 * ROUISSSA RABAH G2
 * BRAHMIA MOHAMED ABD ERRAHMAN HAYTHEM G2  abcent
 * BELMOKHI DIBADJ G2
 * 
 */


class Bank extends Thread {
    public static double balance;

    public Bank(String name) {
        super(name);
    }

    public void run() {
        // critical section of code is that both thraeds accesses and update the shared resource (balance)

        if (this.getName() == "Husband") {
            for (int i = 0; i < 100_000; i++) {
                deposits(10_000);
            }
        } else {
            for (int i = 0; i < 100_000; i++) {
                withdrawls(10_000);
            }
        }
    }

    // the tchnique we used to ensure synchronization is by using method synchronized
    // and the way it works is that it locks the object that is calling the method and
    // no other object can access it until the lock is released

    private static  void withdrawls(double amount) {
        balance -= amount;
    }

    private static  void deposits(double amount) {
        balance += amount;
    }

}

public class Main {
    public static void main(String[] args) throws Exception {
        Bank hus = new Bank("Husband");
        Bank wife = new Bank("Wife");
        hus.start();
        wife.start();
        hus.join();
        wife.join();

        // if we dont use synchronized method the final balance will be incorrect .like in this code 
        // if we remove synchronized method we will have wrong balance value
        // like if husband deposit 10000 and wife withdraw 10000 for 10 times each  the final balance should be 0 but without 
        // synchronized method the value will be diffrent like -256985416 or 1.227E7 ... and
        // the wrong value it's not fixed value because it's depend on the time that each thread take to access the shared resource
        
        System.out.println("Final balance: " + Bank.balance);
    }
}
