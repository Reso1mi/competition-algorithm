import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int T = Integer.valueOf(br.readLine());
        while (T-- > 0) {
            int[] t = read(br);
            out.println(solve(t[0], t[1]));
        }
        out.flush();
    }


    //m个苹果放到n个盘子上，没加cache
    public static int dfs(int m, int n) {
        if (m == 0) return 1;
        if (n == 0) return 0;
        if (m < n) return dfs(m, m);
        //分情况，有空盘子，和没有空盘子
        //有空盘子说明至少有一个空盘子，等价于dfs(m, n-1)
        //没有空盘子说明每个盘子都至少有一个，等价于dfs(m-n, n)
        return dfs(m, n-1) + dfs(m-n, n);
    }

    public static int solve(int m, int n) {
        // 前i个苹果，放到j个盘子，有多少种放法
        int[][] dp = new int[m+1][n+1];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j-1];
                if (i >= j) {
                    dp[i][j] += dp[i-j][j];
                }
            }
        }
        return dp[m][n];
    }

    //完全背包解法，m+1种物品，体积为[0, m]凑齐体积m，且总使用的物品数量不操作n，求方案数量
    public static int solve2(int m, int n) {
        //前i个数，体积恰好为j，总物品数量不超过k的方案数
        int[][][] dp = new int[m+1][m+1][n+1];
        for (int i = 0; i <= n; i++) {
            dp[0][0][i] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    for (int p = 0; p <= k && j-p*i >= 0; p++) {
                        dp[i][j][k] += dp[i-1][j-p*i][k-p];
                    }
                }
            }
        }
        return dp[m][m][n];
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}