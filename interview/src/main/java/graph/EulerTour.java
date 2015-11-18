package graph;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.GraphGenerator;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * An Euler cycle in a graph is a cycle (not necessarily simple)
 * that uses every edge in the graph exactly once. Show that a connected
 * graph has an Euler cycle if and only if every vertex has even degree.
 * Design a linear-time algorithm to determine whether a graph has an
 * Euler cycle, and if so, find one.
 */
public class EulerTour {

    private Graph G;
    private Set<String> marked;
    private List<String> path;
    private int[] degree;

    public EulerTour(Graph g) {
        G = g;
        marked = new HashSet<>();
        path = new ArrayList<>();
        degree = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            degree[v] = G.degree(v);
        }
    }

    public void findEulerTour() {

//        int count = 0;
        while (true /*&& count < 5*/) {

            int start = getNextStart();
            if(start == -1) {
                break;
            }
            StdOut.println("start=" + start);

            int next;
            while (true) {
                next = getNextVertex(start);
                if (next == -1) {
                    break;
                }
                start = next;
            }
//            StdOut.println("path so far = " + path);
//            count++;
        }
    }

    // helper methods
    private int getNextVertex(int u) {
        for (int v: G.adj(u)) {
            if (!marked(u, v)) {
                marked.add(edge(u, v));
                path.add(edge(u, v));
                degree[u] -= 1;
                degree[v] -= 1;
                return v;
            }
        }
        return -1;
    }
    private boolean marked(int u, int v) {
        return marked.contains(edge(u, v)) || marked.contains(edge(v, u));
    }
    private String edge(int u, int v) {
        return Integer.toString(u) + "-" + Integer.toString(v);
    }
    private int getNextStart() {
        for (int v = 0; v < G.V(); v++) {
            if (degree[v] > 0) {
                return v;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        Graph G = new Graph(new In("src/main/resources/Euler.txt"));
        EulerTour tour = new EulerTour(G);
        tour.findEulerTour();
        StdOut.println(tour.path);
        StdOut.println(tour.path.size());

    }

}



















