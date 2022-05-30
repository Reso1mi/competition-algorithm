package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var parent []int

func union(a, b int) bool {
	pa := find(a)
	pb := find(b)
	if pa == pb {
		return true
	}
	parent[pa] = pb
	return false
}

func find(a int) int {
	if parent[a] == a {
		return a
	}
	return find(parent[a])
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	t := ReadArray(reader)
	N, M := t[0], t[1]
	parent = make([]int, N*N+1)
	for i, _ := range parent {
		parent[i] = i
	}

	res := -1
	for i := 1; i <= M; i++ {
		t := ReadStrs(reader)
		x, _ := strconv.Atoi(t[0])
		y, _ := strconv.Atoi(t[1])
		// fmt.Println(x, y, t[2])
		if t[2] == "R" {
			if union((x-1)*N+y, (x-1)*N+y+1) {
				res = i
				break
			}
		} else {
			if union((x-1)*N+y, (x-1)*N+y+N) {
				res = i
				break
			}
		}
	}
	if res == -1 {
		fmt.Println("draw")
	} else {
		fmt.Println(res)
	}
}

func ReadLine(reader *bufio.Reader) string {
	line, _ := reader.ReadString('\n')
	return strings.TrimRight(line, "\n")
}

func ReadArray(reader *bufio.Reader) []int {
	line := ReadLine(reader)
	strs := strings.Split(line, " ")
	nums := make([]int, len(line))
	for i, v := range strs {
		nums[i], _ = strconv.Atoi(v)
	}
	return nums
}

func ReadStrs(reader *bufio.Reader) []string {
	line := ReadLine(reader)
	strs := strings.Split(line, " ")
	return strs
}

func init() {
	os.Stdin, _ = os.Open("./input.txt")
}
