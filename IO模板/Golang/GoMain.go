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
