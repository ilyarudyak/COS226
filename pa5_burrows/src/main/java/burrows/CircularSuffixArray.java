package burrows;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by ilyarudyak on 12/22/15.
 */
public class CircularSuffixArray {

    private char[] chars;
    private int N;
    private Integer[] index;
    private Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer i1, Integer i2) {
            for (int i = 0; i < N; i++) {
                char ch1 = chars[(i1 + i) % N];
                char ch2 = chars[(i2 + i) % N];
                if ( ch1 < ch2) {
                    return -1;
                } else if(ch1 > ch2) {
                    return 1;
                }
            }
            return 0;
        }
    };

    // circular suffix array of chars
    public CircularSuffixArray(String s) {
        chars = s.toCharArray();
        N = chars.length;
        buildIndex();
    }

    private void buildIndex() {

        index = new Integer[N];
        for (int i = 0; i < N; i++) {
            index[i] = i;
        }

    }

    private void buildIndex2() {

        index = new Integer[N];
        for (int i = 0; i < N; i++) {
            index[i] = i;
        }
        Arrays.sort(index, comparator);
    }

    // length of chars
    public int length() {
        return N;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        return index[i];
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

        String s = new In("src/main/resources/abra.txt").readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        StdOut.println(Arrays.toString(csa.index));
    }
}













