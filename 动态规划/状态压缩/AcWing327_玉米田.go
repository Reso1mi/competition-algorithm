package main

import (
    "bufio"
    "fmt"
    "os"
    "strconv"
    "strings"
)

func main() {
    f, _ := os.Open("./input.txt")
    reader := bufio.NewReader(f)
    // reader := bufio.NewReader(os.Stdin)
    in := ReadArr(reader)
    // 1 <= m,n <= 12
    m, n := in[0], in[1]
    w := make([]int, m+1)
    MOD := int(1e8)
    for i := 1; i <= m; i++ {
        a, b := ReadArr(reader), 0
        for i := 0; i < len(a); i++ {
            b = b*2 + a[i]
        }
        w[i] = b
    }

    // 预处理每一行的所有合法状态
    state := make([][]int, m+1)
    state[0] = append(state[0], 0)
    for i := 1; i <= m; i++ {
        for s := 0; s < (1 << n); s++ {
            // s|w[i] != w[i] 说明在0处种了玉米
            if !valid(s) || (s|w[i] != w[i]) {
                continue
            }
            state[i] = append(state[i], s)
        }
    }

    // dp[i][j]: 只摆放前i行，最后一行状态为j，有多少种方案
    dp := make([][]int, m+1)
    for i := 0; i <= m; i++ {
        dp[i] = make([]int, 1<<n)
    }

    dp[0][0] = 1
    for i := 1; i <= m; i++ {
        for _, s := range state[i] {
            for _, t := range state[i-1] {
                // 上下两行不兼容
                if s&t != 0 {
                    continue
                }
                dp[i][s] = (dp[i][s] + dp[i-1][t]) % MOD
            }
        }
    }
    res := 0
    for s := 0; s < 1<<n; s++ {
        res = (res + dp[m][s]) % MOD
    }
    fmt.Println(res)
}

func valid(s int) bool {
    return s&(s<<1) == 0
}

func ReadLine(reader *bufio.Reader) string {
    line, _ := reader.ReadString('\n')
    return strings.TrimRight(line, "\n")
}

func ReadArr(reader *bufio.Reader) []int {
    line := ReadLine(reader)
    strs := strings.Split(line, " ")
    res := make([]int, len(strs))
    for i, v := range strs {
        res[i], _ = strconv.Atoi(v)
    }
    return res
}
