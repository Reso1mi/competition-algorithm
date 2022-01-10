package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var reader = bufio.NewReader(os.Stdin)

func main() {
	INF := int(-0x3f3f3f3f)
	// f, _ := os.Open("./input.txt")
	// reader := bufio.NewReader(f)
	reader := bufio.NewReader(os.Stdin)
	in := ReadArray(reader)
	N, F := in[0], in[1]
	price := ReadArray(reader)

	dp := make([][]int, N+1)
	for i := 0; i <= N; i++ {
		dp[i] = make([]int, 2)
		dp[i][0] = INF
		dp[i][1] = INF
	}

	dp[0][0] = 0
	res := 0
	for i := 1; i <= N; i++ {
		dp[i][0] = Max(dp[i-1][1]+price[i-1]-F, dp[i-1][0])
		dp[i][1] = Max(dp[i-1][0]-price[i-1], dp[i-1][1])
		res = Max(res, dp[i][0])
	}
	fmt.Println(res)
}

func Max(a, b int) int {
	if a > b {
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
