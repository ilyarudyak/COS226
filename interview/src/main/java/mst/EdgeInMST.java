package mst;

import edu.princeton.cs.algs4.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Is an edge in a MST. Given an edge-weighted graph G and an edge e,
 * design a linear-time algorithm to determine whether e appears in some MST of G.
 * Solution. Add all edges e' with w(e') < w(e) to UF. Then check if is in
 * some cycle. In this case it is an edge with max weight in a cycle.
 *
 * We create a random graph and check if all edges are different (in this case
 * we have only one MST). Then build MST and check if it contains e.
 */
public class EdgeInMST {

    private EdgeWeightedGraph G;

    public EdgeInMST(EdgeWeightedGraph g) {
        G = g;
    }

    public boolean isEdgeInMST(Edge edge) {
        UF uf = new UF(G.V());
        for (Edge e: G.edges()) {
            if (e.compareTo(edge) < 0) {
                int v = e.either();
                int w = e.other(v);
                uf.union(v, w);
            }
        }
        int v = edge.either();
        int w = edge.other(v);
        if (uf.connected(v, w)) {
            return false;
        }
        return true;
    }

    // helper methods
    private boolean isAllWeightsDiff() {
        Set<Double> weights = new HashSet<>();
        for (Edge e: G.edges()) {
            if (weights.contains(e.weight())) {
                StdOut.println(e);
            }
            weights.add(e.weight());
        }
        return weights.size() == G.E();
    }
    private boolean test() {
        boolean flag = true;
        for (Edge e: G.edges()) {
            boolean isInMST = isEdgeInMST(e);
            boolean isInFactInMST = isInFactInMST(e);
            if (isInMST != isInFactInMST) {
                StdOut.println(e);
                flag = false;
            }
        }
        return flag;
    }
    private boolean isInFactInMST(Edge e) {
//        KruskalMST mst = new KruskalMST(G);
        PrimMST mst = new PrimMST(G);
        for (Edge edge: mst.edges()) {
            if (e.compareTo(edge) == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

//        In in = new In("src/main/resources/mediumEWG.txt");
//        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        EdgeWeightedGraph G = Utils.buildGraphWithDiffEdges(250, 2000);
        EdgeInMST edgeInMST = new EdgeInMST(G);
        StdOut.println(edgeInMST.isAllWeightsDiff());

        StdOut.println(edgeInMST.test());
    }
}
















