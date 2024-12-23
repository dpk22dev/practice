package cputils;

public class BIT {
    int tree[] = {1, 2, 3};
    int MaxIdx;

    int read(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= (idx & -idx);
        }
        return sum;
    }

    void update(int idx, int val) {
        while (idx <= MaxIdx) {
            tree[idx] += val;
            idx += (idx & -idx);
        }
    }

}
