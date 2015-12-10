package week6.compression;

import edu.princeton.cs.algs4.*;

/**
 * Created by ilyarudyak on 12/10/15.
 */
public class MyGenome {

    private static final Alphabet DNA = Alphabet.DNA;//new Alphabet("ACGT");

    // Do not instantiate.
    private MyGenome() { }

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses them using two bits per
     * character; and writes the results to standard output.
     */
    public static void compress(BinaryIn in, BinaryOut out) {
        String s = in.readString();
        int N = s.length();
        out.write(N);

        // Write two-bit code for char.
        for (int i = 0; i < N; i++) {

            // this returns 0 for 'A', 1 for 'C', 2 for 'T' and 3 for 'G'
            int d = DNA.toIndex(s.charAt(i));
//            StdOut.println(s.charAt(i) + " d=" + d);
            out.write(d, 2);
        }
        out.close();
    }

    /**
     * Reads a binary sequence from standard input; converts each two bits
     * to an 8-bit extended ASCII character over the alphabet { A, C, T, G };
     * and writes the results to standard output.
     */
    public static void expand(BinaryIn in, BinaryOut out) {
        int N = in.readInt();
        // Read two bits; write char.
        for (int i = 0; i < N; i++) {
            char c = in.readChar(2);
            out.write(DNA.toChar(c), 8);
        }
        out.close();
    }
}
