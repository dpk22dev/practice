package progs;

import java.util.*;

public class TopView {
    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(int data) {
            this.data = data;
        }
    }

    private static class Pair {
        Node node;
        int inx;

        public Pair(Node node, int inx) {
            this.node = node;
            this.inx = inx;
        }
    }

    private ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        levelorder(root, map, 0);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    private void inorder(Node root, TreeMap<Integer, Integer> map, int inx) {
        if (root == null)
            return;
        //if( !map.containsKey(inx) ){

        //}
        inorder(root.left, map, inx - 1);
        map.putIfAbsent(inx, root.data);
        inorder(root.right, map, inx + 1);
    }

    private void levelorder(Node root, TreeMap<Integer, Integer> map, int inx) {
        LinkedList<Pair> q = new LinkedList<>();
        Pair pair = new Pair(root, inx);
        q.addLast(pair);

        while (!q.isEmpty()) {
            Pair top = q.removeFirst();
            map.putIfAbsent(top.inx, top.node.data);
            if (top.node.left != null)
                q.push(new Pair(top.node.left, top.inx - 1));
            if (top.node.right != null)
                q.push(new Pair(top.node.right, top.inx + 1));
        }
    }


    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);

        root.left.left = new Node(2);
        root.left.right = new Node(7);
        root.right.left = new Node(12);
        root.right.right = new Node(18);


        TopView topView = new TopView();
        ArrayList<Integer> res = topView.topView(root);
        for (Integer i : res) {
            System.out.println(i);
        }
    }

}
