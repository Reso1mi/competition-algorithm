T = int(input())

while T > 0:
    n = int(input())
    nums = list(map(int, input().split()))
    f = [0] * 2
    for i in range(1, n+1):
        f[0], f[1] = max(f[1], f[0]), f[0] + nums[i-1]
    print(max(f[1], f[0]))
    T -= 1