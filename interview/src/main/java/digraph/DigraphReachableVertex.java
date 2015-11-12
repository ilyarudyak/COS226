package digraph;

import edu.princeton.cs.algs4.*;

/**
 * Ex. 43 http://algs4.cs.princeton.edu/42digraph/
 * Design a linear-time algorithm to determine whether a digraph
 * has a vertex that is reachable from every other vertex, and if so,
 * find one.
 * This solution for graphs that have only one vertex with outdegree == 0
 */
public class DigraphReachableVertex {

    private Digraph G;

    public DigraphReachableVertex() {
        generateGraph();
        StdOut.println(G);
    }

    public boolean isReachableVertex() {

        Digraph R = G.reverse();
        DepthFirstDirectedPaths dfs = null;
        for (int v = 0; v < G.V(); v++) {
            if (G.outdegree(v) == 0) {
                dfs = new DepthFirstDirectedPaths(R, v);
                break;
            }
        }

        for (int v = 0; v < R.V(); v++) {
            if (!dfs.hasPathTo(v)) {
                return false;
            }
        }

        return true;
    }

    // helper methods
    private boolean check(Digraph G) {

        // check if only one vertex has outdegree == 0
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            if (G.outdegree(v) == 0) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }

        // check if G has cycle
        DirectedCycle dc = new DirectedCycle(G);
        if (!dc.hasCycle()) {
            return false;
        }

        if (count == 1) {
            return true;
        }

        return false;
    }
    private void generateGraph() {
        while (true) {
            G = DigraphGenerator.simple(5, 5);
            if (check(G)) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        DigraphReachableVertex drv = new DigraphReachableVertex();
        StdOut.println(drv.isReachableVertex());
    }
}
















