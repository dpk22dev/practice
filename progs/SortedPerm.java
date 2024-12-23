package progs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortedPerm {
    public static void main(String[] args) {
        Integer arr[] = {1, 2, 3, 4};
        List<List<Integer>> res = new ArrayList<>();
        perm(arr, 0, res);
    }

    private static void perm(Integer[] arr, int s, List<List<Integer>> res) {
        int n = arr.length;
        if (s == n) {
            String temp = Arrays.deepToString(arr);
            System.out.println(temp);
            List<Integer> list = Arrays.asList(arr);
            res.add(list);
        }
        for (int i = s; i < n; i++) {
            Integer temp[] = Arrays.copyOf(arr, n);
            swap(temp, i, s);
            //Arrays.sort( temp, s+1, n);
            perm(temp, s + 1, res);
            swap(temp, i, s);
            arr = temp;
        }
    }

    private static void swap(Integer[] arr, int i, int s) {
        int t = arr[i];
        arr[i] = arr[s];
        arr[s] = t;
    }
}
