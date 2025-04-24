package cs3110.hw4.test;

import cs3110.hw4.code.BitmapProcessor;
import cs3110.hw4.code.CharacterSeparator;
import cs3110.hw4.code.Pair;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {

        String path = "src/cs3110/hw4/test/bmp_files/ex1-upload.bmp";
        BitmapProcessor bitmap_processor = new BitmapProcessor(path);

        int[][] rgb_grid = bitmap_processor.getRGBMatrix();
        int height = rgb_grid.length;
        int width = rgb_grid[0].length;

        System.out.println("Height: " + height);
        System.out.println("Width: " + width);

        int min_pixel = 0xFFFFFFFF; // Initialize to the maximum possible value
        int max_pixel = 0xFF000000; // Initialize to the minimum possible value
        String min_location = "";
        String max_location = "";

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int pixel = rgb_grid[row][col];

                if (pixel < min_pixel) {
                    min_pixel = pixel;
                    min_location = "(" + row + ", " + col + ")";
                }

                if (pixel > max_pixel) {
                    max_pixel = pixel;
                    max_location = "(" + row + ", " + col + ")";
                }
            }
        }

        System.out.println("Darkest pixel at: " + min_location + " with value: " + Integer.toHexString(min_pixel) + " " + -1 * min_pixel);
        System.out.println("Lightest pixel at: " + max_location + " with value: " + Integer.toHexString(max_pixel) + " " + -1 * max_pixel);

        Pair<List<Integer>, List<Integer>> whitespace = CharacterSeparator.findSeparationWeighted(path);

        List<Integer> whitespace_rows = whitespace.getFirst();
        List<Integer> whitespace_cols = whitespace.getSecond();

        System.out.println("Whitespace rows: " + whitespace_rows);
        System.out.println("Whitespace cols: " + whitespace_cols);

        final int RED = 0xFFFF0000;
        final int GREEN = 0xFF00FF00;

        try {
            bitmap_processor.updateRGBMatrix(rgb_grid);
            bitmap_processor.writeToFile();
        } catch (Exception e) {
            System.out.println("Error updating RGB matrix: " + e.getMessage());
        }


    }

}