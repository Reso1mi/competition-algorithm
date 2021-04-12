class Solution:
    def minNumberOperations(self, target: List[int]) -> int:
        res = target[0]
        for i in range(1, len(target)):
            if (d := target[i]-target[i-1]) > 0:
                res += d
        return res