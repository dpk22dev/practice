package progs;

public class FlipImage {
    public static void main(String[] args) {
        int image[][] = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        flipAndInvertImage(image);
    }

    public static int[][] flipAndInvertImage(int[][] image) {

        // Replace this placeholder return statement with your code
        int m = image.length, n = image[0].length, l = 0, r = n - 1, i, temp;
        for (i = 0; i < m; i++) {

            while (l <= r) {
                if ((image[i][l] ^ image[i][r]) == 0) {
                    temp = image[i][l] == 1 ? 0 : 1;
                    image[i][l] = image[i][r] = temp;
                }
                l++;
                r--;
            }
        }
        return image;
    }
}
