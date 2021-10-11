package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var w [][]int
var INF = int(0x3f3f3f3f)

func main() {
	// f, _ := os.Open("./input.txt")
	// reader := bufio.NewReader(f)
	reader := bufio.NewReader(os.Stdin)
	// writer := bufio.NewWriter(os.Stdout)
	t := ReadArray(reader)
	n, m := t[0], t[1]
	w = make([][]int, n+1)
	for i := 0; i <= n; i++ {
		w[i] = make([]int, n+1)
		for j := 0; j <= n; j++ {
			w[i][j] = INF
		}
	}
	for i := 0; i < m; i++ {
		t := ReadArray(reader)
		// 有重边，需要注意
		w[t[0]][t[1]] = Min(w[t[0]][t[1]], t[2])
	}
	res := Dijkstra(n)
	if res == INF {
		fmt.Println(-1)
	} else {
		fmt.Println(res)
	}
}

func Dijkstra(n int) int {
	pq := make(NodeHeap, 0)
	vis := make([]bool, n+1)
	dis := make([]int, n+1)
	for i := 0; i <= n; i++ {
		dis[i] = INF
	}
	heap.Push(&pq, &Node{
		idx: 1,
		val: 0,
	})
	dis[1] = 0
	for len(pq) > 0 {
		cur := heap.Pop(&pq).(*Node)
		// fmt.Println(cur)
		i, v := cur.idx, cur.val
		if vis[i] {
			continue
		}
		vis[i] = true
		for j := 1; j <= n; j++ {
			if w[i][j] == INF {
				continue
			}
			if w[i][j]+v < dis[j] {
				dis[j] = w[i][j] + v
				heap.Push(&pq, &Node{j, dis[j]})
			}
		}
	}
	return dis[n]
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
	for i, s := range strs {
		nums[i], _ = strconv.Atoi(s)
	}
	return nums
}
