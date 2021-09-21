T = int(input())

while T > 0:
    n = int(input())
    nums = list(map(int, input().split()))
    f = [0] * (n+1)
    for i in range(1, n+1):
        f[i] = max(f[i-1], (f[i-2] + nums[i-1]) if i-2 >= 0 else nums[i-1])
    print(f[i])
    T -= 1