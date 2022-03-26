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
    e[idx] = b
    w[idx] = c
    ne[idx] = h[a]
    h[a] = idx
    idx++
}

func main() {
    // f, _ := os.Open("./input.txt")
    // reader := bufio.NewReader(f)
    reader := bufio.NewReader(os.Stdin)
    for {
        t, err := ReadArray(reader)
        if err != nil {
            break
        }
        n, m, s := t[0], t[1], t[2]
        idx = 0
        h = make([]int, n+1)
        for i := 0; i <= n; i++ {
            h[i] = -1
        }
        e = make([]int, m+1)
        w = make([]int, m+1)
        ne = make([]int, m+1)
        for i := 0; i < m; i++ {
            _t, _ := ReadArray(reader)
            // 建反向边
            Add(_t[1], _t[0], _t[2])
        }
        ReadArray(reader)
        a, _ := ReadArray(reader)
        fmt.Println(Dijkstra(n, s, a))
    }

}

func Dijkstra(n int, s int, a []int) int {
    pq := make(NodeHeap, 0)
    vis := make([]bool, n+1)
    dis := make([]int, n+1)
    for i := 0; i <= n; i++ {
        dis[i] = INF
    }
    heap.Push(&pq, &Node{
        idx: s,
        val: 0,
    })
    dis[s] = 0
    for len(pq) > 0 {
        cur := heap.Pop(&pq).(*Node)
        i, v := cur.idx, cur.val
        if vis[i] {
            continue
        }
        vis[i] = true
        for j := h[i]; j != -1; j = ne[j] {
            if w[j]+v < dis[e[j]] {
                dis[e[j]] = w[j] + v
                heap.Push(&pq, &Node{e[j], dis[e[j]]})
            }
        }
    }
    min := INF
    for _, v := range a {
        min = Min(min, dis[v])
    }
    if min == INF {
        return -1
    }
    return min
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

func ReadLine(reader *bufio.Reader) (string, error) {
    line, err := reader.ReadString('\n')
    return strings.TrimRight(line, "\n"), err
}

func ReadInt(reader *bufio.Reader) (int, error) {
    a, err := ReadLine(reader)
    if err != nil {
        return -1, err
    }
    num, _ := strconv.Atoi(a)
    return num, err
}

func ReadArray(reader *bufio.Reader) ([]int, error) {
    line, err := ReadLine(reader)
    if err != nil {
        return nil, err
    }
    strs := strings.Split(line, " ")
    nums := make([]int, len(strs))
    for i, s := range strs {
        nums[i], _ = strconv.Atoi(s)
    }
    return nums, err
}
