package cputils;

class BIT {
    private int[] bit;  // BIT array (1-based indexing)
    private int n;      // Size of the BIT

    // Constructor: Initialize BIT with size n
    public BIT(int n) {
        this.n = n;
        this.bit = new int[n + 1]; // 1-based indexing, so allocate n+1
    }

    // 🔹 Update BIT at index idx (1-based) by adding value `val`
    public void update(int idx, int val) {
        while (idx <= n) {
            bit[idx] += val;
            idx += idx & -idx; // Move to next index
        }
    }

    // 🔹 Get prefix sum from index 1 to idx
    public int query(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx -= idx & -idx; // Move to parent
        }
        return sum;
    }

    // 🔹 Get sum of range [left, right]
    public int rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }

    // 🔹 Print BIT structure (For Debugging)
    public void printBIT() {
        for (int i = 1; i <= n; i++) {
            System.out.print(bit[i] + " ");
        }
        System.out.println();
    }
}
