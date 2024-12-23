package progs;

import java.util.*;

public class LISUsingBS {
    public static int longestIncreasingSubsequence(int arr[]) {
        //Your code goes here
        TreeMap<Integer, Integer> myset = new TreeMap<>();
        myset.put(arr[0], arr[0]);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > myset.lastEntry().getValue()) {
                myset.put(arr[i], arr[i]);
            } else {
                Map.Entry<Integer, Integer> entry = myset.ceilingEntry(arr[i]);
                if (entry.getValue() != arr[i]) {
                    myset.remove(entry.getKey());
                    myset.put(arr[i], arr[i]);
                }
            }
        }
        return myset.size();
    }

    public static int longestIncreasingSubsequence1(int arr[]) {
        //Your code goes here
        TreeSet<Integer> myset = new TreeSet<>();
        myset.add(arr[0]);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > myset.last()) {
                myset.add(arr[i]);
            } else {
                //Map.Entry<Integer,Integer> entry = myset.ceilingEntry( arr[i] );
                Integer ceilValue = myset.ceiling(arr[i]);
                if (ceilValue != arr[i]) {
                    myset.remove(ceilValue);
                    myset.add(arr[i]);
                }
            }
        }
        return myset.size();
    }

    public static void main(String[] args) {

    }

}
