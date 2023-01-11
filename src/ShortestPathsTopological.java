import java.util.Stack;

public class ShortestPathsTopological {
    private int[] parent;
    private int s;
    private double[] dist;

    public ShortestPathsTopological(WeightedDigraph G, int s) {
        this.parent = new int[G.V()];
        this.dist = new double[G.V()];
        this.s = s;
        TopologicalWD Top = new TopologicalWD(G);
        Top.dfs(s);
        Stack<Integer> verts = Top.order();
        for (int i : verts) {
            dist[i] = Double.MAX_VALUE;
            parent[i] = -1;
        }
        dist[s] = 0.0;
        while (verts.size() != 0) {
            int v = verts.pop();
            for (DirectedEdge e : G.incident(v)) {
                relax(e);
            }
        }

    }

    public void relax(DirectedEdge e) {
        int w = e.to();
        int v = e.from();
        if (dist[w] > (dist[v] + e.weight())) {
            parent[w] = v;
            dist[w] = dist[v] + e.weight();
        }
    }

    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }
}

