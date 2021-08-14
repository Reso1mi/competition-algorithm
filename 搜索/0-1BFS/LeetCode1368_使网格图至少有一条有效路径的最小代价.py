from collections import deque
from typing import List

class Solution:
    def minCost(self, grid: List[List[int]]) -> int:
        # 顺序不能乱，1234右左下上
        dire = [(0, 1), (0, -1), (1, 0), (-1, 0)]
        n, m = len(grid), len(grid[0])
        dis = [[float('inf') for _ in range(m+1)] for _ in range(n+1)]
        vis = [[0 for _ in range(m+1)] for _ in range(n+1)]
        q = deque()
        q.append((0, 0))
        dis[0][0] = 0
        while q:
            x, y = q.popleft()
            if vis[x][y]:
                continue
            vis[x][y] = 1
            for i in range(4):
                nx, ny = x+dire[i][0], y+dire[i][1]
                if nx < 0 or ny < 0 or nx >= n or ny >= m:
                    continue
                w = 0 if (grid[x][y]-1) == i else 1
                if dis[nx][ny] > dis[x][y] + w:
                    dis[nx][ny] = dis[x][y] + w
                    q.append((nx, ny)) if w else q.appendleft((nx, ny))
        return dis[n-1][m-1]