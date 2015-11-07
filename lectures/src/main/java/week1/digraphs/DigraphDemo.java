package week1.digraphs;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by ilyarudyak on 11/6/15.
 */
public class DigraphDemo {

    public static void main(String[] args) {

        InputStream is = DigraphDemo.class.getResourceAsStream("/tinyDG2.txt");
        In in = new In(new Scanner(is));

        Digraph dg = new Digraph(in);
        StdOut.println(dg);
    }
}
