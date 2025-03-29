from collections import deque

def time_to_burn(W: list) -> int:
    directions = [(0, 1), (0, -1), (-1, 0), (1, 0)] # right, left, above, below
    queue = deque()
    n = len(W)
    burning_trees = 0
    burnable_trees = 0
    minutes = 0

    # iterate through forest
    for i in range(n):
        for j in range(n):
            if W[i][j] == 1:
                queue.append((i, j, 0))  # Add the location of "1"s as tuples
                print(queue)  # Print the queue
                burning_trees += 1
            elif W[i][j] == 0:
                burnable_trees += 1
    
    if not burnable_trees:
        return -1
    

    while(queue):
        y, x, time = queue.popleft()
        minutes = max(minutes, time)
        for direction_y, direction_x in directions:
            neighbor_x = x + direction_x
            neighbor_y = y + direction_y
            if (neighbor_x >= 0 and neighbor_x < n) and (neighbor_y >= 0 and neighbor_y < n) and W[neighbor_y][neighbor_x] == 0:
                W[neighbor_y][neighbor_x] = 1
                queue.append((neighbor_y, neighbor_x, time + 1))
                burnable_trees -=1
    
    return minutes





if __name__ == "__main__":

    forest = [
        [0, 1, 0],
        [1, 0, 1],
        [0, 1, 0]
    ]

    # forest = [
    #     [1, 2, 2, 1],
    #     [2, 2, 1, 2],
    #     [2, 2, 2, 1],
    #     [1, 1, 2, 2]
    # ]

    print(time_to_burn(forest))