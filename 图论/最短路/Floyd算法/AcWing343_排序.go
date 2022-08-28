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

	for {
		var n, m int
		Fscan(_r, &n, &m)

		if n == 0 && m == 0 {
			break
		}

		// 节点入度
		indeg := make([]int, n+1)
		// w[i][j]: i < j
		w := make([][]bool, n+1)
		ww := make([][]bool, n+1)
		for i := 0; i <= n; i++ {
			w[i] = make([]bool, n+1)
			ww[i] = make([]bool, n+1)
		}

		// floyd传递闭包
		floyd := func() {
			for k := 0; k < n; k++ {
				for i := 0; i < n; i++ {
					for j := 0; j < n; j++ {
						if ww[i][k] && ww[k][j] {
							ww[i][j] = true
						}
					}
				}
			}
		}

		// 检查状态
		check := func() int {
			for i := 0; i < n; i++ {
				// 有环
				if ww[i][i] {
					return -1
				}
			}

			for i := 0; i < n; i++ {
				for j := i + 1; j < n; j++ {
					// i,j 没有明确的关系
					if !ww[i][j] && !ww[j][i] {
						return 0
					}
				}
			}
			return 1
		}

		// 拓扑排序
		topSort := func(t int) {
			var queue []int
			for i := 0; i < n; i++ {
				if indeg[i] == 0 {
					queue = append(queue, i)
				}
			}
			var res string
			for len(queue) != 0 {
				cur := queue[0]
				queue = queue[1:]
				res += string(cur + int('A'))
				for i := 0; i < n; i++ {
					if w[cur][i] {
						indeg[i]--
						if indeg[i] == 0 {
							queue = append(queue, i)
						}
					}
				}
			}
			Fprintf(_w, "Sorted sequence determined after %d relations: %s.\n", t, res)
		}

		var res int
		for i := 0; i < m; i++ {
			var t string
			Fscan(_r, &t)
			if res == 1 || res == -1 {
				continue
			}
			a := t[0] - byte('A')
			b := t[2] - byte('A')
			ww[a][b], w[a][b] = true, true
			indeg[b]++

			floyd()
			// 关系检测
			res = check()
			if res == -1 {
				Fprintf(_w, "Inconsistency found after %d relations.\n", i+1)
				// break
			} else if res == 1 {
				topSort(i + 1)
				// 还没读完，不能直接 break，真恶心
				// break
			}
		}

		if res == 0 {
			Fprintln(_w, "Sorted sequence cannot be determined.")
		}
	}
}

func init() {
	os.Stdin, _ = os.Open("./input.txt")
}
