class Solution(object):
    def removeStones(self, stones: List[List[int]]) -> int:
        n = len(stones)
        parent = {}
        count = 0

        def union(a, b):
            nonlocal count
            pa = find(a)
            pb = find(b)
            if pa == pb:
                return
            count -= 1
            parent[pa] = pb

        def find(a):
            nonlocal count
            if a not in parent:
                count += 1
                parent[a] = a
                return a
            if a == parent[a]:
                return a
            parent[a] = find(parent.get(a))
            return parent.get(a)

        for s in stones:
            union(s[0]+10001, s[1])
        return n - count