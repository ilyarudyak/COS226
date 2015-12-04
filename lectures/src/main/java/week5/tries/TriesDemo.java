package week5.tries;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TrieST;

/**
 * Created by ilyarudyak on 12/2/15.
 */
public class TriesDemo {

    public static void main(String[] args) {

        // build symbol table from standard input
        TrieST<Integer> trie = new TrieST<Integer>();
        String[] strings = new In("src/main/resources/shellsST.txt").readAllStrings();
        for (int i = 0; i < strings.length; i++) {
            String key = strings[i];
            trie.put(key, i);
        }

        // print results
//        if (trie.size() < 100) {
//            StdOut.println("keys(\"\"):");
//            for (String key : trie.keys()) {
//                StdOut.println(key + " " + trie.get(key));
//            }
//            StdOut.println();
//        }

        StdOut.println("longestPrefixOf(\"shellsort\"):");
        StdOut.println(trie.longestPrefixOf("shellsort"));
        StdOut.println();

        StdOut.println("longestPrefixOf(\"quicksort\"):");
        StdOut.println(trie.longestPrefixOf("quicksort"));
        StdOut.println();

        StdOut.println("keysWithPrefix(\"sea\"):");
        for (String s : trie.keysWithPrefix("sea"))
            StdOut.println(s);
        StdOut.println();

        StdOut.println("keysThatMatch(\".he\"):");
        for (String s : trie.keysThatMatch(".he"))
            StdOut.println(s);
    }
}
















