package strings.tries;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * Created by ilyarudyak on 12/2/15.
 */
public class PrefixFreeCodes2 {

    private CodesTrie trie;

    public PrefixFreeCodes2(In in) {
        trie = new CodesTrie(in);
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
        PrefixFreeCodes2 pfc1 = new PrefixFreeCodes2(in1);
        StdOut.println(pfc1.check());

        In in2 = new In("src/main/resources/prefix2.txt");
        PrefixFreeCodes2 pfc2 = new PrefixFreeCodes2(in2);
        StdOut.println(pfc2.check());
    }
}


















