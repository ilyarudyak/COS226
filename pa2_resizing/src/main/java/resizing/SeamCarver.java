package resizing;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

/**
 * This is pa2 seam carver from cos226 Princeton, part 2.
 * Seam-carving is a content-aware image resizing technique invented by
 * Princeton graduates and used in Photoshop. We calculate energy profile
 * of a picture and then we
 */
public class SeamCarver {

    private Picture picture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
    }

    // getters
    public Picture picture() {
        return picture;
    }
    public int width() {
        return picture.width();
    }
    public int height() {
        return picture.height();
    }

    // ---------------------------------------------

    // (1) energy of pixel at column x and row y
    public double energy(int x, int y) {
        return 0;
    }

    // helper methods
    private int dualGradient(int x, int y) {
        Color colorLeft = picture.get(x - 1, y);
        return 0;
    }

    // ---------------------------------------------

    // (2a) sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return null;
    }

    // (2b) sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return null;
    }

    // ---------------------------------------------

    // (3a) remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {

    }

    // (3b) remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {

    }
}













