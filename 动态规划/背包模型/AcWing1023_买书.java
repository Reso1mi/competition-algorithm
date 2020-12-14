/*
小明手里有n元钱全部用来买书，书的价格为10元，20元，50元，100元。

问小明有多少种买书方案？（每种书可购买多本）

输入格式
一个整数 n，代表总共钱数。

输出格式
一个整数，代表选择方案种数。

数据范围
0≤n≤1000
 */
import java.util.*;

class Main {

    static int[] price = {10, 20, 50, 100};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(solveOpt(N));
    }

    //完全背包
    public static int solve(int N) {
        //dp[i][j]: 考虑前i本书，凑齐j价值的种类数
        int[][] dp = new int[5][N+1];
        //dp[i][j] = dp[i-1][j] + dp[i-1][j-c[i]] + dp[i-1][j-2*c[i]] + ... + dp[i-1][k*c[i]]
        //dp[i][j-c[i]] = dp[i-1][j-c[i]] + dp[i-1][j-2*c[i]] + ... + dp[i-1][k*c[i]]
        //dp[i][j] = dp[i-1][j] + dp[i][j-c[i]]
        dp[0][0] = 1;
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= price[i-1]) {
                    dp[i][j] += dp[i][j-price[i-1]];
                }
            }
        }
        return dp[4][N];
    }

    //空间优化
    public static int solveOpt(int N) {
        //dp[i][j]: 考虑前i本书，凑齐j价值的种类数
        int[] dp = new int[N+1];
        dp[0] = 1;
        for (int i = 1; i <= 4; i++) {
            for (int j = price[i-1]; j <= N; j++) {
                dp[j] += dp[j-price[i-1]];
            }
        }
        return dp[N];
    }
}