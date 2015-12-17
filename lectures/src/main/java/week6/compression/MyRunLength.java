package week6.compression;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;

/**
 * Created by ilyarudyak on 12/16/15.
 */
public class MyRunLength {

    private static final int R    = 256;
    private static final int LG_R = 8;

    public void compress(String s) {

    }

    public void compress(BinaryIn in, BinaryOut out) {
        char run = 0;
        boolean old = false;
        while (!in.isEmpty()) {
            boolean b = in.readBoolean();
            if (b != old) {
                out.write(run, LG_R);
                run = 1;
                old = !old;
            }
            else {
                if (run == R-1) {
                    out.write(run, LG_R);
                    run = 0;
                    out.write(run, LG_R);
                }
                run++;
            }
        }
        out.write(run, LG_R);
        out.close();
    }

    public static void main(String[] args) {

//        BinaryIn in = new BinaryIn("src/main/resources/q64x96.bin");
//        BinaryOut out = new BinaryOut("src/main/resources/q64x96.rle.bin");
//
//        MyRunLength rl = new MyRunLength();
//        rl.compress(in, out);

//        BinaryIn in = new BinaryIn("src/main/resources/q32x48.bin");
//        MyPictureDump pd = new MyPictureDump(32, 48);
//        MyBinaryDump bd = new MyBinaryDump(32);
//        pd.dump(in);
//        bd.dump(in);

        MyRunLength rl = new MyRunLength();


    }
}



















