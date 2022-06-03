func minimumObstacles(grid [][]int) int {

	INF := 0x3f3f3f3f
	dir := []int{1, 0, -1, 0, 1}
	M, N := len(grid), len(grid[0])
	que := list.New()
	que.PushBack([]int{0, 0})

	vis := make([][]bool, M)
	dis := make([][]int, M)
	for i := 0; i < M; i++ {
		vis[i] = make([]bool, N)
		dis[i] = make([]int, N)
		for j := 0; j < N; j++ {
			dis[i][j] = INF
		}
	}

	dis[0][0] = 0
	for que.Len() > 0 {
		t := que.Front().Value.([]int)
		que.Remove(que.Front())
		x, y := t[0], t[1]
		if x == M-1 && y == N-1 {
			break
		}
		if vis[x][y] {
			continue
		}
		vis[x][y] = true
		for i := 0; i < len(dir)-1; i++ {
			nx, ny := x+dir[i], y+dir[i+1]
			if nx < 0 || ny < 0 || nx >= M || ny >= N {
				continue
			}

			if dis[nx][ny] > dis[x][y]+grid[nx][ny] {
				dis[nx][ny] = dis[x][y] + grid[nx][ny]
				if grid[nx][ny] == 1 {
					que.PushBack([]int{nx, ny})
				} else {
					que.PushFront([]int{nx, ny})
				}
			}
		}
	}
	return dis[M-1][N-1]
}

