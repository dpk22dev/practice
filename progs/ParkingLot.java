package progs;

import java.util.Scanner;

public class ParkingLot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int res = fun(n);
            System.out.println(res);
        }
        sc.close();
    }

    private static int fun(int n) {
        int in = perm(n, n - 2);
        System.out.println(in);
        return (n - 1) * 4 * in;
    }

    private static int perm(int n, int r) {
        int mul = 1;
        for (int i = n; i > n - r; i--) {
            mul *= i;
        }
        return mul;
    }

}
