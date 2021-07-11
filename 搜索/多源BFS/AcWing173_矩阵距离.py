from collections import deque

n, m = map(int, input().split())
w = [list(map(int, list(input()))) for _ in range(n)]
dis = [[-1 for _ in range(m)] for _ in range(n)]

q = deque()
for i in range(n):
    for j in range(m):
        if w[i][j] == 1:
            q.append((i, j))
            dis[i][j] = 0
while q:
    x, y = q.popleft()
    for (nx, ny) in [(x + 1, y), (x, y + 1), (x - 1, y), (x, y - 1)]:
        if nx < 0 or ny < 0 or nx >= n or ny >= m or dis[nx][ny] != -1:
            continue
        dis[nx][ny] = dis[x][y] + 1
        q.append((nx, ny))

[print(' '.join([str(x) for x in r])) for r in dis]


# n, m = map(int, input().split())
# w = [list(map(int, list(input()))) for _ in range(n)]
# dis = [[-1 for _ in range(m)] for _ in range(n)]

# q = []
# for i in range(n):
#     for j in range(m):
#         if w[i][j] == 1:
#             q.append((i, j))
#             dis[i][j] = 0
# while q:
#     nq = []
#     for x, y in q:
#         for (nx, ny) in [(x + 1, y), (x, y + 1), (x - 1, y), (x, y - 1)]:
#             if nx < 0 or ny < 0 or nx >= n or ny >= m or dis[nx][ny] != -1:
#                 continue
#             dis[nx][ny] = dis[x][y] + 1
#             nq.append((nx, ny))
#     q = nq

# [print(' '.join([str(x) for x in r])) for r in dis]
