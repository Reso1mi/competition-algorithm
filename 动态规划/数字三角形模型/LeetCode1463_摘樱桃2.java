import java.util.*;
class Main {
    public static void main(String[] args) {
        
    }

    //暴力N^4解法
    public int cherryPickup2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][][] dp = new int[m+1][n+2][m+1][n+2];
        int INF = -0x3f3f3f3f;
        for (int i1 = 0; i1 <= m; i1++) {
            for (int j1 = 0; j1 <= n+1; j1++) {
                for (int i2 = 0; i2 <= m; i2++) {
                    Arrays.fill(dp[i1][j1][i2], INF);
                }
            }
        }
        dp[1][1][1][n] = grid[0][0]+grid[0][n-1];
        for (int i1 = 2; i1 <= m; i1++) {
            for (int j1 = 1; j1 <= n; j1++) {
                for (int i2 = 2; i2 <= m; i2++) {
                    for (int j2 = 1; j2 <= n; j2++) {
                        int temp = INF;
                        temp = Math.max(temp, dp[i1-1][j1][i2-1][j2]);
                        temp = Math.max(temp, dp[i1-1][j1][i2-1][j2+1]);
                        temp = Math.max(temp, dp[i1-1][j1][i2-1][j2-1]);
                        
                        temp = Math.max(temp, dp[i1-1][j1+1][i2-1][j2]);
                        temp = Math.max(temp, dp[i1-1][j1+1][i2-1][j2+1]);
                        temp = Math.max(temp, dp[i1-1][j1+1][i2-1][j2-1]);   
                        
                        temp = Math.max(temp, dp[i1-1][j1-1][i2-1][j2]);
                        temp = Math.max(temp, dp[i1-1][j1-1][i2-1][j2+1]);   
                        temp = Math.max(temp, dp[i1-1][j1-1][i2-1][j2-1]);
                        dp[i1][j1][i2][j2] = temp + ((i1==i2&&j1==j2) ? grid[i1-1][j1-1] : (grid[i1-1][j1-1] + grid[i2-1][j2-1]));
                    }
                }
            }
        }
        int res = 0;
        for (int j1 = 0; j1 <= n; j1++) {
            for (int j2 = 0; j2 <= n; j2++) {
                res = Math.max(res, dp[m][j1][m][j2]);   
            }
        }
        return res;
    }

    //N^3解法，优化一维状态
    public int cherryPickup4(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        //这题求最大值，在dp数组上边和左右两边各加一行作为边界
        int[][][] dp = new int[m+1][n+2][n+2];
        int INF = -0x3f3f3f3f;
        //全部初始化为INF（实际上初始化两边和最上面的就ok了）
        for (int r = 0; r <= m; r++) {
            for (int j1 = 0; j1 <= n+1; j1++) {
                Arrays.fill(dp[r][j1], INF);
            }
        }
        dp[1][1][n] = grid[0][0] + grid[0][n-1];
        for (int r = 2; r <= m; r++) {
            for (int j1 = 1; j1 <= n; j1++) {
                for (int j2 = 1; j2 <= n; j2++) {
                    int temp = INF;
                    temp = Math.max(temp, dp[r-1][j1][j2]);
                    temp = Math.max(temp, dp[r-1][j1-1][j2+1]);
                    temp = Math.max(temp, dp[r-1][j1+1][j2-1]);

                    temp = Math.max(temp, dp[r-1][j1][j2+1]);
                    temp = Math.max(temp, dp[r-1][j1-1][j2-1]);
                    temp = Math.max(temp, dp[r-1][j1+1][j2]);
                    
                    temp = Math.max(temp, dp[r-1][j1][j2-1]);
                    temp = Math.max(temp, dp[r-1][j1-1][j2]);
                    temp = Math.max(temp, dp[r-1][j1+1][j2+1]);

                    dp[r][j1][j2] = temp + (j1==j2 ? grid[r-1][j1-1] : (grid[r-1][j1-1] + grid[r-1][j2-1]));
                }
            }
        }
        int res = 0;
        for (int j1 = 0; j1 <= n; j1++) {
            for (int j2 = 0; j2 <= n; j2++) {
                res = Math.max(res, dp[m][j1][j2]);   
            }
        }
        return res;
    }

    //N^3解法，优化代码写法，简化代码
    public int cherryPickup(int[][] grid) {
        int[] dir = {1, -1, 0};
        int m = grid.length, n = grid[0].length;
        //这题求最大值，在dp数组上边和左右两边各加一行作为边界
        //这里也可以用滚动数组优化下空间
        int[][][] dp = new int[m+1][n+2][n+2];
        int INF = -0x3f3f3f3f;
        //全部初始化为INF（实际上初始化两边和最上面的就ok了）
        for (int r = 0; r <= m; r++) {
            for (int j1 = 0; j1 <= n+1; j1++) {
                Arrays.fill(dp[r][j1], INF);
            }
        }
        dp[1][1][n] = grid[0][0] + grid[0][n-1];
        for (int r = 2; r <= m; r++) {
            for (int j1 = 1; j1 <= n; j1++) {
                for (int j2 = 1; j2 <= n; j2++) {
                    //方向向量，简化写法
                    for (int d1 = 0; d1 < 3; d1++) {
                        for (int d2 = 0; d2 < 3; d2++) {
                            dp[r][j1][j2] = Math.max(dp[r][j1][j2], dp[r-1][j1+dir[d1]][j2+dir[d2]]);
                        }
                    }
                    dp[r][j1][j2] += (j1==j2 ? grid[r-1][j1-1] : (grid[r-1][j1-1] + grid[r-1][j2-1]));
                }
            }
        }
        int res = 0;
        for (int j1 = 0; j1 <= n; j1++) {
            for (int j2 = 0; j2 <= n; j2++) {
                res = Math.max(res, dp[m][j1][j2]);   
            }
        }
        return res;
    }


    //70*70*70*70 = 2401 0000
    //这种写起来太丑了
    // public int cherryPickup(int[][] grid) {
    //     int m = grid.length, n = grid[0].length;
    //     int INF = 0x3f3f3f3f;
    //     int[][][][] dp = new int[m+1][n+1][m+1][n+1];
    //     dp[1][1][1][n] = grid[0][0]+grid[0][n-1];
    //     for (int j1 = 2; j1 <= n-1; j1++) {
    //         for (int j2 = 2; j2 <= n-1; j2++) {
    //             dp[1][j1][1][j2] = -INF;
    //         }
    //     }
    //     for (int i1 = 2; i1 <= m; i1++) {
    //         for (int j1 = 1; j1 <= n; j1++) {
    //             for (int i2 = 2; i2 <= m; i2++) {
    //                 for (int j2 = 1; j2 <= n; j2++) {
    //                     int temp = dp[i1][j1][i2][j2];
    //                     temp = Math.max(temp, dp[i1-1][j1][i2-1][j2]);
    //                     if (j2+1 <= n) {
    //                         temp = Math.max(temp, dp[i1-1][j1][i2-1][j2+1]);   
    //                     }
    //                     temp = Math.max(temp, dp[i1-1][j1][i2-1][j2-1]);
    //                     if (j1+1 <= n) {
    //                         temp = Math.max(temp, dp[i1-1][j1+1][i2-1][j2]);
    //                         if (j2+1 <= n) {
    //                             temp = Math.max(temp, dp[i1-1][j1+1][i2-1][j2+1]);                                
    //                         }
    //                         temp = Math.max(temp, dp[i1-1][j1+1][i2-1][j2-1]);   
    //                     }
    //                     temp = Math.max(temp, dp[i1-1][j1-1][i2-1][j2]);
    //                     if (j2+1 <= n) {                            
    //                         temp = Math.max(temp, dp[i1-1][j1-1][i2-1][j2+1]);   
    //                     }
    //                     temp = Math.max(temp, dp[i1-1][j1-1][i2-1][j2-1]);
    //                     dp[i1][j1][i2][j2] = temp + ((i1==i2&&j1==j2) ? grid[i1-1][j1-1] : (grid[i1-1][j1-1] + grid[i2-1][j2-1]));
    //                 }
    //             }
    //         }
    //     }
    //     int res = 0;
    //     for (int j1 = 0; j1 <= n; j1++) {
    //         for (int j2 = 0; j2 <= n; j2++) {
    //             res = Math.max(res, dp[m][j1][m][j2]);
    //         }
    //     }
    //     return res;
    // }
} 