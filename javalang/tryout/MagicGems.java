package javalang.tryout;

import java.util.Scanner;

public class MagicGems {
    static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // N: Total space desired
        int M = sc.nextInt(); // M: Space taken by split gem

        System.out.println(countConfigurations(M, N));
    }

    private static int countConfigurations(int M, int N) {
        int[] dp = new int[N + 1];
        dp[0] = 1; // Base case: 1 way to make 0 space

        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1]; // Add configurations by unsplit gem
            if (i >= M) {
                dp[i] = (dp[i] + dp[i - M]) % MOD; // Add configurations by split gem
            }
        }

        return dp[N];
    }
}