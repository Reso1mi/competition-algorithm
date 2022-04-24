package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

var INF = int(0x3f3f3f3f)
var MOD = 100003
var idx int
var h []int
var e []int
var ne []int

func add(a, b int) {
	e[idx] = b
	ne[idx] = h[a]
	h[a] = idx
	idx++
}

func main() {
	// f, _ := os.Open("./input.txt")
	// reader := bufio.NewReader(f)
	var reader = bufio.NewReader(os.Stdin)
	var writer = bufio.NewWriter(os.Stdout)

	in := ReadArray(reader)
	N, M := in[0], in[1]
	idx = 0
	h = make([]int, N+1)
	for i := 0; i <= N; i++ {
		h[i] = -1
	}
	e = make([]int, 2*M+1)
	ne = make([]int, 2*M+1)
	for i := 0; i < M; i++ {
		t := ReadArray(reader)
		add(t[0], t[1])
		add(t[1], t[0])
	}

	var queue []int
	dis := make([]int, N+1)
	for i := 0; i <= N; i++ {
		dis[i] = INF
	}
	dis[1] = 1
	// dp[i]: 从1到i的最短路条数
	dp := make([]int, N+1)
	queue = append(queue, 1)
	dp[1] = 1
	for len(queue) > 0 {
		i := queue[0]
		queue = queue[1:]

		for j := h[i]; j != -1; j = ne[j] {
			k := e[j]
			if dis[i]+1 == dis[k] { // 累加
				dp[k] = (dp[k] + dp[i]) % MOD
			} else if dis[i]+1 < dis[k] { // 有更小的，覆盖掉
				dp[k] = dp[i]
				dis[k] = dis[i] + 1
				queue = append(queue, k) // 只入队一次，最短路确定
			}
		}
	}

	for i := 1; i <= N; i++ {
		writer.WriteString(strconv.Itoa(dp[i]))
		writer.WriteString("\n")
	}
	writer.Flush()
}

func Min(a, b int) int {
	if a < b {
		return a
	}
	return b
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
	for i, v := range strs {
		nums[i], _ = strconv.Atoi(v)
	}
	return nums
}
