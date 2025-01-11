
import java.util.concurrent.*;

class Write extends Thread {
    private Semaphore chr;
    private Semaphore nextChar;

    private String l;

    public Write(String l, Semaphore chr, Semaphore nextChar) {
        this.l = l;
        this.chr = chr;
        this.nextChar = nextChar;
    }

    public void run() {
        try {
            chr.acquire();
            System.out.print(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            nextChar.release();
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Semaphore semaH = new Semaphore(1);
        Semaphore semaE = new Semaphore(0);
        Semaphore semaL = new Semaphore(0);
        Semaphore semaO = new Semaphore(0);
        for (int index = 0; index < 100; index++) {
            new Write("H", semaH, semaE).start();
            new Write("E", semaE, semaL).start();
            new Write("L", semaL, semaL).start();
            new Write("L", semaL, semaO).start();
            new Write("o\n", semaO, semaH).start();
        }
    }
}
