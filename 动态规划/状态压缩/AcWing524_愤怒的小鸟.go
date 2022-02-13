package main

import (
    "bufio"
    "fmt"
    "math"
    "os"
    "strconv"
    "strings"
)

type fat = float64

var EPS = 1e-6

var INF = 0x3f3f3f3f

var w [][]fat

var n int

var path [][]int

var dp map[int]int

func main() {
    // f, _ := os.Open("./input.txt")
    // reader := bufio.NewReader(f)
    reader := bufio.NewReader(os.Stdin)
    T := ReadArr(reader)[0]
    for T > 0 {
        // 不需要m参数（进阶）
        n = ReadArr(reader)[0]
        w = make([][]fat, n)
        dp = make(map[int]int)
        for i := 0; i < n; i++ {
            w[i] = ReadFArr(reader)
        }
        initPath()
        // fmt.Println("path", path)
        fmt.Println(solve((1 << n) - 1))
        T--
    }
}

// y = ax^2 + bx
func initPath() {
    // path[i][j] = mask, 经过第i和第j个点，抛物线经过的点集mask
    path = make([][]int, n)
    for i := 0; i < n; i++ {
        path[i] = make([]int, n)
    }

    for i := 0; i < n; i++ { // 枚举两点，确定抛物线
        path[i][i] = 1 << i
        for j := 0; j < n; j++ {
            // 求a,b
            x1, y1 := w[i][0], w[i][1]
            x2, y2 := w[j][0], w[j][1]
            if Eq(x1, x2) {
                continue
            }
            a := (y1/x1 - y2/x2) / (x1 - x2)
            if a >= 0 {
                continue
            }
            b := (y1 / x1) - a*x1
            for k := 0; k < n; k++ { // 枚举穿过的点
                x, y := w[k][0], w[k][1]
                if !Eq(x*x*a+b*x, y) {
                    // 不经过x, y
                    continue
                }
                path[i][j] |= 1 << k
            }
        }
    }
}

// 穿过s需要的最小的抛物线数量
// 11 path[i][j]=10
func solve(s int) int {
    if v, ok := dp[s]; ok {
        return v
    }
    if s == 0 {
        return 0
    }

    res := INF
    // 第x个小鸟没有被打下来
    x := -1
    for i := 0; i < n; i++ {
        if (s>>i)&1 == 1 {
            x = i
            break
        }
    }

    for i := 0; i < n; i++ {
        if path[x][i] != 0 {
            res = Min(res, solve((s^path[x][i])&s)+1)
        }
    }

    dp[s] = res
    return res
}

func Min(a, b int) int {
    if a < b {
        return a
    }
    return b
}

func Eq(a, b fat) bool {
    if math.Abs(a-b) < EPS {
        return true
    }
    return false
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

func ReadFArr(reader *bufio.Reader) []fat {
    line := ReadLine(reader)
    strs := strings.Split(line, " ")
    res := make([]fat, len(strs))
    for i, v := range strs {
        res[i], _ = strconv.ParseFloat(v, 64)
    }
    return res
}
