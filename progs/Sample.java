package progs;

public class Sample {
    // 1,2,2,3
    // 0.1.2.3

    public static int firstIndex(int arr[], int s, int e, int k) {
        int res = -1;
        while (s <= e) {
            int m = (s + e) / 2;
            if (arr[m] >= k) {
                if (arr[m] == k) {
                    res = m;
                }
                e = m - 1;
            } else {
                s = m + 1;
            }
        }
        return res;
    }

    public static int lastIndex(int arr[], int s, int e, int k) {
        int res = -1;
        while (s <= e) {
            int m = (s + e) / 2;
            System.out.println(s + "," + m + "," + e);
            if (arr[m] > k) {
                e = m - 1;
            } else {
                if (arr[m] == k) {
                    res = m;
                }
                s = m + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 4};
        int f = firstIndex(arr, 0, arr.length - 1, 4);
        int s = lastIndex(arr, 0, arr.length - 1, 4);
        System.out.println(f + ", " + s);
    }

}
