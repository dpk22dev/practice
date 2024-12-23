package javalang.faltu;

public class Diagonal {
    public static void main(String[] args) {
        int n = 5;
        for (int l = 0; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                int j = i + l;
                System.out.println("i: " + i + " j: " + j);
            }
            System.out.println("-----");
        }

    }
}
