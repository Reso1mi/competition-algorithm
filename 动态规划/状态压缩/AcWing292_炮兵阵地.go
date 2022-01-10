package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	// f, _ := os.Open("./input.txt")
	// reader := bufio.NewReader(f)
	reader := bufio.NewReader(os.Stdin)
	in := ReadArray(reader)
	M, N := in[0], in[1]
	w := make([]int, M+1)
	for i := 1; i <= M; i++ {
		t := ReadStrs(reader)
		v := 0
		for j := 0; j < N; j++ {
			if t[j] == "H" {
				v *= 2
			} else {
				v = v*2 + 1
			}
			w[i] = v
		}
	}

	// state[i] 第i行的合法状态
	state := make([][]int, M+1)
	state[0] = append(state[0], 0)
	for i := 1; i <= M; i++ {
		for s := 0; s < 1<<N; s++ {
			if valid(s) && (s|w[i] == w[i]) {
				state[i] = append(state[i], s)
			}
		}
	}

	// dp[i][pre][cur]: 摆放前i行，第i行状态为cur，第i-1行为pre，能摆放多少个炮台
	dp := make([][][]int, 2)
	for i := 0; i < 2; i++ {
		dp[i] = make([][]int, 1<<N)
		for j := 0; j < 1<<N; j++ {
			dp[i][j] = make([]int, 1<<N)
		}
	}

	for _, s := range state[1] {
		dp[1][0][s] = Count(s)
	}

	for i := 2; i <= M; i++ {
		// 当前行状态
		for _, s := range state[i] {
			// 上一行状态
			for _, t := range state[i-1] {
				for _, p := range state[i-2] {
					if s&p != 0 || p&t != 0 || s&t != 0 {
						continue
					}
					dp[i&1][t][s] = Max(dp[i&1][t][s], dp[(i-1)&1][p][t]+Count(s))
				}
			}
		}
	}

	res := 0
	for p := 0; p < 1<<N; p++ {
		for s := 0; s < 1<<N; s++ {
			res = Max(dp[M&1][s][p], res)
		}
	}
	fmt.Println(res)
}

func valid(s int) bool {
	return s<<1&s == 0 && s<<2&s == 0
}

func Count(s int) int {
	ss := uint(s)
	res := 0
	for ss > 0 {
		res += int(ss & 1)
		ss >>= 1
	}
	return res
}

func Max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func ReadLine(reader *bufio.Reader) string {
	line, _ := reader.ReadString('\n')
	return strings.TrimRight(line, "\n")
}

func ReadStrs(reader *bufio.Reader) []string {
	line := ReadLine(reader)
	return strings.Split(line, "")
}

func ReadArray(reader *bufio.Reader) []int {
	line := ReadLine(reader)
	strs := strings.Split(line, " ")
	res := make([]int, len(strs))
	for i, v := range strs {
		res[i], _ = strconv.Atoi(v)
	}
	return res
}
