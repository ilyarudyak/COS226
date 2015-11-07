package week1.digraphs;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by ilyarudyak on 11/6/15.
 */
public class BFSDemo {

    public static void main(String[] args) {

        InputStream is = DigraphDemo.class.getResourceAsStream("/tinyDG.txt");
        In in = new In(new Scanner(is));
        Digraph G = new Digraph(in);

        int s = 0;
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("->" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d (-):  not connected\n", s, v);
            }

        }
    }
}
