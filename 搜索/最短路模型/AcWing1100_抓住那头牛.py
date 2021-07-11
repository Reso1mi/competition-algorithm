# 农夫知道一头牛的位置，想要抓住它。

# 农夫和牛都位于数轴上，农夫起始位于点 N，牛位于点 K。

# 农夫有两种移动方式：

# 从 X 移动到 X−1 或 X+1，每次移动花费一分钟
# 从 X 移动到 2∗X，每次移动花费一分钟
# 假设牛没有意识到农夫的行动，站在原地不动。

# 农夫最少要花多少时间才能抓住牛？

from collections import deque

n, k = map(int, input().split())
MAX = int(1e5+10)

def solve(n, k):
    if n > k: return n-k
    q = deque()
    dis = [0 if i == n else -1 for i in range(MAX)]
    q.append(n)
    while q:
        top = q.popleft()
        if top == k:
            return dis[k]
        for ne in [top+1, top-1, top*2]:
            if ne < MAX and dis[ne] == -1:
                dis[ne] = dis[top]+1
                q.append(ne)
    return -1

print(solve(n, k))