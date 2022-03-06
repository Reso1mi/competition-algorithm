package main

import (
	"bufio"
	"container/list"
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
	in := ReadArray(reader)
	T, R, P, S := in[0], in[1], in[2], in[3]
	h = make([]int, T+1)
	e = make([]int, (R+P)*2)
	ne = make([]int, (R+P)*2)
	w = make([]int, (R+P)*2)

	for i := 0; i < len(h); i++ {
		h[i] = -1
	}

	for i := 0; i < R; i++ {
		in := ReadArray(reader)
		Add(in[0], in[1], in[2])
		Add(in[1], in[0], in[2])
	}

	for i := 0; i < P; i++ {
		in := ReadArray(reader)
		Add(in[0], in[1], in[2])
	}

	vis := make([]bool, T+1)
	dis := make([]int, T+1)
	for i := 0; i < len(dis); i++ {
		dis[i] = INF
	}

	queue := list.New()
	queue.PushBack(S)
	vis[S] = true
	dis[S] = 0

	for queue.Len() > 0 {
		i := queue.Front().Value.(int)
		queue.Remove(queue.Front())
		vis[i] = false
		for j := h[i]; j != -1; j = ne[j] {
			if dis[e[j]] > dis[i]+w[j] {
				dis[e[j]] = dis[i] + w[j]
				if !vis[e[j]] {
					vis[e[j]] = true
					if queue.Len() > 0 && dis[queue.Front().Value.(int)] > dis[e[j]] {
						queue.PushFront(e[j])
					} else {
						queue.PushBack(e[j])
					}
				}
			}
		}
	}

	for i := 1; i <= T; i++ {
		if dis[i] == INF {
			fmt.Println("NO PATH")
		} else {
			fmt.Println(dis[i])
		}
	}

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
