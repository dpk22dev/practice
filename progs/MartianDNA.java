package progs;

import java.util.*;

// chat gpt
public class MartianDNA {
    static final int MOD = 1000000007;

    // Function to multiply two matrices modulo MOD
    static long[][] matrixMultiply(long[][] a, long[][] b, int m) {
        long[][] result = new long[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return result;
    }

    // Function to perform binary exponentiation on matrices
    static long[][] matrixExponentiation(long[][] base, long exp, int m) {
        long[][] result = new long[m][m];
        for (int i = 0; i < m; i++) result[i][i] = 1; // Identity matrix
        while (exp > 0) {
            if ((exp & 1) == 1) result = matrixMultiply(result, base, m);
            base = matrixMultiply(base, base, m);
            exp >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int m = sc.nextInt();
        int k = sc.nextInt();

        // Initialize adjacency matrix
        long[][] adj = new long[m][m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(adj[i], 1); // Initially allow all transitions
        }

        // Read forbidden pairs
        for (int i = 0; i < k; i++) {
            String pair = sc.next();
            int u = getIndex(pair.charAt(0));
            int v = getIndex(pair.charAt(1));
            adj[u][v] = 0; // Mark the pair as forbidden
        }

        // Exponentiate the adjacency matrix to (n-1)
        long[][] result = matrixExponentiation(adj, n - 1, m);

        // Sum all entries in the resulting matrix
        long total = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                total = (total + result[i][j]) % MOD;
            }
        }

        System.out.println(total);
        sc.close();
    }

    // Function to get index of a nucleotide
    static int getIndex(char c) {
        if (c >= 'a' && c <= 'z') return c - 'a';
        else return c - 'A' + 26;
    }
}
