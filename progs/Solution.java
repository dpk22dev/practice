package progs;// you can also use imports, for example:

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static List<List<Integer>> mergeOverlappingIntervals(int[][] arr) {
        // Write your code here.
        Arrays.sort(arr, (x, y) -> {
            return x[1] - y[1];
        });
        List<List<Integer>> res = new ArrayList<>();
        int n = arr.length;
        res.add(Arrays.asList(arr[0][0], arr[0][1]));
        for (int i = 1; i < n; i++) {
            int cur[] = arr[i];
            List<Integer> top = res.get(res.size() - 1);
            if (cur[0] <= top.get(1)) {
                List<Integer> temp = Arrays.asList(Math.min(top.get(0), cur[0]), Math.max(top.get(1), cur[1]));
                res.remove(res.size() - 1);
                res.add(temp);
            } else {
                res.add(Arrays.asList(cur[0], cur[1]));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        //int a = 2, c =3, d=a;
        //int a,b,c;
        int[][] arr = {{2, 2}, {2, 3}, {2, 5}, {3, 6}, {4, 4}, {4, 5}, {6, 6}};
        List<List<Integer>> res = mergeOverlappingIntervals(arr);
    }


}