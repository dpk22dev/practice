package progs;

public class MySegmentTree {

    private static class SegmentTree {
        int n;
        int tree[];
        int arr[];

        public SegmentTree(int n, int arr[]) {
            this.n = n;
            this.arr = arr;
            this.tree = new int[4 * n];
        }

        public void build(int inx, int l, int h) {
            if (l == h) {
                tree[inx] = arr[l];
                return;
            }

            int m = l + (h - l) / 2;

            build(2 * inx + 1, l, m);
            build(2 * inx + 2, m + 1, h);
            tree[inx] = tree[2 * inx + 1] + tree[2 * inx + 2];

        }

        // add v to node n
        public void pointUpdate(int inx, int l, int h, int pinx, int v) {
            if (l == h) {
                tree[inx] += v;
            } else {
                int m = l + (h - l) / 2;
                if (pinx >= l && pinx <= m) {
                    pointUpdate(2 * inx + 1, l, m, pinx, v);
                } else {
                    pointUpdate(2 * inx + 2, m + 1, h, pinx, v);
                }
                tree[inx] = tree[2 * inx + 1] + tree[2 * inx + 2];
            }
        }

        public int query(int inx, int l, int h, int ql, int qh) {
            if (ql <= l && qh >= h) {
                return tree[inx];
            }
            if (h < ql || l > qh) {
                return 0;
            }
            int m = l + (h - l) / 2;
            int leftVal = query(2 * inx + 1, l, m, ql, qh);
            int rightVal = query(2 * inx + 2, m + 1, h, ql, qh);
            return leftVal + rightVal;
        }

        public void printTree() {
            for (int e : tree) {
                System.out.println(e + ", ");
            }
        }

    }

    public static void main(String[] args) {
        int arr[] = new int[]{8, 2, 5, 1, 4, 5, 3, 9, 6, 10};
        int n = arr.length;
        SegmentTree segmentTree = new SegmentTree(n, arr);
        segmentTree.build(0, 0, n - 1);
        int s1 = segmentTree.query(0, 0, n - 1, 1, 3);
        System.out.println(s1);

        segmentTree.pointUpdate(0, 0, n - 1, 2, 5);
        int s2 = segmentTree.query(0, 0, n - 1, 1, 3);
        System.out.println(s2);

        segmentTree.pointUpdate(0, 0, n - 1, 2, 5);
        s2 = segmentTree.query(0, 0, n - 1, 1, 3);
        System.out.println(s2);

        segmentTree.pointUpdate(0, 0, n - 1, 0, 5);
        s2 = segmentTree.query(0, 0, n - 1, 0, 3);
        System.out.println(s2);

    }

}
