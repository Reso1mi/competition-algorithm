/*
辰辰是个天资聪颖的孩子，他的梦想是成为世界上最伟大的医师。

为此，他想拜附近最有威望的医师为师。

医师为了判断他的资质，给他出了一个难题。

医师把他带到一个到处都是草药的山洞里对他说：“孩子，这个山洞里有一些不同的草药，采每一株都需要一些时间，每一株也有它自身的价值。我会给你一段时间，在这段时间里，你可以采到一些草药。如果你是一个聪明的孩子，你应该可以让采到的草药的总价值最大。”

如果你是辰辰，你能完成这个任务吗？

输入格式
输入文件的第一行有两个整数T和M，用一个空格隔开，T代表总共能够用来采药的时间，M代表山洞里的草药的数目。

接下来的M行每行包括两个在1到100之间（包括1和100）的整数，分别表示采摘某株草药的时间和这株草药的价值。

输出格式
输出文件包括一行，这一行只包含一个整数，表示在规定的时间内，可以采到的草药的最大总价值。

数据范围
1≤T≤1000,
1≤M≤100
输入样例：
70 3
71 100
69 1
1 2
输出样例：
3*/

import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int M = sc.nextInt();
        int[] v = new int[M];
        int[] cost = new int[M];
        for (int i = 0; i < M; i++) {
            //可以边读边计算，为了让输入和处理逻辑解耦，算了
            cost[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        System.out.println(solveOpt(T, M, v, cost));
    }

    //裸01背包
    public static int solve(int T, int M, int[] v, int[] cost) {
        //考虑前i件药，背包大小为j，能装的最大收益
        int[][] dp = new int[M+1][T+1];
        //dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]] + v[i])
        for (int i = 1; i <= M; i++) {
            for (int j = T; j >= 0; j--) {
                dp[i][j] = Math.max(dp[i-1][j], j >= cost[i-1] ? dp[i-1][j-cost[i-1]] + v[i-1] : -1);
            }
        }
        return dp[M][T];
    }

    //空间优化
    public static int solveOpt(int T, int M, int[] v, int[] cost) {
        //考虑前i件药，背包大小为j，能装的最大收益
        //dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]] + v[i])
        int[] dp = new int[T+1];
        for (int i = 0; i < M; i++) {
            for (int j = T; j >= cost[i]; j--) {
                //j > cost[i] dp[j] = dp[j](old)
                dp[j] = Math.max(dp[j], dp[j-cost[i]] + v[i]);
            }
        }
        return dp[T];
    }
}