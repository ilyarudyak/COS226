package strings.tries;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class CodesTrie2 {

    private static final int R = 2;
    private static final int D = 48;


    private Node root;
    private int N;

    private static class Node {
        private Integer val;
        private Node[] next = new Node[R];
    }

    public CodesTrie2(In in) {

        String[] a = in.readAllStrings();
        for (int i = 0; i < a.length; i++) {
            put(a[i], i);
        }
    }

    public Integer get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }
    public void put(String key, Integer val) {
        root = put(root, key, val, 0);
    }
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new Queue<>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    // helper methods
    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        int c = key.charAt(d) - D;
        return get(x.next[c], key, d+1);
    }
    private Node put(Node x, String key, Integer val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (x.val == null) N++;
            x.val = val;
            return x;
        }
        int c = key.charAt(d) - D;
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }
    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
        if (x == null) return;
        if (x.val != null) results.enqueue(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append((char)(c + D));
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public static void main(String[] args) {

        In in = new In("src/main/resources/prefix1.txt");
        CodesTrie2 trie = new CodesTrie2(in);

        for (String key : trie.keys()) {
            StdOut.println(key + " " + trie.get(key));
        }


    }
}
















