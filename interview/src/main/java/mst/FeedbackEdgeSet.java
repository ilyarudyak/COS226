package mst;

import edu.princeton.cs.algs4.*;

/**
 * Ex.5 in Web Exercises http://algs4.cs.princeton.edu/43mst/
 * Minimum-weight feedback edge set. A feedback edge set of
 * a graph is a subset of edges that contains at least one
 * edge from every cycle in the graph. If the edges of
 * a feedback edge set are removed, the resulting graph
 * is acyclic. Given an edge-weighted graph, design an efficient
 * algorithm to find a feedback edge set of minimum weight.
 * Assume the edge weights are positive.
 *
 * Solution. Find a Maximum Spanning Tree. All edges not in tree are
 * in the feedback set. Since the tree is of maximum weight,
 * the feedback set is of minimum weight. To find a Maximum Spanning
 * Tree, use Kruskal’s algorithm on graph Gneg, where Gneg is G with
 * each edge weight multiplied by negative 1.
 */
public class FeedbackEdgeSet {

    private EdgeWeightedGraph G;
    private Iterable<Edge> feedback;
    private double weight;

    public FeedbackEdgeSet(EdgeWeightedGraph g) {
        G = g;
        buildFeedback();
    }

    public Iterable<Edge> getFeedback() {
        return feedback;
    }

    public double getWeight() {
        return weight;
    }

    private void buildFeedback() {
        KruskalMaxST maxST = new KruskalMaxST(G);
        Queue<Edge> f = new Queue<>();
        for (Edge e: G.edges()) {
            if (!isInMST(e, maxST.edges())) {
                f.enqueue(e);
                weight += e.weight();
            }
        }
    }

    private boolean isInMST(Edge edge, Iterable<Edge> mst) {
        for (Edge e: mst) {
            // we use here the fact that all edges are different
            if (e.weight() == edge.weight()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

//        EdgeWeightedGraph G = Utils.buildGraphWithDiffEdges(250, 1500);
//        EdgeWeightedGraph GN = Utils.buildNegativeGraph(G);
//
//        KruskalMaxST maxST = new KruskalMaxST(G);
//        KruskalMST mst = new KruskalMST(GN);
//
//        StdOut.println(maxST.weight());
//        StdOut.println(mst.weight());

        EdgeWeightedGraph G = Utils.buildGraphWithDiffEdges(250, 1500);
        FeedbackEdgeSet feedback = new FeedbackEdgeSet(G);
        StdOut.println("feedback weight = " + feedback.getWeight());

        KruskalMaxST maxST = new KruskalMaxST(G);
        double weight = Utils.getTotalWeight(G) - maxST.weight();
        StdOut.println(Utils.getTotalWeight(G));
        StdOut.println("total weight - MaxST weight = " + weight);
    }

}


















