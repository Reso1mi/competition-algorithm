package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var MOD = int(1e9 + 7)

func main() {
	f, _ := os.Open("./input.txt")
	reader := bufio.NewReader(f)
	// reader := bufio.NewReader(os.Stdin)
	n := ReadInt(reader)
	t := ReadLine(reader)
	m := len(t)
	dp := make(map[int]map[int]int)
	for i := 0; i <= n; i++ {
		dp[i] = make(map[int]int)
	}
	// 构建next数组
	next := make([]int, m)
	next[0] = -1
	for i, j := -1, 1; j < m; j++ {
		for i > -1 && t[i+1] != t[j] {
			i = next[i]
		}
		if t[i+1] == t[j] {
			i++
		}
		next[j] = i
	}
	// 长度为0，必然不包含子串
	dp[0][-1] = 1
	for i := 0; i < n; i++ { // 密码长度
		for c := 'a'; c <= 'z'; c++ { // 密码第i-1位字符
			for j := -1; j < m-1; j++ { // 密码第i-1位在子串的状态
				u := j
				for u > -1 && byte(c) != t[u+1] {
					u = next[u]
				}
				if byte(c) == t[u+1] {
					u++
				}
				// j -> u 的状态变化，如果u < m-1 也就说明下一个字符为c状态为u时仍然不匹配
				// 故 dp[i+1][u] += dp[i][j]
				if u < m-1 {
					dp[i+1][u] = (dp[i+1][u] + dp[i][j]) % MOD
				}
			}
		}
	}
	var res = 0
	for i := -1; i < m-1; i++ {
		res = (res + dp[n][i]) % MOD
	}
	fmt.Println(res)
}

func ReadLine(reader *bufio.Reader) string {
	line, _ := reader.ReadString('\n')
	return strings.TrimRight(line, "\n")
}

func ReadInt(reader *bufio.Reader) int {
	num, _ := strconv.Atoi(ReadLine(reader))
	return num
}
