package cs3110.hw4.test;

import java.util.List;
import java.util.Map;

import cs3110.hw4.code.CharacterSeparator;
import cs3110.hw4.code.WeightedAdjacencyList;
import org.junit.jupiter.api.Test;
import cs3110.hw4.code.Pair;
import static org.junit.jupiter.api.Assertions.*;


public class TestCases {

    /*
     * WeightedAdjacencyList tests
     */

    @Test
    public void testGetShortestPathsWithNegativeEdge() {

        // Create vertices
        Pair<Integer, Integer> vertex_1 = new Pair<>(0, 0);
        Pair<Integer, Integer> vertex_2 = new Pair<>(0, 1);
        Pair<Integer, Integer> vertex_3 = new Pair<>(1, 0);

        // Create a graph
        WeightedAdjacencyList<Pair<Integer, Integer>> graph = new WeightedAdjacencyList<>(List.of(vertex_1, vertex_2, vertex_3));

        assertTrue(graph.addEdge(vertex_1, vertex_2, 1));
        assertTrue(graph.addEdge(vertex_2, vertex_3, -1));

        assertThrows(IllegalArgumentException.class, () -> graph.getShortestPaths(vertex_1));

    }

    @Test
    public void testValidShortestPaths() {
        // Create vertices
        Pair<Integer, Integer> vertex_1 = new Pair<>(0, 0);
        Pair<Integer, Integer> vertex_2 = new Pair<>(0, 1);
        Pair<Integer, Integer> vertex_3 = new Pair<>(1, 0);
        Pair<Integer, Integer> vertex_4 = new Pair<>(1, 1);

        // Create a graph
        WeightedAdjacencyList<Pair<Integer, Integer>> graph = new WeightedAdjacencyList<>(List.of(vertex_1, vertex_2, vertex_3, vertex_4));
        assertTrue(graph.addEdge(vertex_1, vertex_2, 1));
        assertTrue(graph.addEdge(vertex_2, vertex_3, 2));
        assertTrue(graph.addEdge(vertex_3, vertex_4, 3));
        assertTrue(graph.addEdge(vertex_1, vertex_4, 10));
        assertTrue(graph.addEdge(vertex_2, vertex_4, 5));
    }

    @Test
    public void testFindSeparationWeighted() {
        // Path to the test BMP file
        String testImagePath = "src/cs3110/hw4/test/bmp_files/ex1-upload.bmp";

        // Expected whitespace rows and columns
        List<Integer> expectedWhitespaceRows = List.of(); // Example rows
        List<Integer> expectedWhitespaceCols = List.of();    // Example columns

        // Call the method
        Pair<List<Integer>, List<Integer>> result = CharacterSeparator.findSeparationWeighted(testImagePath);

        // Ensure the result is not null
        assertNotNull(result, "The result should not be null");

        // Extract the whitespace rows and columns
        List<Integer> actualWhitespaceRows = result.getFirst();
        List<Integer> actualWhitespaceCols = result.getSecond();

        // Verify the rows and columns match the expected values
        assertEquals(expectedWhitespaceRows, actualWhitespaceRows, "Whitespace rows do not match");
        assertEquals(expectedWhitespaceCols, actualWhitespaceCols, "Whitespace columns do not match");
    }

}
