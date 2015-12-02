package strings.tries;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TrieST;

import java.util.Iterator;

/**
 * Created by ilyarudyak on 12/2/15.
 */
public class PrefixFreeCodes {

    private TrieST trie;

    public PrefixFreeCodes(In in) {
        trie = new TrieST();
        String[] strings = in.readAllStrings();
        for (int i = 0; i < strings.length; i++) {
            String key = strings[i];
            trie.put(key, i);
        }
    }

    public boolean check() {
        for (Object key : trie.keys()) {
            Iterator<String> iterator = trie.keysWithPrefix((String) key).iterator();
            iterator.next();
            if (iterator.hasNext()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        In in1 = new In("src/main/resources/prefix1.txt");
        PrefixFreeCodes pfc1 = new PrefixFreeCodes(in1);
        StdOut.println(pfc1.check());

        In in2 = new In("src/main/resources/prefix2.txt");
        PrefixFreeCodes pfc2 = new PrefixFreeCodes(in2);
        StdOut.println(pfc2.check());
    }
}


















