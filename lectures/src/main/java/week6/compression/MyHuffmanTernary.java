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
                    out.write(false);
                } else if (code.charAt(j) == '1') {
                    out.write(false);
                    out.write(true);
                } else if (code.charAt(j) == '2') {
                    out.write(true);
                    out.write(false);
                } else throw new IllegalStateException("Illegal state");
            }
        }
        out.close();
    }

    // helper methods
    private void buildCode(Node node, String s) {
        if (!node.isLeaf()) {
            buildCode(node.left,  s + '0');
            buildCode(node.middle,  s + '1');
            buildCode(node.right, s + '2');
        }
        else {
            codes[node.ch] = s;
        }
    }
    private void buildTrie() {

        // initialize priority queue with singleton trees
        MinPQ<Node> pq = new MinPQ<>();
        for (char c = 0; c < R; c++)
            if (freq[c] > 0)
                pq.insert(new Node(c, freq[c], null, null, null));

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left  = pq.delMin();
            Node middle = pq.delMin();
            Node right = pq.delMin();
            // we use '\0' as a placeholder for all non-leaf nodes
            Node parent = new Node('\0', left.freq + middle.freq + right.freq, left, middle, right);
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
                int code = get2bits(in);
                if (code == 0) {
                    node = node.left;
                } else if (code == 1){
                    node = node.middle;
                } else if (code == 2) {
                    node = node.right;
                } else {
                    throw new IllegalArgumentException("wrong code");
                }
            }
        }
    }

    private int get2bits(BinaryIn in) {
        if (!in.isEmpty()) {
            boolean bit1 = in.readBoolean();
            boolean bit2 = in.readBoolean();
            if (bit1 == false && bit2 == false) {
                return 0;
            } else if (bit1 == false && bit2 == true) {
                return 1;
            } else if (bit1 == true && bit2 == false) {
                return 2;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    // ----------- helper class -----------------

    // Huffman trie node
    private class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, middle, right;

        Node(char ch, int freq, Node left, Node middle, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.middle = middle;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            return (left == null) && (middle == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    public static void main(String[] args) {

        In in = new In("src/main/resources/abra3.txt");
//        BinaryOut out = new BinaryOut("src/main/resources/abra3.huffman");
        MyHuffmanTernary h = new MyHuffmanTernary(in);
//        h.compress(out);


//        In in = new In("src/main/resources/abra3.txt");
        BinaryIn in2 = new BinaryIn("src/main/resources/abra3.huffman");
        h.expand(in2);


    }
}














