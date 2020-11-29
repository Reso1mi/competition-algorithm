import java.util.*;
import java.io.*;
/*
熊大妈的奶牛在小沐沐的熏陶下开始研究信息题目。

小沐沐先让奶牛研究了最长上升子序列，再让他们研究了最长公共子序列，现在又让他们研究最长公共上升子序列了。

小沐沐说，对于两个数列A和B，如果它们都包含一段位置不一定连续的数，且数值是严格递增的，那么称这一段数是两个数列的公共上升子序列，而所有的公共上升子序列中最长的就是最长公共上升子序列了。

奶牛半懂不懂，小沐沐要你来告诉奶牛什么是最长公共上升子序列。

不过，只要告诉奶牛它的长度就可以了。

数列A和B的长度均不超过3000。

输入格式
第一行包含一个整数N，表示数列A，B的长度。

第二行包含N个整数，表示数列A。

第三行包含N个整数，表示数列B。

输出格式
输出一个整数，表示最长公共上升子序列的长度。

数据范围
1≤N≤3000,序列中的数字均不超过2^31−1

4
2 2 1 3
2 1 2 3

2
 */
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        int[] B = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            B[i] = sc.nextInt();
        }
        System.out.println(solveOpt(A, B, N));
    }

    public static int solve(int[] A, int[] B, int N){
        //A前i个字符和B前j个字符并且以B[j]结尾的最长LCIS
        int[][] dp = new int[N+1][N+1];
        int res = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i-1][j];
                if (A[i-1] == B[j-1]) {
                    dp[i][j] = Math.max(dp[i][j], 1);
                    for (int k = 1; k < j; k++) {
                        if (B[j-1] > B[k-1]) {
                            dp[i][j] = Math.max(dp[i][k]+1, dp[i][j]);
                        }
                    }
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    //代码等价变形，时间复杂度N^2
    public static int solveOpt(int[] A, int[] B, int N){
        //A前i个字符和B前j个字符并且以B[j]结尾的最长LCIS
        int[][] dp = new int[N+1][N+1];
        int res = 0;
        for (int i = 1; i <= N; i++) {
            int maxv = 1;
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i-1][j];
                if (A[i-1] == B[j-1]) {
                    dp[i][j] = Math.max(dp[i][j], maxv);
                }
                if (B[j-1] < A[i-1]) {
                    maxv = Math.max(maxv, dp[i][j]+1);
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}