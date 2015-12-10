package week6.compression;

import edu.princeton.cs.algs4.BinaryIn;

/**
 * Created by ilyarudyak on 12/10/15.
 */
public class GenomeDemo {

    public static void main(String[] args) {

        BinaryIn in = new BinaryIn("src/main/resources/abra.txt");
        MyHexDump dump = new MyHexDump(4);
        dump.dump(in);
    }

}
