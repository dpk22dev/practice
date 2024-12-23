package javalang.lang;

import java.util.*;

class Solution {

    public class TreeNode {
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

    private class Node {
        public TreeNode treeNode;
        public int hd;
        public int vd;

        Node(TreeNode treeNode, int hd, int vd) {
            this.treeNode = treeNode;
            this.hd = hd;
            this.vd = vd;
        }
    }

    private void it(TreeNode root, TreeMap<Integer, TreeMap<Integer, TreeSet<Integer>>> map) {

        if (root == null)
            return;

        LinkedList<Node> list = new LinkedList<>();
        list.add(new Node(root, 0, 0));
        TreeMap tm = new TreeMap();

        while (!list.isEmpty()) {
            Node top = list.removeFirst();
            int hd = top.hd;
            int vd = top.vd;
            // add to map
            if (map.get(hd) == null) {
                tm = new TreeMap();
                map.put(hd, tm);
                tm.computeIfAbsent(vd, (k) -> new TreeSet<Integer>());
                tm.computeIfPresent(vd, (k, v) -> {
                    ((TreeSet<Integer>) v).add(top.treeNode.val);
                    return v;
                });
                //tm.computeIfPresent( vd, (k, set) -> {set.add(top.treeNode.val); return set; });
            } else {
                tm = map.get(hd);
                tm.computeIfAbsent(vd, (k) -> new TreeSet());
                tm.computeIfPresent(vd, (k, v) -> {
                    ((TreeSet<Integer>) v).add(top.treeNode.val);
                    return v;
                });
            }
            // recurse
            if (top.treeNode.left != null)
                list.addLast(new Node(top.treeNode.left, top.hd - 1, top.vd + 1));
            if (top.treeNode.right != null)
                list.addLast(new Node(top.treeNode.right, top.hd + 1, top.vd + 1));

        }

    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, TreeSet<Integer>>> map = new TreeMap();
        it(root, map);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmpList = new ArrayList<>();
        for (TreeMap<Integer, TreeSet<Integer>> tm : map.values()) {
            for (TreeSet<Integer> set : tm.values()) {
                tmpList = set.stream().toList();
            }
            res.add(tmpList);
        }

        return res;
    }
}