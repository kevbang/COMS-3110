package cs3110.hw4.code;

import java.io.IOException;

public class Test
{
    public static void main(String[] args) throws IOException {

        BitmapProcessor bitmap_processor = new BitmapProcessor("src/cs3110/hw4/test/ex1-upload.bmp");
        int[][] rgb_grid = bitmap_processor.getRGBMatrix();
        int height = rgb_grid.length;
        int width = rgb_grid[0].length;

        System.out.println("Height: " + height);
        System.out.println("Width: " + width);
        System.out.println("Total pixels: " + (height * width));

        int white_pixels = 0;
        int text_pixels = 0;
        boolean found_first_text = false;

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(rgb_grid[i][j] == 0xFFFFFFFF) { // white pixel
                    white_pixels++;
                }
                else {
                    text_pixels++;
                    if (!found_first_text) {
                        System.out.println("First text pixel found at: (" + i + ", " + j + ")");
                        found_first_text = true;
                    }
                }
            }
        }
        System.out.println("White pixels: " + white_pixels);
        System.out.println("Text pixels: " + text_pixels);

    }
}
