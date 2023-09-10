public class MyTrie {

    static class TrieNode {
        boolean eow;
        TrieNode nodes[];

        TrieNode() {
            this.eow = false;
            this.nodes = new TrieNode[26];
        }
    }

    public static void makeTrie(TrieNode root, String product) {
        int charInx;
        for (int i = 0; i < product.length(); i++) {
            charInx = product.charAt(i) - 'a';
            if (root.nodes[charInx] == null) {
                root.nodes[charInx] = new TrieNode();
            }
            root = root.nodes[charInx];
        }
        root.eow = true;
    }

    public static TrieNode findRoot(TrieNode root, String str) {
        TrieNode temp = root;
        for (int i = 0; i < str.length() && temp != null; i++) {
            temp = temp.nodes[str.charAt(i) - 'a'];
        }
        return temp;
    }

    // trie usage method
    // public static void perm(TrieNode root, String inp, int[] count, List<String>
    // res) {
    // if (root == null)
    // return;
    // if (count[0] == 3)
    // return;
    // if (root.eow == true) {
    // res.add(inp);
    // count[0]++;
    // }

    // for (int i = 0; i < 26; i++) {
    // if (root.nodes[i] != null) {
    // perm(root.nodes[i], inp + Character.toString('a' + i), count, res);
    // }
    // if (count[0] == 3)
    // return;
    // }

    // }

    // public List<List<String>> suggestedProducts(String[] products, String
    // searchWord) {

    // // Replace this placeholder return statement with your code
    // TrieNode root = new TrieNode();
    // for (String product : products) {
    // makeTrie(root, product);
    // }

    // List<List<String>> resList = new ArrayList<>();
    // for (List<String> list : resList) {
    // list = new ArrayList();
    // }

    // for (int i = 1; i <= searchWord.length(); i++) {
    // String str = searchWord.substring(0, i);
    // TrieNode newRoot = findRoot(root, str);
    // int count[] = { 0 };
    // List<String> temp = new ArrayList<>();
    // perm(newRoot, str, count, temp);
    // resList.add(temp);
    // }

    // return resList;
    // }

    // public static void main(String[] args) {
    // MapsPract mp = new MapsPract();

    // List<List<String>> res = mp.suggestedProducts(
    // new String[] { "bags", "baggage", "banner", "box", "clothes" }, "bags");
    // System.out.println(res);
    // }
}
