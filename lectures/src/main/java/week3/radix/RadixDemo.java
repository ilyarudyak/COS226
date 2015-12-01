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

//        String[] a = new In("src/main/resources/tinyLSD.txt").readAllStrings();
//        LSDCopy.sort(a, 4);
//        StdOut.println();
//        StdOut.println(Arrays.toString(a));

//        String[] a = new In("src/main/resources/tinyMSD.txt").readAllStrings();
//        MSDCopy.sort(a);
//        StdOut.println();
//        StdOut.println(Arrays.toString(a));

        String[] a = new In("src/main/resources/tiny3way.txt").readAllStrings();
        Quick3StringCopy.sort(a);
        StdOut.println();
        StdOut.println(Arrays.toString(a));
    }
}





















