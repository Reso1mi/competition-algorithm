package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	f, _ := os.Open("./input.txt")
	reader := bufio.NewReader(f)
	// reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	t := ReadLine(reader)
	// n, _ := strconv.Atoi(t)
	writer.WriteString("Resolmi")
	writer.WriteString(t)

	writer.Flush()
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

func readArray(reader *bufio.Reader) []int {
	line := ReadLine(reader)
	strs := strings.Split(line, " ")
	nums := make([]int, len(strs))
	for i, s := range strs {
		nums[i], _ = strconv.Atoi(s)
	}
	return nums
}
