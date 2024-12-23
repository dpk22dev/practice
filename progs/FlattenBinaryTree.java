package progs;

/*
gives solution for few half results
 */
public class FlattenBinaryTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode solve(TreeNode root) {
        if (root == null) return root;
        if (root.left == null && root.right == null) {
            return root;
        }
        if (root.left == null) {
            root = root.right;
            return solve(root);
        } else {
            TreeNode last;
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = null;
            last = solve(root.right);
            if (last != null) {
                last.right = temp;
                last = solve(temp);
            }
            return last;
        }
    }

    public void flatten(TreeNode root) {
        solve(root);
    }
}
