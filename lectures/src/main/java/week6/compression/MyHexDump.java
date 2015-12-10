package week6.compression;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by ilyarudyak on 12/10/15.
 */
public class MyHexDump {

    private static final int BYTE_SIZE = 8;
    private static final int STANDARD_LINE_SIZE = 16;
    private int bytesPerLine;

    public MyHexDump() {
        bytesPerLine = STANDARD_LINE_SIZE;
    }

    public MyHexDump(int bytesPerLine) {
        this.bytesPerLine = bytesPerLine;
    }

    public void dump(BinaryIn in) {
        int byteCount;
        for (byteCount = 0; !in.isEmpty(); byteCount++) {

            // print space between bytes etc.
            if (byteCount == 0) {
                StdOut.printf("");
            } else if (byteCount % bytesPerLine == 0) {
                StdOut.printf("\n", byteCount);
            } else {
                StdOut.print(" ");
            }

            char c = in.readChar();

            StdOut.printf("%02x", (int) c);
        }

        // print bits count
        StdOut.println();
        StdOut.println((byteCount * BYTE_SIZE) + " bits");
    }
}















