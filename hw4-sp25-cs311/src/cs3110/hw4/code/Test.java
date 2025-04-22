package cs3110.hw4.code;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {

        String path = "src/cs3110/hw4/test/test1.bmp";
        BitmapProcessor bitmap_processor = new BitmapProcessor(path);

        int[][] rgb_grid = bitmap_processor.getRGBMatrix();
        int height = rgb_grid.length;
        int width = rgb_grid[0].length;

        System.out.println("Height: " + height);
        System.out.println("Width: " + width);

        Pair<List<Integer>, List<Integer>> whitespace = CharacterSeparator.findSeparationWeighted(path);
        List<Integer> whitespace_rows = whitespace.getFirst();
        List<Integer> whitespace_cols = whitespace.getSecond();

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