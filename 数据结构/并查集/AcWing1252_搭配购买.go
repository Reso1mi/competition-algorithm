package main

import (
	"bufio"
	. "fmt"
	"os"
)

var parent []int
var cost []int
var worth []int

func union(a, b int) bool {
	pa := find(a)
	pb := find(b)
	if pa == pb {
		return true
	}
	parent[pa] = pb
	// 将价值和价格加到父节点上
	cost[pb] += cost[pa]
	worth[pb] += worth[pa]
	return false
}

func find(a int) int {
	if parent[a] == a {
		return a
	}
	return find(parent[a])
}

func main() {
	var Max = func(a, b int) int {
		if a > b {
			return a
		}
		return b
	}
	r := bufio.NewReader(os.Stdin)
	w := bufio.NewWriter(os.Stdout)

	var N, M, W int
	Fscan(r, &N, &M, &W)
	C := make([]int, N+1)
	D := make([]int, N+1)
	for i := 1; i <= N; i++ {
		Fscan(r, &C[i], &D[i])
	}

	parent = make([]int, N+1)
	cost = make([]int, N+1)
	worth = make([]int, N+1)
	for i, _ := range parent {
		parent[i] = i
		cost[i] = C[i]
		worth[i] = D[i]
	}
	for i := 0; i < M; i++ {
		var u, v int
		Fscan(r, &u, &v)
		union(u, v)
	}

	var cloud []int
	for i := 1; i <= N; i++ {
		if parent[i] == i {
			cloud = append(cloud, i)
		}
	}

	// 变成一个背包问题，容积W，物品体积cost，价值worth
	dp := make([]int, W+1)
	// dp[i][j]: 前i个物品，花费不超过j，最大价值
	for _, i := range cloud {
		for j := W; j >= cost[i]; j-- {
			dp[j] = Max(dp[j], dp[j-cost[i]]+worth[i])
		}
	}

	Fprintln(w, dp[W])
	w.Flush()
}

func init() {
	os.Stdin, _ = os.Open("./input.txt")
}
