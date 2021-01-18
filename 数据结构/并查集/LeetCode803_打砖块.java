import java.util.*;
import java.io.*;
 
class Solution {

    int[] parent;
    int[] size;

    public int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return;
        if (size[pa] > size[pb]) {
            parent[pb] = pa;
            size[pa] += size[pb]; 
        } else {
            parent[pa] = pb;
            size[pb] += size[pa];
        }
    }

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int n = grid.length, m = grid[0].length;
        int hlen = hits.length;
        //记录当前hits对应的位置有没有砖块
        int[] exist = new int[hlen];
        for (int i = 0; i < hlen; i++) {
            int x = hits[i][0], y = hits[i][1];
            exist[i] = grid[x][y]&1;
            grid[x][y] = 0;
        }
        parent = new int[m*n+1];
        size = new int[m*n+1];
        //抽象的屋顶节点
        int ROOT = m*n;
        for (int i = 0; i <= m*n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        //和屋顶有关联的先合并下
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1) {
                union(ROOT, j);
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]==1) {
                    //与左边和上边方块合并
                    if (j-1 >= 0 && grid[i][j-1] == 1) {
                        union(m*i+j, m*i+j-1);
                    }
                    if (/*i-1 >= 0 &&*/ grid[i-1][j] == 1) {
                        union(m*i+j, m*(i-1)+j);
                    }
                }
            }
        }
        int[] res = new int[hlen];
        int[] dir = {1, 0, -1, 0, 1};
        for (int i = hlen-1; i >= 0; i--) {
            int last = size[find(ROOT)];
            int x = hits[i][0], y = hits[i][1];
            //WA_2: 还原错误，hit原本可能没有砖块，一开始直接还原成1了
            grid[x][y] = exist[i]&1;
            if (grid[x][y] == 0) continue;
            //WA_1: 还原后和ROOT连接的别忘了合并
            if (x == 0) union(ROOT, x*m+y);
            //与周围4个方向的方块合并
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k], ny = y + dir[k+1];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || grid[nx][ny] != 1) {
                    continue;
                }
                union(x*m+y, nx*m+ny);
            }
            res[i] = Math.max(0, size[find(ROOT)]-last-1);
        }
        return res;
    }
}