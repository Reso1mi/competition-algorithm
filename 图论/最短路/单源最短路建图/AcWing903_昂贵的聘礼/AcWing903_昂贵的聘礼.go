package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var price []int
var rank []int
var w [][]int
var INF = int(0x3f3f3f3f)

func main() {
	// f, _ := os.Open("./input.txt")
	// reader := bufio.NewReader(f)
	reader := bufio.NewReader(os.Stdin)
	// writer := bufio.NewWriter(os.Stdout)
	t := ReadArray(reader)
	M, N := t[0], t[1]
	rank = make([]int, N+1)
	w = make([][]int, N+1)
	for i := 0; i <= N; i++ {
		w[i] = make([]int, N+1)
		for j := 0; j <= N; j++ {
			w[i][j] = INF
		}
	}
	for i := 1; i <= N; i++ {
		plx := ReadArray(reader)
		rank[i] = plx[1]
		// 虚拟源点，连接所有节点
		w[0][i] = plx[0]
		for j := 0; j < plx[2]; j++ {
			tv := ReadArray(reader)
			// 反向建边
			w[tv[0]][i] = tv[1]
		}
	}
	var res = INF
	// 枚举区间 [rank[1]-m, rank[1]+m]
	for i := rank[1] - M; i <= rank[1]; i++ {
		res = Min(res, Dijkstra(N, i, i+M))
	}
	fmt.Println(res)
}

func Dijkstra(n int, lt int, rt int) int {
	pq := make(NodeHeap, 0)
	vis := make([]bool, n+1)
	dis := make([]int, n+1)
	for i := 0; i <= n; i++ {
		dis[i] = INF
	}
	heap.Push(&pq, &Node{
		idx: 0,
		val: 0,
	})
	dis[0] = 0
	for len(pq) > 0 {
		cur := heap.Pop(&pq).(*Node)
		i, v := cur.idx, cur.val
		if vis[i] {
			continue
		}
		vis[i] = true
		for j := 1; j <= n; j++ {
			if rank[j] > rt || rank[j] < lt {
				continue
			}
			if w[i][j] == INF {
				continue
			}
			if w[i][j]+v < dis[j] {
				dis[j] = w[i][j] + v
				heap.Push(&pq, &Node{j, dis[j]})
			}
		}
	}
	return dis[1]
}

func Min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

type Node struct {
	idx int
	val int
}

type NodeHeap []*Node

func (h NodeHeap) Len() int { return len(h) }

// 小顶堆
func (h NodeHeap) Less(i, j int) bool { return h[i].val < h[j].val }
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
