package progs;

class RotatedSortedSearch {
    private int findp(int nums[]) {
        int l = 0, n = nums.length - 1;
        int h = n;
        if (nums[l] < nums[h])
            return h;

        while (l <= h) {
            int m = (l + h) / 2;
            int left = (m - 1 >= 0) ? nums[m - 1] : Integer.MIN_VALUE;
            int right = (m + 1 <= n) ? nums[m + 1] : Integer.MIN_VALUE;
            if (nums[m] > left && nums[m] > right) {
                return m;
            } else if (nums[m] < left && nums[m] < right) {
                return m - 1;
            } else if (nums[m] > nums[l] && nums[m] > nums[h]) {
                l = m + 1;
            } else if (nums[m] < nums[l] && nums[m] < nums[h]) {
                h = m - 1;
            }
        }
        return -1;
    }

    private int bs(int[] nums, int t, int l, int h) {
        int m;
        while (l <= h) {
            m = (l + h) / 2;
            if (nums[m] == t) {
                return m;
            }
            if (t < nums[m]) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int n = nums.length - 1;
        int p = findp(nums);
        System.out.println(p);
        int res = -1;
        if (target >= nums[0] && target <= nums[p])
            res = bs(nums, target, 0, p);
        else
            res = bs(nums, target, p + 1, n);

        System.out.println("res: " + res);
        return res;
    }

    public static void main(String[] args) {
        RotatedSortedSearch rs = new RotatedSortedSearch();
        int[] nums = {4, 5, 1, 2, 3};
        int inx = rs.search(nums, 1);

    }
}