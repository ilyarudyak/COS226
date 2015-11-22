package strings.radix;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LSD;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Given N distinct strings, each of length L, design an algorithm
 * to determine whether there exists a pair of distinct strings
 * that are cyclic rotations of one another.
 *
 * This question is different from this one: Given two string s1 and s2
 * how will you check if s1 is a rotated version of s2 ?
 */
public class CyclicRotations {

    private static int W = 10;

    private String[] words;

    public CyclicRotations(String[] words) {
        this.words = words;
    }

    public boolean check() {
        return duplicates(fingerprints());
    }

    // helper methods
    private String[] fingerprints() {
        String[] fingeprints = new String[words.length];
        int i = 0;
        int length = rotateAllSymbols(words[0]).length();
        for (String word : words) {
            fingeprints[i++] = rotateAllSymbols(word);
        }
        LSD.sort(fingeprints, length);
        return fingeprints;
    }
    private boolean duplicates(String[] fingeprints) {
        String temp = fingeprints[0];
        for (int i = 1; i < fingeprints.length; i++) {
            if (temp.equals(fingeprints[i])) {
                StdOut.println(temp);
                return true;
            }
            temp = fingeprints[i];
        }
        return false;
    }
    private String rotateAllSymbols(String s) {
        String[] rotations = new String[s.length()];
        rotations[0] = s;
        for (int i = 1; i < s.length(); i++) {
            s = rotateOneSymbol(s);
            rotations[i] = s;
        }

        LSD.sort(rotations, W);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rotations.length; i++) {
            if (i < rotations.length - 1) {
                sb.append(rotations[i] + "-");
            } else {
                sb.append(rotations[i]);
            }
        }

        return sb.toString();
    }
    private String rotateOneSymbol(String s) {
        return s.substring(1) + s.substring(0, 1);
    }
    private boolean check2Str(String s1, String s2) {
        return (s1.length() == s2.length()) && ((s1+s1).indexOf(s2) != -1);
    }

    public static void main(String[] args) {

        String[] words = new In("src/main/resources/rotations.txt").readAllLines();
        CyclicRotations rotation = new CyclicRotations(words);

        StdOut.println(rotation.check());

    }
}











