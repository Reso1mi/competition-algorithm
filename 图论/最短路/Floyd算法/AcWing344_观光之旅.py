import sys

sys.stdin = open('input.txt')

INF = 0x3f3f3f3f
path = []

N, M = map(int, input().split())
# 原图
w = [[INF for _ in range(N+1)] for _ in range(N+1)]
# 最短路
dis = [[INF for _ in range(N+1)] for _ in range(N+1)]
# dp回溯
pre = [[0 for _ in range(N+1)] for _ in range(N+1)]

def dfs(i, j):
    k = pre[i][j]
    if k == 0:
        return
    dfs(i, k)
    path.append(k)
    dfs(k, j)


def get_path(i, j, k):
    path.clear()
    path.append(k)
    path.append(i)
    dfs(i, j) # k->i->j->k
    path.append(j)


def solve():
    res = INF
    for i in range(M):
        u, v, l = map(int, input().split())
        dis[v][u] = dis[u][v] = w[v][u] = w[u][v] = min(w[u][v], l)

    for k in range(1, N+1):

        # 已计算出 i, j 经过前 k-1 个点的最短路，这里再引入第k个点，查看是否k和i,j直接相连
        for i in range(1, k):
            for j in range(1, k):
                if i != j and dis[i][j] + w[j][k] + w[k][i] < res:
                    res = dis[i][j] + w[j][k] + w[k][i]
                    get_path(i, j, k)

        # floyd 记录好上一个状态
        for i in range(1, N+1):
            for j in range(1, N+1):
                if dis[i][k] + dis[k][j] < dis[i][j]:
                    dis[i][j] = dis[i][k] + dis[k][j]
                    pre[i][j] = k
                

    if res == INF:
        print("No solution.")
        return
    print(' '.join(str(r) for r in path))



if __name__ == '__main__':
    solve()