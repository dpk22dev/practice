package progs;

import java.util.Scanner;

//https://www.codechef.com/JAN221B/problems/RIFFLES
public class Riffles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of test cases
        int T = scanner.nextInt();

        // Loop through each test case
        for (int t = 0; t < T; t++) {
            // Read N and K for the current test case
            int N = scanner.nextInt();
            int K = scanner.nextInt();

            // Process the input (example: print N and K)
            //System.out.println("Test Case #" + (t + 1) + ": N = " + N + ", K = " + K);

            solve(N, K);
        }

        scanner.close(); // Close the scanner
    }

    private static void solve(int n, int k) {
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        int tr[] = transform(arr, k);
        for (int i = 0; i < n; i++) {
            System.out.println(tr[i] + 1);
        }
    }

    private static int[] transform(int[] arr, int k) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        while (k > 0) {
            if ((k & 1) == 1) {
                res = compose(arr);
            }
            arr = compose(arr);
            k >>= 1;
        }
        return res;
    }

    private static int[] compose(int[] arr) {
        int n = arr.length;
        int res[] = new int[n];
        int ix = 0;
        for (int i = 0; i < n; i += 2) {
            res[ix++] = arr[i];
        }
        for (int j = 1; j < n; j += 2) {
            res[ix++] = arr[j];
        }
        return res;
    }

    private static int[] compose1(int[] input, int[] tr) {
        int res[] = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            res[i] = tr[input[i]];
        }
        return res;
    }

}
