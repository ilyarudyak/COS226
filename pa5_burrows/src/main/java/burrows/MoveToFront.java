package burrows;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ilyarudyak on 12/22/15.
 */
public class MoveToFront {

    // alphabet size of extended ASCII
    private static final int R = 256;

    // apply move-to-front encoding, reading from
    // standard input and writing to standard output
    public static void encode() {

        // initialize alphabet array
        List<Integer> A = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            A.add(i);
        }

        // read the input
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        // print number of bytes in original uncompressed message
        BinaryStdOut.write(input.length);

        //  for each symbol of the input sequence:
        //      output the index of the symbol in the symbol table
        //      move that symbol to the front of the symbol table
        for (int i = 0; i < input.length; i++) {
            int ch = input[i];
            int index = A.indexOf(ch);

//            StdOut.printf("%c:%2d index=%02x ", ch, ch, index);
//            StdOut.print(A.toString().substring(0, 20) + " ...  ");

            BinaryStdOut.write(index, 8);
            A.remove(index);
//            StdOut.println(A.toString().substring(0, 20) + " ...");
            A.add(0, ch);
        }

        // close output stream
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from
    // standard input and writing to standard output
    public static void decode() {

        // initialize alphabet array
        List<Integer> A = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            A.add(i);
        }

        // number of bytes to write
        int length = BinaryStdIn.readInt();
//        StdOut.printf("length=%d\n", length);

        // for each index of the input sequence:
        //      output the symbol at that index of the symbol table
        //      move that symbol to the front of the symbol table
        for (int i = 0; i < length; i++) {
            int index = BinaryStdIn.readInt(8);
            int ch = A.get(index);

//            StdOut.printf("%2c:%2d index=%02d ", ch, ch, index);
//            StdOut.println(A.toString().substring(0, 20) + " ...  ");

            StdOut.print((char) ch);
            A.remove(index);
//            StdOut.println(A.toString().substring(0, 20) + " ...");
            A.add(0, ch);
        }
        StdOut.println();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
