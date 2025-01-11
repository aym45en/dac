public class GfG {
    public static void add(int[][] A, int[][] B, int[][] C) {
        int n = A.length;
        int m = A[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
    }

    public static void main(String[] args) {
        int[][] A = { {1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {4, 4, 4} };
        int[][] B = { {1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {4, 4, 4} };
        int[][] C = new int[4][3];
        add(A, B, C);

        System.out.println("Result matrix is:");
        for (int[] row : C) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}