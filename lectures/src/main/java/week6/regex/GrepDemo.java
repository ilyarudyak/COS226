package week6.regex;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.NFA;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by ilyarudyak on 12/18/15.
 */
public class GrepDemo {

    private String regex;
    private NFA nfa;

    public GrepDemo(String regex) {
        this.regex = "(.*" + regex + ".*)";
        nfa = new NFA(this.regex);
    }

    public void matchAll(In in) {
        while (in.hasNextLine()) {
            String line = in.readLine();
            if (nfa.recognizes(line)) {
                StdOut.println(line);
            }
        }
    }

    public static void main(String[] args) {

        In in = new In("src/main/resources/tinyL.txt");
        GrepDemo grep = new GrepDemo("(A*B|AC)D");
        grep.matchAll(in);
    }
}
