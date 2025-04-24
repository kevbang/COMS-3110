package cs3110.hw4.code;
/**
 * @author Kevin Tran
 * 4/20/2025
 */
import java.util.*;

public class WeightedAdjacencyList<T> implements WeightedGraph<T> {

    // The graph represented as an adjacency list.
    // For each vertex, map neighbors to edge weights
    private final Map<T, Map<T, Integer>> adjacency_list;


    /*
     * This constructor initializes the graph with the given list of vertices. This list
     * may be empty.
     */
    public WeightedAdjacencyList(List<T> vertices) {
        adjacency_list = new HashMap<>();
        for (T vertex : vertices) {
            adjacency_list.put(vertex, new HashMap<>());
        }

    }

    /**
     * Adds the directed edge (u,v) to the graph. If the edge is already present, it should not be modified.
     * @param u The source vertex.
     * @param v The target vertex.
     * @param weight The weight of the edge (u,v).
     * @return True if the edge was added to the graph, false if 1) either u or v are not in the graph 2) the edge was already present.
     */
    @Override
    public boolean addEdge(T u, T v, int weight) {
        if (!adjacency_list.containsKey(u) || !adjacency_list.containsKey(v))
            return false;

        Map<T, Integer> neighbors = adjacency_list.get(u);
        // to avoid duplicate edges, check if the edge already exists
        if(neighbors.containsKey(v))
            return false;

        // otherwise
        neighbors.put(v, weight);
        return true;
    }

    /**
     * @param vertex A vertex to add to the graph.
     * @return False vertex was already in the graph, true otherwise.
     */
    @Override
    public boolean addVertex(T vertex) {
        // if the vertex is already in the graph, return false
        if (adjacency_list.containsKey(vertex)) {
            return false;
        }

        // add the vertex to the graph
        adjacency_list.put(vertex, new HashMap<>());
        return true;
    }

    /**
     * @return |V|
     */
    @Override
    public int getVertexCount() {
        return adjacency_list.size();
    }

    /**
     * @param v The name of a vertex.
     * @return True if v is in the graph, false otherwise.
     */
    @Override
    public boolean hasVertex(T v) {
        return adjacency_list.containsKey(v);
    }

    /**
     * @return An Iterable of V.
     */
    @Override
    public Iterable<T> getVertices() {
        return adjacency_list.keySet();
    }

    /**
     * @return |E|
     */
    @Override
    public int getEdgeCount() {
        int count = 0;
        for (Map<T, Integer> neighbors : adjacency_list.values()) {
            count += neighbors.size();
        }
        return count;
    }

    /**
     * @param u The source of the edge.
     * @param v The target of the edge.
     * @return True if (u,v) is in the graph, false otherwise.
     */
    @Override
    public boolean hasEdge(T u, T v) {
        return adjacency_list.containsKey(u) && adjacency_list.get(u).containsKey(v);
    }

    /**
     * @param u A vertex.
     * @return The neighbors of u in the weighted graph.
     */
    @Override
    public Iterable<T> getNeighbors(T u) {
        return adjacency_list.get(u).keySet();
    }

    /**
     * @param u
     * @param v
     * @return
     */
    @Override
    public boolean areNeighbors(T u, T v) {
        return hasEdge(u,v);
    }

    /**
     * Uses Dijkstra's algorithm to find the (length of the) shortest path from s to all other reachable vertices in the graph.
     * If the graph contains negative edge weights, the algorithm should terminate, but the return value is undefined.
     * @param s The source vertex.
     * @return A Mapping from all reachable vertices to their distance from s. Unreachable vertices should NOT be included in the Map.
     */
    @Override
    public Map<T, Long> getShortestPaths(T s) {
        Map<T, Long> distances = new HashMap<>();
        PriorityQueue<Pair<T, Long>> priority_queue = new PriorityQueue<>(Comparator.comparingLong(Pair::getSecond));

        // initialize distances to infinity initially
        for(T vertex: adjacency_list.keySet()) {
            distances.put(vertex, Long.MAX_VALUE);
        }

        // set the distance to the source vertex to 0
        distances.put(s, 0L);
        priority_queue.add(new Pair<>(s, 0L));

        // while the priority queue is not empty
        while(!priority_queue.isEmpty()) {
            Pair<T, Long> current = priority_queue.poll();
            T u = current.getFirst();
            long dist = current.getSecond();

            if (dist > distances.get(u)) {
                continue; // Skip if we have already found a better path
            }

            // iterate through all neighbors of u
            for (Map.Entry<T, Integer> entry : adjacency_list.get(u).entrySet()) {
                T v = entry.getKey();
                int weight = entry.getValue();

                //check for a negative weight. If there is a negative weight, terminate.
                if(weight < 0) {
                    throw new IllegalArgumentException("Weight cannot be negative");
                }

                long new_dist = dist + weight;

                // if the new distance is less than the current distance, add the distance to the map with new best distance
                // and add the vertex to the priority queue
                if( new_dist < distances.get(v)) {
                    distances.put(v, new_dist);
                    priority_queue.add(new Pair<>(v, new_dist));
                }
            }

        }

        return distances;
    }
}
