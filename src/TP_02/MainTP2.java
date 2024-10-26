
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private int balance;
    public Lock lock = new ReentrantLock();

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(int amount) {
        lock.lock();
        try {
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }
}

class AccountTransferThread extends Thread {
    private BankAccount sourceAccount, targetAccount;
    private int amount;

    public AccountTransferThread(BankAccount account1, BankAccount account2, int amount) {
        this.sourceAccount = account1;
        this.targetAccount = account2;
        this.amount = amount;
    }

    @Override
    public void run() {
        while (sourceAccount.getBalance() >= amount) {
            sourceAccount.lock.lock();
            if (targetAccount.lock.tryLock()) {
                try {
                    sourceAccount.withdraw(amount);
                    targetAccount.deposit(amount);
                } finally {
                    targetAccount.lock.unlock();
                    sourceAccount.lock.unlock();
                }
            } else {
                sourceAccount.lock.unlock();
            }
            
        }
    }
}

public class MainTP2 {
    public static void main(String[] args) {

        BankAccount account1 = new BankAccount(100_000_000);
        BankAccount account2 = new BankAccount(200_000_000);
        BankAccount account3 = new BankAccount(300_000_000);

        AccountTransferThread transfer1 = new AccountTransferThread(account1, account2, 1_000_000);
        AccountTransferThread transfer2 = new AccountTransferThread(account2, account3, 1_000_000);
        AccountTransferThread transfer3 = new AccountTransferThread(account3, account1, 1_000_000);

        transfer1.start();
        transfer2.start();
        transfer3.start();

        try {
            transfer1.join();
            transfer2.join();
            transfer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());
        System.out.println("Account 2 balance: " + account3.getBalance());
    }
}
