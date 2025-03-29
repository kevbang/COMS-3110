from collections import deque

def wildfire_burn_time(W):
    n = len(W)
    queue = deque()
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]  # Right, Down, Left, Up
    trees = 0  # Count of trees that need to burn
    
    # Step 1: Add all burning trees (1) to the queue and count trees (0)
    for i in range(n):
        for j in range(n):
            if W[i][j] == 1:
                queue.append((i, j, 0))  # (row, col, time)
            elif W[i][j] == 0:
                trees += 1
    
    # If there are no trees to burn, return 0
    if trees == 0:
        return 0
    
    # If there are no fires to start with, return -1
    if not queue:
        return -1
    
    max_time = 0
    
    # Step 2: Perform BFS
    while queue:
        x, y, time = queue.popleft()
        max_time = max(max_time, time)
        
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            if 0 <= nx < n and 0 <= ny < n and W[nx][ny] == 0:
                W[nx][ny] = 1  # Mark the tree as burning
                queue.append((nx, ny, time + 1))
                trees -= 1  # Decrease the count of unburned trees
                
                # Early exit: If all trees are burned, return the time
                if trees == 0:
                    return time + 1
    
    # Step 3: If there are still unburned trees, return -1
    return -1





if __name__ == "__main__":
    forest = [
    [0, 0, 0],
    [0, 2, 0],
    [2, 1, 2]
]
    
print(wildfire_burn_time(forest))