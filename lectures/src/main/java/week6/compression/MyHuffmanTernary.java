package week6.compression;

import edu.princeton.cs.algs4.*;

/**
 * Created by ilyarudyak on 12/16/15.
 */
public class MyHuffmanTernary {

    // alphabet size of extended ASCII
    private static final int R = 127;

    private String text;
    private int[] freq;
    private Node root;
    // codes['A'] = "001" etc.
    private String[] codes;

    public MyHuffmanTernary(In in) {
        text = in.readString();
        freq = new int[R];
        codes = new String[R];
        buildFreq();
        buildTrie();
        buildCode(root, "");
    }

    // ----------- compression -----------------

    public void compress(BinaryOut out) {

        // use Huffman code to encode input
        char[] input = text.toCharArray();
        for (int i = 0; i < input.length; i++) {
            String code = codes[input[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    out.write(false);
                } else if (code.charAt(j) == '1') {
                    out.write(true);
                } else throw new IllegalStateException("Illegal state");
            }
        }
        out.close();
    }

    // helper methods
    private void buildCode(Node node, String s) {
        if (!node.isLeaf()) {
            buildCode(node.left,  s + '0');
            buildCode(node.right, s + '1');
        }
        else {
            codes[node.ch] = s;
        }
    }
    private void buildTrie() {

        // initialze priority queue with singleton trees
        MinPQ<Node> pq = new MinPQ<>();
        for (char i = 0; i < R; i++)
            if (freq[i] > 0)
                pq.insert(new Node(i, freq[i], null, null));

        // special case in case there is only one character with a nonzero frequency
        if (pq.size() == 1) {
            if (freq['\0'] == 0) pq.insert(new Node('\0', 0, null, null));
            else                 pq.insert(new Node('\1', 0, null, null));
        }

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left  = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        root = pq.delMin();
    }
    private void buildFreq() {
        char[] input = text.toCharArray();
        for (int i = 0; i < input.length; i++)
            freq[input[i]]++;
    }
    private void showFreq() {
        for (int i = 0; i < freq.length; i++) {
            if(freq[i] != 0) {
                StdOut.printf("%c:%d\n", i, freq[i]);
            }
        }
    }
    private void showCodes() {
        for (int i = 0; i < codes.length; i++) {
            if(codes[i] != null) {
                StdOut.printf("%c:%s\n", i, codes[i]);
            }
        }
    }

    // ---------- expansion ---------------------

    public void expand(BinaryIn in) {
        Node node = root;
        while (!in.isEmpty()) {
            if (node.isLeaf()) {
                StdOut.print(node.ch);
                node = root;
            } else {
                if (!in.readBoolean()) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }
    }

    // ----------- helper class -----------------

    // Huffman trie node
    private class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    public static void main(String[] args) {

//        In in = new In("src/main/resources/abra.txt");
//        BinaryOut out = new BinaryOut("src/main/resources/abra.huffman");
//        MyHuffman h = new MyHuffman(in);
//        h.compress(out);

//        BinaryIn in = new BinaryIn("src/main/resources/abra.huffman");
//        MyBinaryDump bd = new MyBinaryDump();
//        bd.dump(in);

        In in = new In("src/main/resources/abra2.txt");
        MyHuffmanTernary h = new MyHuffmanTernary(in);
        h.showFreq();
        h.showCodes();
//        h.expand(new BinaryIn("src/main/resources/abra.huffman"));

    }
}














