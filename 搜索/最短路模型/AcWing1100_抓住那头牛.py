# 农夫知道一头牛的位置，想要抓住它。

# 农夫和牛都位于数轴上，农夫起始位于点 N，牛位于点 K。

# 农夫有两种移动方式：

# 从 X 移动到 X−1 或 X+1，每次移动花费一分钟
# 从 X 移动到 2∗X，每次移动花费一分钟
# 假设牛没有意识到农夫的行动，站在原地不动。

# 农夫最少要花多少时间才能抓住牛？
import sys
import queue

line = sys.stdin.readline().split(" ")
# line = [5, 7]
def solve(n, k):
    if n > k: return n-k
    MAX = int(1e5+10)
    q = queue.Queue()
    dis = [-1 for _ in range(MAX)]
    dis[n] = 0
    q.put(n)
    while not q.empty():
        top = q.get()
        if top == k:
            return dis[k]
        for ne in [top+1, top-1, top*2]:
            if ne < MAX and dis[ne] == -1:
                dis[ne] = dis[top]+1
                q.put(ne)
    return -1

if __name__ == '__main__':
    print(solve(int(line[0]), int(line[1])))