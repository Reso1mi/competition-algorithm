/*
给定N个正整数A1,A2,…,AN，从中选出若干个数，使它们的和为M，求有多少种选择方案。

输入格式
第一行包含两个整数N和M。

第二行包含N个整数，表示A1,A2,…,AN。

输出格式
包含一个整数，表示可选方案数。

数据范围
1≤N≤100,
1≤M≤10000,
1≤Ai≤1000
输入样例：
4 4
1 1 2 2
输出样例：
3
 */
import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] w = new int[N];
        for (int i = 0; i < N; i++) {
            w[i] = sc.nextInt();
        }
        System.out.println(solve(w, M));
    }

    //01背包求方案数量
    public static int solve (int[] w, int M){
        int[] dp = new int[M+1];
        dp[0] = 1;
        for (int i = 0; i < w.length; i++) {
            for (int j = M; j >= w[i]; j--) {
                dp[j] += dp[j-w[i]];
            }
        }
        return dp[M];
    }
}