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
	reader := bufio.NewReader(os.Stdin)
	n := ReadInt(reader)
	t := "0" + ReadLine(reader)
	m := len(t) - 1
	dp := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int, m)
	}
	// 构建next数组
	next := make([]int, m+1)
	for i, j := 0, 2; j <= m; j++ {
		for i > 0 && t[i+1] != t[j] {
			i = next[i]
		}
		if t[i+1] == t[j] {
			i++
		}
		next[j] = i
	}
	dp[0][0] = 1
	for i := 0; i < n; i++ { // 密码长度
		for c := 'a'; c <= 'z'; c++ { // 密码第i-1位的字母
			for j := 0; j < m; j++ { // 密码第i-1位匹配到子串的哪一位
				u := j
				for u > 0 && byte(c) != t[u+1] {
					u = next[u]
				}
				if byte(c) == t[u+1] {
					u++
				}
				if u < m {
					dp[i+1][u] = (dp[i+1][u] + dp[i][j]) % MOD
				}
			}
		}
	}
	var res = 0
	for i := 0; i < m; i++ {
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
