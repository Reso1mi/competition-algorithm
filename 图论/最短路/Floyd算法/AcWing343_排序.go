package main

import (
	"bufio"
	. "fmt"
	"os"
)

func main() {
	_r := bufio.NewReader(os.Stdin)
	_w := bufio.NewWriter(os.Stdout)
	defer _w.Flush()

}

func init() {
	os.Stdin, _ = os.Open("./input.txt")
}
