package week6.compression;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by ilyarudyak on 12/10/15.
 */
public class MyBinaryDump {

    private static final int BYTE = 8;
    private static final int STANDARD_LINE_SIZE = 16;
    private int bitsPerLine;

    public MyBinaryDump() {
        bitsPerLine = STANDARD_LINE_SIZE;
    }

    public MyBinaryDump(int bitsPerLine) {
        this.bitsPerLine = bitsPerLine;
    }

    public void dump(BinaryIn in) {

        int bitCount;
        for (bitCount = 0; !in.isEmpty(); bitCount++) {

            // print space after each byte and new line if we reach bitCount
            if (bitCount !=  0 && bitCount % bitsPerLine != 0 && bitCount % BYTE == 0) {
                StdOut.print(" ");
            } else if (bitCount !=  0 && bitCount % bitsPerLine == 0) {
                StdOut.println();
            }

            // read bit-by-bit
            if (in.readBoolean()) {
                StdOut.print(1);
            } else {
                StdOut.print(0);
            }

        }

        // print bits count
        StdOut.println();
        StdOut.println(bitCount + " bits");
    }


}
