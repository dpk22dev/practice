package progs;

import java.util.*;

public class SubsetEqualK {
    private static void f(ArrayList<Integer> arr, int inx, int s, int k, ArrayList<Integer> temp, ArrayList<ArrayList<Integer>> res) {

        if (s == k) {
            System.out.println(s + ", " + temp.size());
            res.add(temp);
        }
        if (inx == arr.size())
            return;

        int n = arr.size();

        ArrayList<Integer> t1 = new ArrayList<>(temp);
        f(arr, inx + 1, s, k, t1, res);

        ArrayList<Integer> t2 = new ArrayList<>(temp);
        t2.add(arr.get(inx));
        f(arr, inx + 1, s + arr.get(inx), k, t2, res);
    }

    /*
        private static void f(ArrayList<Integer> arr, int inx, int s, int k, ArrayList<Integer> temp, ArrayList<ArrayList<Integer>> res) {
            if (s == k) {
                res.add(new ArrayList<>(temp)); // Add a copy of 'temp' to 'res'
                return;
            }
            if (s > k || inx == arr.size()) {
                return;
            }

            // Include the current element and recurse
            temp.add(arr.get(inx));
            f(arr, inx + 1, s + arr.get(inx), k, temp, res);
            temp.remove(temp.size() - 1); // Backtrack by removing the last element

            // Exclude the current element and recurse
            f(arr, inx + 1, s, k, temp, res);
        }
    */
    private static void findSubsets(ArrayList<Integer> arr, int index, int sum, int target, ArrayList<Integer> subset, ArrayList<ArrayList<Integer>> result) {
        if (sum == target) {
            System.out.println(sum + ", " + subset.size());
            result.add(new ArrayList<>(subset));
            //return;
        }
        if (index == arr.size())
            return;

        for (int i = index; i < arr.size(); i++) {
            subset.add(arr.get(i));
            findSubsets(arr, i + 1, sum + arr.get(i), target, subset, result);
            subset.remove(subset.size() - 1);
        }
    }

    public static ArrayList<ArrayList<Integer>> findSubsetsThatSumToK(ArrayList<Integer> arr, int n, int k) {
        // Write your code here.
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList();
        f(arr, 0, 0, k, temp, res);
        //Collections.sort(arr);
        //findSubsets(arr, 0, 0, k, temp, res);
        return res;
    }

    public static void main(String[] args) {

    }
}
