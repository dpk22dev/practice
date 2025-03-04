package javalang.lang;

import java.util.Arrays;

public class ArraysUsage {

    private static class Product {
        public int id;
        public String item;

        public Product(int i, String item) {
            this.id = i;
            this.item = item;
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[]{2, 15, 70, 19, 100, 12};
        /* last elemetn will be 0, in case of Objects it will be null */
        /* copyOf creates new array and return, we can't pass our own array */
        int[] arr2 = Arrays.copyOf(arr, 7);
        int[] arr3 = Arrays.copyOfRange(arr, 1, 4);
        System.out.println(Arrays.toString(arr2)); // toString is helpful in printing
        System.out.println(Arrays.toString(arr3));

        /* copies srcArr, start pos to destArr, startPos; len # elements */
        int[] arr4 = new int[10];
        System.arraycopy(arr, 1, arr4, 3, 3);
        System.out.println(Arrays.toString(arr4));

        Arrays.sort(arr, 1, 5);
        System.out.println(Arrays.toString(arr));

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int inx = Arrays.binarySearch(arr, 15);
        System.out.println(inx);

        int inx1 = Arrays.binarySearch(arr, 17);
        System.out.println("17 it should be at " + (-(inx1 + 1)));

        // elements are equal witin range
        int[] arr5 = {12, 14, 15, 16, 17, 18};
        int[] arr6 = {11, 14, 15, 16, 17, 19};
        int c1 = Arrays.compare(arr5, 1, arr5.length - 1, arr6, 1, arr6.length - 1);
        System.out.println(c1);

        int[] arr7 = {12, 14, 15, 16, 16, 18};
        int[] arr8 = {11, 14, 15, 16, 17, 19};
        int c2 = Arrays.compare(arr7, 1, arr7.length - 1, arr8, 1, arr8.length - 1);
        System.out.println(c2); // arr7 is lexico smaller than arr8
        int c3 = Arrays.compare(arr8, 1, arr8.length - 1, arr7, 1, arr7.length - 1);
        System.out.println(c3);// arr8 is lexico greater than arr7

        Arrays.fill(arr5, 1, arr5.length - 1, 0);
        System.out.println(Arrays.toString(arr5));

        /*
        if we want to remove certain elements from array, no in built method
        use systemcopy to copy elements
         */
        int s = 1, e = 5;
        int len = arr5.length - (e - s); // e-s
        int res[] = new int[len];
        System.arraycopy(arr5, 0, res, 0, (s - 0));
        System.arraycopy(arr5, e, res, 1, 1);
        System.out.println(Arrays.toString(res));

        //compare, copy arr of products
        Product[] products = {new Product(23, "potatoes"),
                new Product(14, "orange"), new Product(13, "lemon"),
                new Product(26, "bread"), new Product(14, "sugar")};

        Product[] p2 = Arrays.copyOfRange(products, 1, 4);
        Arrays.sort(p2, 0, p2.length, (pr1, pr2) -> {
            return pr1.item.compareTo(pr2.item);
        });
        Arrays.stream(p2).forEach(p -> System.out.println(p.id + ", " + p.item));
        int pinx = Arrays.binarySearch(p2, 0, p2.length,
                new Product(26, "bread"), (pr1, pr2) -> {
                    return pr1.item.compareTo(pr2.item);
                });
        System.out.println("found: " + pinx);

        Product[] p3 = new Product[10];
        System.arraycopy(p2, 0, p3, 0, 2);
        Arrays.stream(p3).forEach(p -> {
            if (p != null) System.out.println(p.id + ", " + p.item);
        });

    }
}
