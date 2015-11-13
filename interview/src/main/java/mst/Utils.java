package mst;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by ilyarudyak on 11/12/15.
 */
public class Utils {

    public static EdgeWeightedGraph buildGraphWithDiffEdges(int V, int E) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = Math.round(100000000 * StdRandom.uniform()) / 100000000.0;
            Edge e = new Edge(v, w, weight);
            G.addEdge(e);
        }
        return G;
    }

    // multiply all weights by -1
    public static EdgeWeightedGraph buildNegativeGraph(EdgeWeightedGraph G) {
        EdgeWeightedGraph GN = new EdgeWeightedGraph(G.V());
        for (Edge e: G.edges()) {
            int v = e.either();
            int w = e.other(v);
            Edge en = new Edge(v, w, e.weight() * -1);
            GN.addEdge(en);
        }
        return GN;
    }

    public static double getTotalWeight(EdgeWeightedGraph G) {
        double w = 0;
        for (Edge e: G.edges()) {
            w += e.weight();
        }
        return w;
    }

}
