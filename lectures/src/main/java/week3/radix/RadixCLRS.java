package week3.radix;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * This is an implementation of radix sort from CLRS.
 * Created by ilyarudyak on 12/4/15.
 */
public class RadixCLRS {

    public static final int WORD_LENGTH = 3;
    private static final int k = 9;

    private String[] A;
    private String[] B;
    private int[] C;

    public RadixCLRS(In in) {
        buildA(in);
        B = new String[A.length];
        C = new int[k + 1];
    }

    private void buildA(In in) {
        String[] words = in.readAllStrings();
        A = new String[words.length+1];
        for (int i = 0; i < words.length; i++) {
            A[i+1] = words[i];
        }
    }

    public void sort() {
        for (int i = WORD_LENGTH - 1; i >= 0; i--) {
            sort(i);
        }
    }

    public void sort(int index) {
        for (int i = 1; i < A.length; i++) {
            int j = Integer.parseInt(A[i].substring(index, index+1));
            C[j] += 1;
        }

        StdOut.println(Arrays.toString(C));

        for (int i = 1; i < C.length; i++) {
            C[i] += C[i-1];
        }

        for (int i = A.length - 1; i > 0; i--) {
            int j = Integer.parseInt(A[i].substring(index, index+1));
            B[C[j]] = A[i];
            C[j]--;
        }

        // copy from B to A
        for (int i = 0; i < A.length; i++) {
            A[i] = B[i];
        }

        // clear C
        for (int i = 0; i < C.length; i++) {
            C[i] = 0;
        }
    }

    private static class CountingSort {

        private static final int k = 9;

        private int[] A;
        private int[] B;
        private int[] C;

        public CountingSort(int[] A) {
            this.A = A;
            B = new int[A.length];
            C = new int[k + 1];
        }

        public void sort() {
            for (int i = 1; i < A.length; i++) {
                C[A[i]] += 1;
            }


            for (int i = 1; i < C.length; i++) {
                C[i] += C[i-1];
            }

            for (int i = A.length - 1; i > 0; i--) {
                B[C[A[i]]] = A[i];
                C[A[i]]--;
            }
        }


    }

    public static void main(String[] args) {

//        int[] A = {0, 2, 5, 3, 0, 2, 3, 0, 3};
//        CountingSort cs = new CountingSort(A);
//        cs.sort();
//        StdOut.println(Arrays.toString(A));

        In in = new In("src/main/resources/radix");
        RadixCLRS rs = new RadixCLRS(in);
        StdOut.println(Arrays.toString(rs.A));
        rs.sort();
        StdOut.println(Arrays.toString(rs.A));


    }


}
















