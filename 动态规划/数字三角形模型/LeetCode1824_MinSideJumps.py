class Solution:
    def minSideJumps(self, obs: List[int]) -> int:
        INF = 1000000
        f = [INF] * 4
        f[2] = 0
        f[1] = f[3] = 1
        for i in range(1, len(obs)):
            f[obs[i]] = INF
            f[1] = min(f[1], f[2]+1, f[3]+1)
            f[2] = min(f[2], f[1]+1, f[3]+1)
            f[3] = min(f[3], f[1]+1, f[2]+1)
            f[obs[i]] = INF
        return min(f[1], f[2], f[3])