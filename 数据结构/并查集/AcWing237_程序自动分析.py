import sys

class DSU:
    def __init__(self, n):
        self.parent = [-1] * n
        for i in range(1, n):
            self.parent[i] = i
    

    def find(self, i):
        if self.parent[i] != i:
            self.parent[i] = self.find(self.parent[i])
        return self.parent[i]
    
    def union(self, x, y):
        rootx = self.find(x)
        rooty = self.find(y)
        self.parent[rooty] = self.parent[rootx]



if __name__ == '__main__':
    sys.stdin = open('input.txt')

    T = int(input())

    while T > 0:
        N = int(input())
        dsu = DSU(2*N+1)
        lis = []
        rank = {}
        sort_list = []
        cond = []
        
        for _ in range(N):
            # 输入离散化
            i, j, e = map(int, input().split())
            sort_list.append(i)
            sort_list.append(j)
            cond.append([i, j, e])
        sort_list.sort()

        for i in range(len(sort_list)):
            rank[sort_list[i]] = i

        for i, j, e in cond:
            if e:
                dsu.union(rank[i], rank[j])
            else:
                lis.append([rank[i], rank[j]])
        flag = False
        for i, j in lis:
            if dsu.find(i) == dsu.find(j):
                flag = True
                break
        if flag:
            print("NO")
        else:
            print("YES")
        T = T-1
