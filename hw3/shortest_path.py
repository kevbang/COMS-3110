from collections import defaultdict, deque

def bfs_shortest_path(graph, start, end):
    """Performs BFS to find the shortest path distance between start and end."""
    queue = deque([(start, 0)])  # (current_node, distance)
    visited = set()
    while queue:
        current, dist = queue.popleft()
        if current == end:
            return dist
        if current not in visited:
            visited.add(current)
            for neighbor in graph[current]:
                if neighbor not in visited:
                    queue.append((neighbor, dist + 1))
    return float('inf')  # If no path exists

def is_w_in_shortest_path(graph, u, v, w):
    """
    Returns True if vertex w is in some shortest path from u to v.
    """
    # Find the shortest path distance from u to v
    dist_u_to_v = bfs_shortest_path(graph, u, v)
    
    # Find the shortest path distance from u to w and w to v
    dist_u_to_w = bfs_shortest_path(graph, u, w)
    dist_w_to_v = bfs_shortest_path(graph, w, v)
    
    # Check if w lies on a shortest path from u to v
    return dist_u_to_w + dist_w_to_v == dist_u_to_v


if __name__ == "__main__":
    # Define the graph as an adjacency list
    graph = defaultdict(list)
    graph[1] = [2, 3]
    graph[2] = [1, 4]
    graph[3] = [1, 4]
    graph[4] = [2, 3, 5]
    graph[5] = [4]

    u, v, w = 1, 5, 4
    print(is_w_in_shortest_path(graph, u, v, w))  # Output: True