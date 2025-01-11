class CalPrime {

    public static void main(String[] args) throws InterruptedException {
        long a = 101027;
        long b = 8537;
        if (isPrime(a) && isPrime(b)) {
            long n = a * b;

            FirstH f = new FirstH(3, n / 2, n);
            SecH s = new SecH(n / 2 + 1, n, n);
             
            f.start();
            // s.start();
            f.join();
            s.join();

        } 
    }

    public static boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (long i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

class FirstH extends Thread {
    private long start, end, n;

    public FirstH(long start, long end, long n) {
        this.start = start;
        this.end = end;
        this.n = n;
    }

    public void run() {
        for (long i = start; i <= end ; i++) {
            System.out.println(i);
            if (CalPrime.isPrime(i) && n % i == 0) {
                System.out.println("\nthe solution:");
                System.out.println(n / i);
                System.out.println(i);
                break;
            }
        }
    }
}

class SecH extends Thread {
    private long start, end, n;

    public SecH(long start, long end, long n) {
        this.start = start;
        this.end = end;
        this.n = n;
    }

    public void run() {
        for (long i = start; i <= end ; i++) {
            System.out.println("            "+i);
            if (CalPrime.isPrime(i) && n % i == 0) {
                System.out.println("\nthe solution:");
                System.out.println(n / i);
                System.out.println(i);
                break;
            }
        }
    }
}