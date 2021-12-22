package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

//3 2 16
func main() {
	f, _ := os.Open("./input.txt")
	reader := bufio.NewReader(f)
	t := ReadArray(reader)
	n, k := t[0], t[1]
	// 预处理所有合法状态以及转移
	state := make([][]int, 1<<n)
	cnt := make([]int, 1<<n)
	for i := 0; i < (1<<n - 1); i++ {
		if !check(i) {
			continue
		}
		for j := 0; j < (1<<n - 1); j++ {
			if !check(j) {
				continue
			}
			if i&j == 0 && check(i|j) {
				// i <-> j 状态转移都是合法的
				state[i] = append(state[i], j)
				cnt[i] = count(i)
			}
		}
	}

	// init
	dp := make([][][]int, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([][]int, k+1)
		for j := 0; j <= k; j++ {
			dp[i][j] = make([]int, 1<<10)
		}
	}

	// dp[i][k][state]: 只摆放了前i行, 摆放了k个棋子, 且最后一行状态为state，有多少种摆放方法
	dp[0][0][0] = 1
	for i := 1; i <= n; i++ {
		for j := 0; j <= k; j++ {
			for s, next := range state {
				for _, t := range next {
					// 当前行摆放数量不超过j
					if cnt[s] <= j {
						dp[i][j][s] += dp[i-1][j-cnt[s]][t]
					}
				}
			}
		}
	}
	var res = 0
	for s := 0; s < (1 << n); s++ {
		res += dp[n][k][s]
	}
	fmt.Println(res)
}

func count(i int) int {
	ui := uint(i)
	var res = 0
	for ui > 0 {
		res += int(ui & 1)
		ui >>= 1
	}
	return res
}

func check(s int) bool {
	return s&(s<<1) == 0
}

func ReadLine(reader *bufio.Reader) string {
	line, _ := reader.ReadString('\n')
	return strings.TrimRight(line, "\n")
}

func ReadInt(reader *bufio.Reader) int {
	num, _ := strconv.Atoi(ReadLine(reader))
	return num
}

func ReadArray(reader *bufio.Reader) []int {
	line := ReadLine(reader)
	strs := strings.Split(line, " ")
	nums := make([]int, len(strs))
	for i, s := range strs {
		nums[i], _ = strconv.Atoi(s)
	}
	return nums
}
