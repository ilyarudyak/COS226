package resizing;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

/**
 * This is pa2 seam carver from cos226 Princeton, part 2.
 * Seam-carving is a content-aware image resizing technique invented by
 * Princeton graduates and used in Photoshop. We calculate energy profile
 * of a picture and then we try to find 1-pixel wide path (both
 * vertical and horizontal) with min energy (and then delete this path).
 */
public class SeamCarver {

    private static final int BORDER_ENERGY = 1000;
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
        if (isOnBorder(x, y)) {
            return BORDER_ENERGY;
        }
        return Math.sqrt(dualGradientX(x, y) + dualGradientY(x, y));
    }

    // helper methods
    private boolean isOnBorder(int x, int y) {
        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) {
            return true;
        }
        return false;
    }
    private double dualGradientX(int x, int y) {
        Color colorLeft = picture.get(x - 1, y);
        Color colorRight = picture.get(x + 1, y);

        return  Math.pow(colorRight.getRed() - colorLeft.getRed(), 2) +
                Math.pow(colorRight.getGreen() - colorLeft.getGreen(), 2) +
                Math.pow(colorRight.getBlue() - colorLeft.getBlue(), 2);
    }
    private double dualGradientY(int x, int y) {
        Color colorBottom = picture.get(x, y - 1);
        Color colorUp = picture.get(x, y + 1);

        return  Math.pow(colorUp.getRed() - colorBottom.getRed(), 2) +
                Math.pow(colorUp.getGreen() - colorBottom.getGreen(), 2) +
                Math.pow(colorUp.getBlue() - colorBottom.getBlue(), 2);
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













