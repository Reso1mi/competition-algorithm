package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var INF = int(0x3f3f3f3f)
var h []int
var e []int
var ne []int
var w []int
var idx int

func Add(a, b, c int) {
	w[idx] = c
	e[idx] = b
	ne[idx] = h[a]
	h[a] = idx
	idx++
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	T := ReadArray(reader)[0]
	for ; T > 0; T-- {
		in := ReadArray(reader)
		N, M := in[0], in[1]
		idx = 0
		h = make([]int, N+1)
		e = make([]int, M+1)
		ne = make([]int, M+1)
		w = make([]int, M+1)

		for i := 0; i < len(h); i++ {
			h[i] = -1
		}

		for i := 0; i < M; i++ {
			t := ReadArray(reader)
			Add(t[0], t[1], t[2])
		}

		sf := ReadArray(reader)
		S, F := sf[0], sf[1]

		fmt.Println(Dijkstra(S, F, N))
	}
}

func Dijkstra(s, f, n int) int {
	var pq NodeHeap
	dis := make([][2]int, n+1)
	cnt := make([][2]int, n+1)
	vis := make([][2]bool, n+1)
	heap.Push(&pq, &Node{s, 0, 0})
	for i := 0; i <= n; i++ {
		dis[i][0] = INF
		dis[i][1] = INF
	}
	cnt[s][0] = 1
	dis[s][0] = 0

	for len(pq) > 0 {
		cur := heap.Pop(&pq).(*Node)
		i, v, t := cur.i, cur.v, cur.t
		if vis[i][t] {
			continue
		}
		vis[i][t] = true
		// i 出边
		for j := h[i]; j != -1; j = ne[j] {
			if dis[e[j]][0] > w[j]+v { // 最短路变为次短路
				dis[e[j]][1] = dis[e[j]][0]
				cnt[e[j]][1] = cnt[e[j]][0] // 历史最短路覆盖次短路cnt
				dis[e[j]][0] = w[j] + v
				cnt[e[j]][0] = cnt[i][t] // 新的最短路覆盖历史最短路cnt
				heap.Push(&pq, &Node{e[j], dis[e[j]][0], 0})
				heap.Push(&pq, &Node{e[j], dis[e[j]][1], 1})
			} else if dis[e[j]][0] == w[j]+v { // 找到一条新的最短路
				cnt[e[j]][0] += cnt[i][t]
			} else if dis[e[j]][1] > w[j]+v { // 找到更短的次短路
				dis[e[j]][1] = w[j] + v
				cnt[e[j]][1] = cnt[i][t] // 新次短路覆盖次最短路cnt
				heap.Push(&pq, &Node{e[j], dis[e[j]][1], 1})
			} else if dis[e[j]][1] == w[j]+v {
				cnt[e[j]][1] += cnt[i][t]
			}
		}
	}

	res := cnt[f][0]
	if dis[f][1] == dis[f][0]+1 {
		res += cnt[f][1]
	}
	return res
}

type Node struct {
	i int
	v int
	t int
}

type NodeHeap []*Node

func (h NodeHeap) Len() int { return len(h) }

// 小顶堆
func (h NodeHeap) Less(i, j int) bool { return h[i].v < h[j].v }
func (h NodeHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *NodeHeap) Push(x interface{}) {
	// Push 和 Pop 使用 pointer receiver 作为参数，
	// 因为它们不仅会对切片的内容进行调整，还会修改切片的长度。
	*h = append(*h, x.(*Node))
}

func (h *NodeHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func ReadLine(reader *bufio.Reader) string {
	line, _ := reader.ReadString('\n')
	return strings.TrimRight(line, "\n")
}

func ReadArray(reader *bufio.Reader) []int {
	line := ReadLine(reader)
	strs := strings.Split(line, " ")
	nums := make([]int, len(strs))
	for i := 0; i < len(strs); i++ {
		nums[i], _ = strconv.Atoi(strs[i])
	}
	return nums
}

func init() {
	os.Stdin, _ = os.Open("./input.txt")
}
