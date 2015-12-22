package burrows;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdIn;

/**
 * Created by ilyarudyak on 12/22/15.
 */
public class BurrowsWheeler {

    // apply Burrows-Wheeler encoding, reading from
    // standard input and writing to standard output
    public static void encode() {

        String s = BinaryStdIn.readString();
        char[] chars = s.toCharArray();
        CircularSuffixArray csa = new CircularSuffixArray(s);

        for (int i = 0; i < s.length(); i++) {
            if (csa.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            int index = csa.index(i);
            int j;
            if (index > 0) {
                j = index - 1;
            } else {
                j = s.length() - 1;
            }
            BinaryStdOut.write(chars[j]);
        }

        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler decoding, reading from
    // standard input and writing to standard output
    public static void decode() {

    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
        if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
