package javalang.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sorting2DArray {
    public static void main(String[] args) {
        int[][] arr = {{2, 1}, {3, 1}, {4, 2}};

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]); // First column sorting
            }
            return Integer.compare(a[1], b[1]); // Second column sorting if first is equal
        });

        for (int[] row : arr) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("+++++++++++++++");

        char[][] carr = {{'a', 'b'}, {'c', 'd'}, {'a', 'a'}};

        Arrays.sort(carr, (a, b) -> {
            if (a[0] != b[0]) {
                return Character.compare(a[0], b[0]); // First column sorting
            }
            return Character.compare(a[1], b[1]); // Second column sorting if first is equal
        });


        for (char[] row : carr) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("+++++++++++++++");

        Integer[][] iarr = {{2, 1}, {3, 1}, {4, 2}};

        Arrays.sort(iarr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]); // First column sorting
            }
            return Integer.compare(a[1], b[1]); // Second column sorting if first is equal
        });

        for (Integer[] row : iarr) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("+++++++++++++++");

        double EPS = 1e-9;
        float[][] farr = {{1.2f, 1.3f}, {2.1f, 2.3f}, {3.1f, 3.2f}};

        Arrays.sort(farr, (a, b) -> {
            if (Float.compare(a[0], b[0]) == 0) {
                return Float.compare(a[1], b[1]);
            }
            return Float.compare(a[1], b[1]);
        });

        for (float[] fel : farr) {
            System.out.println(Arrays.toString(fel));
        }

        System.out.println("+++++++++++++++ 4");

        ArrayList<int[]> al = new ArrayList<>();
        al.add(new int[]{1, 2});
        al.add(new int[]{2, 3});
        al.add(new int[]{1, 3});
        Collections.sort(al, (a, b) -> {
            if (a[0] == a[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        al.stream().map(a -> a[0] + "," + a[1]).forEach(System.out::print);

    }


}
