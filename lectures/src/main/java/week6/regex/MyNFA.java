package week6.regex;

import edu.princeton.cs.algs4.*;

public class MyNFA {

    private Digraph G;         // digraph of epsilon transitions
    private String regexp;     // regular expression
    private int M;             // number of characters in regular expression

    /**
     * Initializes the NFA from the specified regular expression.
     *
     * @param  regexp the regular expression
     */
    public MyNFA(String regexp) {
        this.regexp = regexp;
        M = regexp.length();
        buildNFA();
    }

    private void buildNFA() {

        // stack to store parens and |
        Stack<Integer> stack = new Stack<>();

        // digraph contains e-transitions
        G = new Digraph(M+1);

        int multi = 0;
        for (int i = 0; i < M; i++) {

            // lp - left paren
            int lp = i;


            if (regexp.charAt(i) == '(') {
                stack.push(i);
            } else if (regexp.charAt(i) == '|') {
                stack.push(i);
                multi++;
            } else if (regexp.charAt(i) == ')') {

                // index of | in regex
                int or = stack.pop();

                // 2-way or operator
                if (regexp.charAt(or) == '|' && multi == 1) {
                    lp = stack.pop();
                    G.addEdge(lp, or+1);
                    G.addEdge(or, i);
                // 3-way operator
                } else if (regexp.charAt(or) == '|' && multi == 2) {
                    int or2 = stack.pop();
                    lp = stack.pop();
                    StdOut.println("lp=" + lp);
                    G.addEdge(lp, or+1);
                    G.addEdge(or, i);
                    G.addEdge(lp, or2+1);
                    G.addEdge(or2, i);
                    multi = 0;
                } else if (regexp.charAt(or) == '(') {
                    lp = or;
                } else {
                    assert false;
                }
            }

            StdOut.println(regexp.charAt(i) + " " + stack + " multi=" + multi);

            // closure operator (uses 1-character lookahead)
            if (i < M-1 && regexp.charAt(i+1) == '*') {
                G.addEdge(lp, i+1);
                G.addEdge(i+1, lp);
            }
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '*' || regexp.charAt(i) == ')')
                G.addEdge(i, i+1);

        } // end of main loop

        if (stack.size() != 0)
            throw new IllegalArgumentException("Invalid regular expression");
    }

    /**
     * Returns true if the text is matched by the regular expression.
     * 
     * @param  txt the text
     * @return <tt>true</tt> if the text is matched by the regular expression,
     *         <tt>false</tt> otherwise
     */
    public boolean recognizes(String txt) {
        DirectedDFS dfs = new DirectedDFS(G, 0);
        Bag<Integer> pc = new Bag<Integer>();
        for (int v = 0; v < G.V(); v++)
            if (dfs.marked(v)) pc.add(v);

        // Compute possible NFA states for txt[i+1]
        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == '*' || txt.charAt(i) == '|' || txt.charAt(i) == '(' || txt.charAt(i) == ')')
                throw new IllegalArgumentException("text contains the metacharacter '" + txt.charAt(i) + "'");

            Bag<Integer> match = new Bag<Integer>();
            for (int v : pc) {
                if (v == M) continue;
                if ((regexp.charAt(v) == txt.charAt(i)) || regexp.charAt(v) == '.')
                    match.add(v+1); 
            }
            dfs = new DirectedDFS(G, match); 
            pc = new Bag<Integer>();
            for (int v = 0; v < G.V(); v++)
                if (dfs.marked(v)) pc.add(v);

            // optimization if no states reachable
            if (pc.size() == 0) return false;
        }

        // check for accept state
        for (int v : pc)
            if (v == M) return true;
        return false;
    }

    /**
     * Unit tests the <tt>NFA</tt> data type.
     */
    public static void main(String[] args) {
//        String regexp = "((A*B|AC)D)";
        String regexp = "(.*AB((C|D|E)F)*G)";
        String txt = "ABG";
        MyNFA nfa = new MyNFA(regexp);
        StdOut.println(nfa.recognizes(txt));
    }

}













