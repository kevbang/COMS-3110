package cs3110.hw4.code;
/**
 * @author Kevin Tran
 * 4/20/2025
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CharacterSeparator {
    /**
     * This method uses the WeightedAdjacencyList class to identify the space between characters in an image of text.
     * For efficiency, it should only construct a single graph object and should only make a constant
     * number of calls to Dijkstra's algorithm.
     * @param path The location of the image on disk.
     * @return Two lists of Integer. The first list indicates whitespace rows. The second list indicates whitespace columns. Returns null if some error occurred loading the image.
     */
    public static <T> Pair<List<Integer>, List<Integer>> findSeparationWeighted(String path) {

        try {

            BitmapProcessor bitmap_processor = new BitmapProcessor(path);
            int[][] rgb_grid = bitmap_processor.getRGBMatrix();

            int height = rgb_grid.length;
            int width = rgb_grid[0].length;

            // Create graph

            List<Pair<Integer, Integer>> vertices = new ArrayList<>();
            for(int row = 0; row < height; row++) {
                for(int col = 0; col < width; col++) {
                    vertices.add(new Pair<>(row, col));
                }
            }

            WeightedAdjacencyList<Pair<Integer, Integer>> graph = new WeightedAdjacencyList<>(vertices);
            int[][] directions = {
                    {0, 1}, // right
                    {1, 0}, // down
                    {0, -1}, // left
                    {-1, 0} // up
            };
            // Add edges to the graph

            for(int row = 0; row < height; row++) {
                for(int col = 0; col < width; col++) {
                    Pair<Integer, Integer> from = new Pair<>(row, col);

                    for(int[] direction : directions) {
                        int new_row = row + direction[0];
                        int new_col = col + direction[1];

                        // if row is within bounds, and column is within bounds, create edge
                        if(new_row >= 0 && new_row < height && new_col >= 0 && new_col < width) {
                            Pair<Integer, Integer> to = new Pair<>(new_row, new_col);
                            // weight is 1 if the pixel is white, 0 if the pixel is black

                            int weight = (rgb_grid[row][col] == 0xFFFFFFFF) ? 0 : 1;
                            graph.addEdge(from, to, weight);
                        }
                    }
                }
            }


            // check for whitespace rows
            List<Integer> whitespace_rows = new ArrayList<>();
            for(int row = 0; row < height; row++) {
                Pair<Integer, Integer> left = new Pair<>(row, 0);
                Pair<Integer, Integer> right = new Pair<>(row, width - 1);
                // get the shortest path from left to right
                Map<Pair<Integer, Integer>, Long> shortest_paths = graph.getShortestPaths(left);

                // if the shortest path to the right is 0, then the row is whitespace
                if(shortest_paths.get(right) == 0) {
                    whitespace_rows.add(row);
                }
            }

            // check for whitespace cols

            List<Integer> whitespace_cols = new ArrayList<>();

            for(int col = 0; col < width; col++) {
                Pair<Integer, Integer> top = new Pair<>(0, col);
                Pair<Integer, Integer> bottom = new Pair<>(height - 1, col);
                // get the shortest path from top to bottom
                Map<Pair<Integer, Integer>, Long> shortest_paths = graph.getShortestPaths(top);

                // if the shortest path to the bottom is 0, then the column is whitespace
                if(shortest_paths.get(bottom) == 0) {
                    whitespace_cols.add(col);
                }
            }

            // return the whitespace rows and cols
            return new Pair<>(whitespace_rows, whitespace_cols);



        }
        catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            return null;
        }




    }
}
