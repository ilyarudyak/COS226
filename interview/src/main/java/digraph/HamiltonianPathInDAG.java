package digraph;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DigraphGenerator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Topological;

import java.util.Iterator;

/**
 * Ex. 32 http://algs4.cs.princeton.edu/42digraph/
 * Given a DAG, design a linear-time algorithm to determine
 * whether there is a directed path that visits each vertex exactly once.
 * Solution. Compute a topological sort and check if there is an edge between
 * each consecutive pair of vertices in the topological order.
 */
public class HamiltonianPathInDAG {

    private Digraph DAG;

    public HamiltonianPathInDAG(Digraph DAG) {
        this.DAG = DAG;
    }

    private boolean hasPath() {

        // calculate topological order
        Topological ts = new Topological(DAG);
        Iterable<Integer> order = ts.order();
        
        // check if there is an edge between
        // each consecutive pair of vertices
        Iterator<Integer> iterator = order.iterator();
        int start = iterator.next();
        while (iterator.hasNext()) {
            int next = iterator.next();
            if (!contains(DAG.adj(start), next)) {
                return false;
            }
            start = next;
        }
        return true;
    }

    private boolean contains (Iterable<Integer> iterable, int next) {
        for (int v: iterable) {
            if (v == next) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Digraph DAG = DigraphGenerator.dag(5, 9);
        StdOut.println(DAG);
        StdOut.println(new Topological(DAG).order());
        HamiltonianPathInDAG hp = new HamiltonianPathInDAG(DAG);
        StdOut.println(hp.hasPath());
    }
}
