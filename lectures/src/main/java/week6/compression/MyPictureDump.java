package week6.compression;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Picture;

import java.awt.*;

/**
 * Created by ilyarudyak on 12/16/15.
 */
public class MyPictureDump {

    private int width;
    private int height;
    private Picture picture;

    public MyPictureDump(int width, int height) {
        this.width = width;
        this.height = height;
        picture = new Picture(width, height);
    }

    public void dump(BinaryIn in) {
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                picture.set(col, row, Color.RED);
                if (!in.isEmpty()) {
                    boolean bit = in.readBoolean();
                    if (bit) {
                        picture.set(col, row, Color.BLACK);
                    } else {
                        picture.set(col, row, Color.WHITE);
                    }
                }
            }
        }
        picture.show();
    }

}

















