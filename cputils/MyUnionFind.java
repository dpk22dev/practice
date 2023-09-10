public class MyUnionFind {

    static class DSU {
        int[] parent;
        int[] rank;

        public DSU(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            int px = parent[x];
            if (px == x)
                return x;
            return parent[x] = find(px);
        }

        public void union(int x, int y) {
            int px = parent[x];
            int py = parent[y];
            int res;
            if (px != py) {
                if (rank[px] < rank[py]) {
                    parent[px] = py;
                } else if (rank[px] > rank[py]) {
                    parent[py] = px;
                } else {
                    parent[px] = py;
                    rank[py]++;
                }
            }
        }
    }

}
