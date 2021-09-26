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
	t := readLine(reader)
	writer.WriteString(strconv.Itoa(v) + " ")

	writer.Flush()
}

func readLine(reader *bufio.Reader) string {
	line, _ := reader.ReadString('\n')
	return strings.TrimRight(line, "\n")
}

func readInt(reader *bufio.Reader) int {
	num, _ := strconv.Atoi(readLine(reader))
	return num
}

func readArray(reader *bufio.Reader) []int {
	line := readLine(reader)
	strs := strings.Split(line, " ")
	nums := make([]int, len(strs))
	for i, s := range strs {
		nums[i], _ = strconv.Atoi(s)
	}
	return nums
}
