package week3.radix;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LSD;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by ilyarudyak on 12/1/15.
 */
public class RadixDemo {

    public static void main(String[] args) {

        String[] a = new In("src/main/resources/tinyLSD.txt").readAllStrings();
        LSDCopy.sort(a, 4);
        StdOut.println();
        StdOut.println(Arrays.toString(a));
    }
}
