package week6.compression;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by ilyarudyak on 12/10/15.
 */
public class GenomeDemo {

    public static void main(String[] args) {

//        BinaryIn in = new BinaryIn("src/main/resources/genomeTiny.txt");
//        BinaryOut out = new BinaryOut("src/main/resources/compressed.bin");
//        MyGenome.compress(in, out);
//
//        BinaryIn in2 = new BinaryIn("src/main/resources/compressed.bin");
//        BinaryOut out2 = new BinaryOut("src/main/resources/decompressed.bin");
//        MyGenome.expand(in2, out2);


//        BinaryIn in = new BinaryIn("src/main/resources/genomeTiny.txt");
//        BinaryIn in2 = new BinaryIn("src/main/resources/compressed.bin");
//        MyHexDump hd = new MyHexDump();
//        hd.dump(in);
//        StdOut.println();
//        hd.dump(in2);

        BinaryIn in = new BinaryIn("src/main/resources/genomeVirus.txt");
        MyPictureDump pd = new MyPictureDump(512, 100);
        pd.dump(in);

        BinaryIn in2 = new BinaryIn("src/main/resources/genomeVirus.txt");
        BinaryOut out = new BinaryOut("src/main/resources/compressedVirus.bin");
        MyGenome.compress(in2, out);
        BinaryIn in3 = new BinaryIn("src/main/resources/compressedVirus.bin");
        MyPictureDump pd2 = new MyPictureDump(512, 25);
        pd2.dump(in3);


    }
}
