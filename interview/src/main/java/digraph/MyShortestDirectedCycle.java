package digraph;

import edu.princeton.cs.algs4.*;

/**
 * Ex. 40 http://algs4.cs.princeton.edu/42digraph/
 * Given a digraph, design an algorithm to find a directed cycle
 * with the minimum number of edges (or report that the graph is acyclic).
 * The running time of your algorithm should be proportional to E V in the worst case.
 */
public class MyShortestDirectedCycle {

    private Digraph G;
    // reversed Digraph
    private Digraph R;
    private String cycle;
    private int dist = Integer.MAX_VALUE;

    public MyShortestDirectedCycle(Digraph G) {
        this.G = G;
        R = G.reverse();
    }

    public void searchAllCycles() {
        for (int v=0; v < G.V(); v++) {
            searchCycleOneNode2(v);
        }
    }

    private void searchCycleOneNode2(int v) {
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, v);
        for (int w: R.adj(v)) {
            if (bfs.hasPathTo(w) && bfs.distTo(w) < dist) {
                dist = bfs.distTo(w);
                cycle = getCycle(bfs.pathTo(w), v);
            }
        }
    }

    private String getCycle(Iterable<Integer> path, int v) {
        StringBuilder sb = new StringBuilder();
        for (int w: path) {
            sb.append(w + "->");
        }
        sb.append(v);
        return sb.toString();
    }

    public static void main(String[] args) {

//        Digraph G = new Digraph(new In("src/main/resources/tinyDG.txt"));
//        MyShortestDirectedCycle sdc = new MyShortestDirectedCycle(G);
//        sdc.searchAllCycles();
//        StdOut.println(sdc.cycle);

        Digraph GS = DigraphGenerator.tournament(1000);

        Stopwatch stopwatch = new Stopwatch();
        MyShortestDirectedCycle msdc = new MyShortestDirectedCycle(GS);
        msdc.searchAllCycles();
        StdOut.println(msdc.cycle);
        StdOut.println("time=" + stopwatch.elapsedTime());

        stopwatch = new Stopwatch();
        ShortestDirectedCycle sdc = new ShortestDirectedCycle(GS);
        StdOut.println(sdc.cycle());
        StdOut.println("time=" + stopwatch.elapsedTime());
    }
}
