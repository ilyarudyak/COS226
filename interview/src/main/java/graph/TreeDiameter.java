package graph;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.GraphGenerator;
import edu.princeton.cs.algs4.StdOut;

/**
 * Diameter: design a linear-time algorithm to find the longest
 * simple path in the tree. Hint (diameter): to compute the diameter,
 * pick a vertex s; run BFS from s; then run BFS again from the vertex
 * that is furthest from s. -HARD This is an exercise in CLRS with a star.
 */
public class TreeDiameter {

    private Graph G;
    private int s = 0;
    private int u;
    private int v;
    private int diameter;

    public TreeDiameter(Graph g) {
        G = g;
        findMaxPath();
    }

    private void findMaxPath() {

        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
        u = furthest(bfs);
        BreadthFirstPaths bfs2 = new BreadthFirstPaths(G, u);
        v = furthest(bfs2);
        diameter = bfs2.distTo(v);
    }

    // helper methods
    private int furthest(BreadthFirstPaths bfs) {
        int dist = 0;
        int u = -1;
        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v) && bfs.distTo(v) > dist) {
                u = v;
                dist = bfs.distTo(v);
            }
        }
        return u;
    }

    public static void main(String[] args) {

        Graph G = GraphGenerator.binaryTree(127);
        TreeDiameter td = new TreeDiameter(G);
        StdOut.printf("diameter=%d\n", td.diameter);
    }
}
