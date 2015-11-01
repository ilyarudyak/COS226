package demo;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by ilyarudyak on 10/31/15.
 */
public class Demo {

    public static void main(String[] args) {

        In in = new In ("src/main/resources/demo/tinyDG.txt");
        Digraph G = new Digraph(in);

        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
                StdOut.println(v + "->" + w);
    }
}
