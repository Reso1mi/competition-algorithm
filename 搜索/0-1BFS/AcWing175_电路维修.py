from collections import deque

T = int(input())

# 格点和斜线的方向向量
d1 = [[-1, -1], [-1, 1], [1, 1], [1, -1]]
d2 = [[-1, -1], [-1, 0], [0, 0], [0, -1]]
chs = '\\/\\/'
while T > 0:
    T = T - 1
    r, c = map(int, input().split())
    # 斜线
    w = ['' for _ in range(r)]
    # 格点
    dis = [[float('inf') for _ in range(c + 1)] for _ in range(r + 1)]
    vis = [[0 for _ in range(c + 1)] for _ in range(r + 1)]
    for i in range(r):
        w[i] = input()
    if (r + c) & 1:
        print('NO SOLUTION')
        continue
    q = deque()
    q.append((0, 0))
    dis[0][0] = 0
    while q:
        x, y = q.popleft()
        if x == r and y == c:
            break
        if vis[x][y]:
            continue
        vis[x][y] = 1
        # 遍历4个方向的格点
        for i in range(4):
            nx = x + d1[i][0]
            ny = y + d1[i][1]
            if nx < 0 or ny < 0 or nx >= r+1 or ny >= c+1:
                continue
            d = 0 if chs[i] == w[x + d2[i][0]][y + d2[i][1]] else 1
            if dis[x][y] + d < dis[nx][ny]:
                q.append((nx, ny)) if d else q.appendleft((nx, ny))
                dis[nx][ny] = dis[x][y] + d
    print(dis[r][c])