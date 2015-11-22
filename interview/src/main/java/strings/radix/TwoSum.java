package strings.radix;

import edu.princeton.cs.algs4.LSD;
import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

/**
 * see Ex. 1 in Web ex. http://algs4.cs.princeton.edu/51radix/
 * 2-sum. Given an array a of N 64-bit integers and a target value T,
 * determine whether there are two distinct integers i and j such
 * that ai+aj=T. Your algorithm should run in linear time in the
 * worst case. Solution. Radix sort the array in linear time. Scan
 * a pointer i from left to right and a pointer j from right to left:
 * consider a[i] + a[j]. If it is bigger than T, advance the j pointer;
 * if it is smaller than T, advance the i pointer; if it is equal to T,
 * we have found the desired indices.
 */
public class TwoSum {

    private static int LENGTH = 10;
    private static int LOW_BOUND = 10000;
    private static int BOUND = 1000;
    private static Random random = new Random(0);

    private int[] a;
    private int sum;

    public TwoSum(int sum) {
        this.a = generate();
        LSD.sort(a);
        this.sum = sum;
    }

    public boolean check() {
        int i = 0;
        int j = LENGTH - 1;
        while (i < j) {
            int temp = a[i] + a[j];
            if (temp == sum) {
                StdOut.printf("a[i]=%d a[j]=%d\n", a[i], a[j]);
                return true;
            } else if (temp < sum) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    private int[] generate() {
        int[] a = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            a[i] = LOW_BOUND + random.nextInt(BOUND);
        }
        return a;
    }

    public static void main(String[] args) {

//        TwoSum sum = new TwoSum(20962); // true
        TwoSum sum = new TwoSum(20961); //false
        StdOut.println(sum.check());

    }
}



















