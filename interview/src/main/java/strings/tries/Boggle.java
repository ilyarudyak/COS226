package strings.tries;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TrieST;

import java.util.*;

/**
 * Created by ilyarudyak on 12/3/15.
 */
public class Boggle {

    public static final int WORDS_NUMBER = 10;
    public static final int WORDS_LENGTH = 5;

    private TrieST trie;
    private Grid grid;
    Set<String> words;

    public Boggle() {
        In in = new In("src/main/resources/words");
        trie = new TrieST();
        String[] a = in.readAllStrings();
        for (int i = 0; i < a.length; i++) {
            trie.put(a[i].toLowerCase(), i);
        }

        In in2 = new In("src/main/resources/boggleF239.txt");
        grid = new Grid(in2);

        words = new HashSet<>();
        solve(WORDS_LENGTH);
    }

    // generate random words of given size and
    // check if they are in a dictionary
    public void solve(int size) {
        while (words.size() <= WORDS_NUMBER) {
            String word = grid.word(size).toLowerCase();
            if (trie.contains(word)) {
                words.add(word);
                StdOut.println(words.size());
            }
        }

    }


    private static class Grid {

        private int N = 4;
        private int M = 8;
        private char[][] grid;
        private Random random;
        private boolean[][] marked;

        public Grid(In in) {
            grid = new char[N][N];
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N; j++) {
                    if (in.hasNextChar()) {
                        char c = in.readChar();
                        if (c != '\n') {
//                            StdOut.print(c);
                            grid[i][j] = c;
                        }
                    }
                }
//                StdOut.println();
            }
            random = new Random();
            marked = new boolean[N][N];
        }

        // generate random word with length l
        public String word(int l) {

            int i = random.nextInt(N);
            int j = random.nextInt(N);
            String w = "";
            while (w.length() != l) {
                int move = random.nextInt(M);
                switch (move) {
                    case 0:
                        if (right(i, j)) {
                            j += 1;
                            if(!marked[i][j]) {
                                w += grid[i][j];
                            }
                            marked[i][j] = true;
                        }
                        break;
                    case 1:
                        if (left(i, j)) {
                            j -= 1;
                            if(!marked[i][j]) {
                                w += grid[i][j];
                            }
                            marked[i][j] = true;
                        }
                        break;
                    case 2:
                        if (up(i, j)) {
                            i += 1;
                            if(!marked[i][j]) {
                                w += grid[i][j];
                            }
                            marked[i][j] = true;
                        }
                        break;
                    case 3:
                        if (down(i, j)) {
                            i -= 1;
                            if(!marked[i][j]) {
                                w += grid[i][j];
                            }
                            marked[i][j] = true;
                        }
                        break;

                    case 4:
                        if (rightUp(i, j)) {
                            i -= 1;
                            j += 1;
                            if(!marked[i][j]) {
                                w += grid[i][j];
                            }
                            marked[i][j] = true;
                        }
                        break;
                    case 5:
                        if (leftUp(i, j)) {
                            i -= 1;
                            j -= 1;
                            if(!marked[i][j]) {
                                w += grid[i][j];
                            }
                            marked[i][j] = true;
                        }
                        break;
                    case 6:
                        if (rightDown(i, j)) {
                            i += 1;
                            j += 1;
                            if(!marked[i][j]) {
                                w += grid[i][j];
                            }
                            marked[i][j] = true;
                        }
                        break;
                    case 7:
                        if (leftDown(i, j)) {
                            i += 1;
                            j -= 1;
                            if(!marked[i][j]) {
                                w += grid[i][j];
                            }
                            marked[i][j] = true;
                        }
                        break;
                }
            }
            clear();
            return w;
        }

        // helper methods
        private void clear() {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    marked[i][j] = false;
                }
            }
        }
        private boolean right(int i, int j) {
            if (out(i, j)) { return false; }
            if (j == N - 1) { return false; }
            return true;
        }
        private boolean left(int i, int j) {
            if (out(i, j)) { return false; }
            if (j == 0) { return false; }
            return true;
        }
        private boolean up(int i, int j) {
            if (out(i, j)) { return false; }
            if (i == N - 1) { return false; }
            return true;
        }
        private boolean down(int i, int j) {
            if (out(i, j)) { return false; }
            if (i == 0) { return false; }
            return true;
        }
        private boolean out(int i, int j) {
            if (i < 0 || i > N - 1 || j < 0 || j > N - 1) { return true; }
            return false;
        }

        private boolean rightUp(int i, int j) {
            if (out(i, j)) { return false; }
            if (i == 0 || j == N - 1) { return false; }
            return true;
        }
        private boolean leftUp(int i, int j) {
            if (out(i, j)) { return false; }
            if (i == 0 || j == 0) { return false; }
            return true;
        }
        private boolean rightDown(int i, int j) {
            if (out(i, j)) { return false; }
            if (i == N - 1 || j == N - 1) { return false; }
            return true;
        }
        private boolean leftDown(int i, int j) {
            if (out(i, j)) { return false; }
            if (i == N - 1 || j == 0) { return false; }
            return true;
        }

        public void show() {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    StdOut.print(grid[i][j] + " ");
                }
                StdOut.println();
            }
        }
    }

    public static void main(String[] args) {

        Boggle boggle = new Boggle();
        StdOut.println(boggle.words);
    }
}

















