package counting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ilyarudyak on 12/24/15.
 */
public class KeyIndexCounting {

    private Student[] a;
    private int N;
    private int R = 5;

    public KeyIndexCounting(In in) {
        a = new Student[in.readInt()];
        N = a.length;
        int index = 0;
        while (!in.isEmpty()) {
            String name = in.readString();
            int section = in.readInt();
            a[index++] = new Student(name, section);
        }
    }

    public void sort() {

        Student[] aux = new Student[N];
        int[] count = new int[R+1];

        // compute frequency
        for (int i = 0; i < N; i++)
            count[a[i].key() + 1]++;
        StdOut.println(Arrays.toString(count));

        // cumulative counts
        for (int r = 0; r < R; r++)
            count[r+1] += count[r];
        StdOut.println(Arrays.toString(count));

        // distribute the records
        for (int i = 0; i < N; i++) {
            aux[count[a[i].key()]++] = a[i];
        }
        StdOut.println(Arrays.toString(count));

        // copy back
        for (int i = 0; i < N; i++)
            a[i] = aux[i];

    }

    private class Student {

        private String name;
        private int section;

        private int key() {
            return section;
        }

        Student(String name, int section) {
            this.name = name;
            this.section = section;
        }

        @Override
        public String toString() {
            return name + ':' + section;
        }
    }

    public static void main(String[] args) {

        In in = new In("src/main/resources/sections.txt");
        KeyIndexCounting kic = new KeyIndexCounting(in);
//        StdOut.println(Arrays.toString(kic.a));
        kic.sort();
//        StdOut.println(Arrays.toString(kic.a));
    }
}















