package progs;

// phodigere@paloaltonetworks.com
public class MergeLists {
    private static class Node {
        Integer data;
        Node right;

        public Node(int data) {
            this.data = data;
            this.right = null;
        }

        public Node(int data, Node right) {
            this.data = data;
            this.right = right;
        }
    }

    private static Node mergeList(Node head1, Node head2) {
        Node prev = null;
        Node head;
        if (head1 == null) {
            head = head2;
        } else if (head2 == null) {
            head = head1;
        } else {
            if (head1.data < head2.data) {
                prev = head1;
                head1 = head1.right;
            } else {
                prev = head2;
                head2 = head2.right;
            }
            head = prev;
            while (head1 != null && head2 != null) {
                if (head1.data == prev.data) {
                    head1 = head1.right;
                    continue;
                } else if (head2.data == prev.data) {
                    head2 = head2.right;
                    continue;
                }
                if (head1.data <= head2.data) {

                    prev.right = head1;

                    head1 = head1.right;
                } else {

                    prev.right = head2;

                    head2 = head2.right;
                }
                prev = prev.right;
            }
            if (head1 == null) prev.right = head2;
            else prev.right = head1;
        }
        return head;
    }

    public static void main(String[] args) {

        Node list1 = new Node(1, new Node(5, new Node(5, new Node(11))));
        Node list2 = new Node(3, new Node(6, new Node(6, new Node(11))));
        Node res = mergeList(list1, list2);
        print(res);

    }

    private static void print(Node res) {
        Node c = res;
        while (c != null) {
            System.out.println(c.data);
            c = c.right;
        }
    }
}
