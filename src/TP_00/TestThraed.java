
class FirstThread extends Thread {
    public int a;
    public int b;
    public int res;

    public FirstThread(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void run() {
        this.res = a + b;

    }
}

class SecThread implements Runnable {
    public int c;
    public int d;
    public int res;

    public SecThread(int c, int d) {
        this.c = c;
        this.d = d;
    }

    public void run() {
        res = c * d;
    }
}

public class TestThraed {
    public static void main(String[] args) throws Exception {

        FirstThread f = new FirstThread(8,6);
        SecThread s = new SecThread(12,15);
        Thread sec = new Thread(s);

        f.start();
        sec.start();

        f.join();
        sec.join();

        System.out.println(f.res / s.res);
    }
}
