import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by ilyarudyak on 11/7/15.
 */
public class SAP {

    private static final int LENGTH = 0;
    private static final int ANCESTOR = 1;

    private final Digraph G;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = new Digraph(G);
    }

    private int buildSAP(int v, int w, int flag) {

        // handle case if v and w are the same
        if (v == w) {
            if (flag == LENGTH) {
                return 0;
            } else {
                return v;
            }
        }

        int minDist = Integer.MAX_VALUE;
        int ancestor = -1;

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);

        // separately handle case when there is v -> w path or vise versa
        if (bfsV.hasPathTo(w)) {
            minDist = bfsV.distTo(w);
            ancestor = w;
        }

        if (bfsW.hasPathTo(v) && bfsW.distTo(v) < minDist) {
            minDist = bfsW.distTo(v);
            ancestor = v;
        }

        for (int i = 0; i < G.V();  i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int distFromV = bfsV.distTo(i);
                int distFromW = bfsW.distTo(i);
                int dist = distFromV + distFromW;
                if (dist < minDist) {
                    minDist = dist;
                    ancestor = i;
                }
            }
        }
        if (flag == LENGTH) {
            if (minDist == Integer.MAX_VALUE) {
                return -1;
            } else {
                return minDist;
            }
        } else {
            return ancestor;
        }
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return buildSAP(v, w, LENGTH);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return buildSAP(v, w, ANCESTOR);
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> V, Iterable<Integer> W) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, V);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, W);
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < G.V();  i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int d = bfsV.distTo(i) + bfsW.distTo(i);
                if (d < minDist) {
                    minDist = d;
                }
            }
        }

        if (minDist == Integer.MAX_VALUE) {
            return -1;
        }

        return minDist;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> V, Iterable<Integer> W) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, V);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, W);
        int minDist = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < G.V();  i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int d = bfsV.distTo(i) + bfsW.distTo(i);
                if (d < minDist) {
                    minDist = d;
                    ancestor = i;
                }
            }
        }
        return ancestor;
    }

    // do unit testing of this class
    public static void main(String[] args) {

        Digraph G1 = new Digraph(new In("src/main/resources/digraph1.txt"));
        SAP sap1 = new SAP(G1);

        StdOut.println(sap1.length(3, 8));
        StdOut.println(sap1.ancestor(3, 8));

//        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G1, 3);
//        StdOut.println(bfs.hasPathTo(3));

    }
}


















