package week1.digraphs;

import edu.princeton.cs.algs4.*;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by ilyarudyak on 11/7/15.
 */
public class DirectedCycleDemo {

    private boolean[] marked;        // marked[v] = has vertex v been marked?
    private int[] edgeTo;            // edgeTo[v] = previous vertex on path to v
    private boolean[] onStack;       // onStack[v] = is vertex on the stack?
    private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)

    /**
     * Determines whether the digraph <tt>G</tt> has a directed cycle and, if so,
     * finds such a cycle.
     * @param G the digraph
     */
    public DirectedCycleDemo(Digraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null) dfs(G, v);
    }

    // check that algorithm computes either the topological order or finds a directed cycle
    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        show(onStack);
        marked[v] = true;
        for (int w : G.adj(v)) {
            StdOut.println("w=" + w);
            // short circuit if directed cycle found
            if (cycle != null) {
                return;
            }

            //found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                StdOut.println("w is on stack: " + w);
                cycle = new Stack<Integer>();
                StdOut.println(Arrays.toString(edgeTo));
                for (int x = v; x != w; x = edgeTo[x]) {
                    StdOut.print("x=" + x + " ");
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                assert check();
            }
        }
        onStack[v] = false;
    }

    private void show(boolean[] b) {
        StdOut.print("onStack: ");
        for (int i=0; i< b.length; i++) {
            if (b[i]) {
                StdOut.print(i + " ");
            }
        }
    }

    /**
     * Does the digraph have a directed cycle?
     * @return <tt>true</tt> if the digraph has a directed cycle, <tt>false</tt> otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a directed cycle if the digraph has a directed cycle, and <tt>null</tt> otherwise.
     * @return a directed cycle (as an iterable) if the digraph has a directed cycle,
     *    and <tt>null</tt> otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }


    // certify that digraph has a directed cycle if it reports one
    private boolean check() {

        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) first = v;
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                return false;
            }
        }


        return true;
    }

    /**
     * Unit tests the <tt>DirectedCycle</tt> data type.
     */
    public static void main(String[] args) {
//        In in = new In(args[0]);
//        Digraph G = new Digraph(in);

        InputStream is = DigraphDemo.class.getResourceAsStream("/tinyDG.txt");
        In in = new In(new Scanner(is));
        Digraph G = new Digraph(in);

        DirectedCycleDemo finder = new DirectedCycleDemo(G);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

        else {
            StdOut.println("No directed cycle");
        }
        StdOut.println();
    }
}













