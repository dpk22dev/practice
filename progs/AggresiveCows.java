package progs;

import java.util.Arrays;

public class AggresiveCows {

    private static boolean trySetting(int[] stalls, int x, int k) {
        int cc = 1;
        int p = 0, c = 1, n = stalls.length;
        while (c < n) {
            if (stalls[c] - stalls[p] > x) {
                cc++;
                p = c;
                c++;
                if (cc > k) break;
            } else {
                c++;
            }
        }
        return cc > k;
    }

    public static int aggressiveCows(int[] stalls, int k) {
        //    Write your code here.

        Arrays.sort(stalls);
        int ans = 0;
        int l = 0;
        int r = stalls[stalls.length - 1];

        while (l < r) {
            int mid = (l + r) / 2;
            boolean res = trySetting(stalls, mid, k);
            if (res) {
                l = mid + 1;
                ans = Math.max(ans, mid);
            } else
                r = mid - 1;
        }
        return ans;
    }
}