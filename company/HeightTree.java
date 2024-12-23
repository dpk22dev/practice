package company;

import java.util.Arrays;

public class HeightTree {

    static int res[];

    static int height(int p[], int inx) {
        if (res[inx] != -1)
            return res[inx];

        res[inx] = (p[inx] == -1) ? 0 : height(p, p[inx]) + 1;
        return res[inx];

        /*int temp;
        if( p[inx] == -1 ){
            temp = 0;
        } else {
            temp = height( p, p[inx] ) + 1; //
        }
        res[inx] = temp;
        return temp;*/
    }

    public static void main(String[] args) {

        int[] parent = {2, 3, 3, -1, 2, 2};
        int n = parent.length;
        res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            height(parent, i);
        }

    }
}
