
import java.util.concurrent.*;

class Matrix implements Runnable {
    int[][] A;
    int[][] B;
    int[][] C;
    int i;

    public Matrix(int[][] A, int[][] B, int[][] C, int i) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.i = i;
    }

    public void run() {
        for (int j = 0; j < A[i].length; j++) {
            C[i][j] = A[i][j] + B[i][j];
        }
    }
}

public class ThreadPoolDemo {
    public static void main(String args[]) throws InterruptedException {
        int numProcs = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(numProcs);
        int[][] A = { { 1, 1, 1, 1, 1 }, { 2, 2, 2, 2, 2 }, { 3, 3, 3, 3, 3 }, { 4, 4, 4, 4, 4 } };
        int[][] B = { { 1, 1, 1, 1, 1 }, { 2, 2, 2, 2, 2 }, { 3, 3, 3, 3, 3 }, { 4, 4, 4, 4, 4 } };
        int[][] C = new int[4][5];
        for (int i = 0; i < A.length; i++)
            pool.submit(new Matrix(A, B, C, i));
        pool.shutdown();
        pool.awaitTermination(numProcs, TimeUnit.SECONDS);
        System.out.println("Result matrix is:");
        for (int[] row : C) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}