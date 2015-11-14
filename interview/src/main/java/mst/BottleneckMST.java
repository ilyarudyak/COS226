package mst;

import edu.princeton.cs.algs4.*;

/**
 * This is a classic problem 23-3 from Cormen p. 640
 * Solution can be found here:
 * http://www.jade-cheng.com/uh/coursework/ics-311/homework/homework-10.pdf
 *
 * But we don't have structure to delete edges, so we use UF and *add* edges.
 */
public class BottleneckMST {

    private EdgeWeightedGraph G;
    private UF uf;
    private MinPQ<Edge> minPQ;

    public BottleneckMST(EdgeWeightedGraph g) {
        G = g;
        uf = new UF(G.V());
        minPQ = new MinPQ<>();
        for (Edge e : G.edges()) {
            minPQ.insert(e);
        }
    }

    private double findBottleneck1() {
        Edge e = null;
        while (uf.count() > 1) {
            e = minPQ.delMin();
            int v = e.either();
            int w = e.other(v);
            uf.union(v, w);
        }
        if (e != null) {
            return e.weight();
        } else {
            return -1;
        }
    }

    private double findBottleneck2() {
        double bottleneck = 0;
        for (Edge e: G.edges()) {
            if (e.weight() < .45) {
                if (uf.count() > 1) {
                    int v = e.either();
                    int w = e.other(v);
                    uf.union(v, w);
                    if (e.weight() > bottleneck) {
                        bottleneck = e.weight();
                    }
                } else {
                    break;
                }
            }
        }
        if (uf.count() == 1) {
            return bottleneck;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {

        EdgeWeightedGraph G = Utils.buildGraphWithDiffEdges(250, 1500);

        BottleneckMST bottleneck = new BottleneckMST(G);
        StdOut.println("bottleneck = " + bottleneck.findBottleneck2());

        KruskalMST kruskalMST = new KruskalMST(G);
        StdOut.println("max edge in mst = " + Utils.maxEdgeInMST(kruskalMST.edges()));
    }
}


















