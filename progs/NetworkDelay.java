import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class NetworkDelay {

    public static class Graph {
        public int V;
        public ArrayList<Edge> adj[];
        public int[] dist;
        PriorityQueue<Node> pq;

        static class Edge {
            int e;
            int w;

            Edge(int e, int w) {
                this.e = e;
                this.w = w;
            }
        }

        static class Node {
            int name;
            int w;

            Node(int e, int w) {
                this.name = e;
                this.w = w;
            }
        }

        Graph(int n) {
            this.V = n;
            adj = (ArrayList<Edge>[]) new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<Edge>();
            }
            dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        }

        public void addEdge(int s, int e, int wt) {
            adj[s].add(new Edge(e, wt));
        }

        public int maxDistance(int k) {
            int cnt = 0;
            pq.offer(new Node(k, 0));
            while (!pq.isEmpty()) {
                if (cnt++ >= this.V) {
                    break;
                }
                Node top = pq.peek();
                for (Edge edge : adj[top.name]) {
                    if (dist[top.name] != Integer.MAX_VALUE && dist[top.name] + edge.w < dist[edge.e]) {
                        dist[edge.e] = dist[top.name] + edge.w;
                        pq.offer(new Node(edge.e, dist[edge.e]));
                    }
                }
            }
            int mx = -1;
            for (int i = 0; i < dist.length; i++) {
                if (dist[i] == Integer.MAX_VALUE)
                    return -1;
                mx = Math.max(mx, dist[i]);
            }
            return mx;
        }

    }

    public static int networkDelayTime(int[][] times, int n, int k) {

        // Replace this placeholder return statement with your code
        Graph g = new Graph(n);
        for (int[] edge : times) {
            g.addEdge(edge[0], edge[1], edge[1]);
        }
        return -1;
    }

    public static void main(String[] args) {

        int res = networkDelayTime(new int[][] { { 1, 2, 5 }, { 1, 3, 10 }, { 1, 4, 15 } }, 4, 1);
        System.out.println(res);
    }

}