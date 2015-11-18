package graph;

import edu.princeton.cs.algs4.*;

/**
 * Created by ilyarudyak on 11/18/15.
 */
public class NonRecursiveDFS {

    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;
    private Stack<Integer> stack;

    public NonRecursiveDFS(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        stack = new Stack<>();
        dfs2(G, s);
    }

    private void dfs2(Graph G, int s) {
        marked[s] = true;
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            marked[v] = true;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    stack.push(w);
                }
            }
        }
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    public static void main(String[] args) {

        Graph G = new Graph(new In("src/main/resources/tinyCG.txt"));

        DepthFirstPaths dfs = new DepthFirstPaths(G, 0);
        StdOut.println(dfs.pathTo(4));

        NonRecursiveDFS dfs2 = new NonRecursiveDFS(G, 0);
        for (int v = 0; v < G.V(); v++) {
            StdOut.printf("v=%d path= %s\n", v, dfs2.pathTo(v));
        }

    }
}














