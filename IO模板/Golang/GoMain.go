package main

import (
	"bufio"
	. "fmt"
	"os"
)

func main() {
	_r := bufio.NewReader(os.Stdin)
	_w := bufio.NewWriter(os.Stdout)
	var n int
	Fscan(_r, &n)
	Fprintln(_w, n)
	_w.Flush()
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

func init() {
	os.Stdin, _ = os.Open("./input.txt")
}
