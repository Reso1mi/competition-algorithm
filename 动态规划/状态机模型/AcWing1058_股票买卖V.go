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
	n := ReadInt(reader)
	price := ReadArray(reader)
	dp := make([][2]int, n+1)
	dp[0][1] = -price[0]
	var res = 0
	for i := 1; i <= n; i++ {
		dp[i][0] = Max(dp[i-1][1]+price[i-1], dp[i-1][0])
		if i-2 >= 0 {
			dp[i][1] = Max(dp[i-2][0]-price[i-1], dp[i-1][1])
		} else {
			dp[i][1] = Max(-price[i-1], dp[i-1][1])
		}
		res = Max(res, dp[i][0])
	}
	fmt.Println(res)
}

func Max(a int, b int) int {
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
