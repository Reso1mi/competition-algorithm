package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	// reader := bufio.NewReader(os.Stdin)
	f, _ := os.Open("./input.txt")
	reader := bufio.NewReader(f)
	writer := bufio.NewWriter(os.Stdout)
	ReadInt(reader)
	t := ReadLine(reader)
	ReadInt(reader)
	s := ReadLine(reader)
	for _, v := range kmp(s, t) {
		writer.WriteString(strconv.Itoa(v) + " ")
	}
	writer.Flush()
}

// len(t) = 0 会报错，这里不处理，调用者根据题目具体处理
func kmp(s string, t string) []int {
	next := make([]int, len(t))
	next[0] = -1
	var res []int
	for i, j := -1, 1; j < len(t); j++ {
		for i > -1 && t[i+1] != t[j] {
			i = next[i]
		}
		if t[i+1] == t[j] {
			i++
		}
		next[j] = i
	}
	for i, j := -1, 0; j < len(s); j++ {
		for i > -1 && t[i+1] != s[j] {
			i = next[i]
		}
		if t[i+1] == s[j] {
			i++
		}
		if i == len(t)-1 {
			res = append(res, j-len(t)+1)
			i = next[i]
		}
	}
	return res
}

func ReadLine(reader *bufio.Reader) string {
	line, _ := reader.ReadString('\n')
	return strings.TrimRight(line, "\n")
}

func ReadInt(reader *bufio.Reader) int {
	num, _ := strconv.Atoi(ReadLine(reader))
	return num
}
