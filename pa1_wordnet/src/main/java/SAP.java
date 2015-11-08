import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by ilyarudyak on 11/7/15.
 */
public class SAP {

    private final Digraph G;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = new Digraph(G);
    }

    private int buildSAP(int v, int w, int flag) {

        StdOut.println("v=" + v + " w=" + w);

        int minDist = Integer.MAX_VALUE;
        int ancestor = -1;

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);

        if (bfsV.hasPathTo(w)) {
            minDist = bfsV.distTo(w);
        }

        if (bfsW.hasPathTo(v) && bfsW.distTo(v) < minDist) {
            minDist = bfsW.distTo(v);
        }

        for (int i = 0; i < G.V();  i++) {
            if (i != v && i != w && bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int distFromV = bfsV.distTo(i);
                int distFromW = bfsW.distTo(i);
                int dist = distFromV + distFromW;
                StdOut.println("i=" + i + " dist=" + dist);
                if (dist < minDist) {
                    minDist = dist;
                    ancestor = i;
                }
            }
        }
        if (flag == 0) {
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
        return buildSAP(v, w, 0);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return buildSAP(v, w, 1);
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
