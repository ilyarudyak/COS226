package week6.compression;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TrieST;

/**
 * Created by ilyarudyak on 12/18/15.
 */
public class SardinasPatterson {

    public static void main(String[] args) {

        TrieST<Integer> trie = new TrieST<>();
        String[] strings = new In("src/main/resources/sardinas.txt").readAllStrings();
        for (int i = 0; i < strings.length; i++) {
            String key = strings[i];
            trie.put(key, i);
        }

        StdOut.println(trie.keysWithPrefix("1"));

    }
}
