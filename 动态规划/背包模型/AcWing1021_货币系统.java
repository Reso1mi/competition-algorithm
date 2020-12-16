/*
给你一个n种面值的货币系统，求组成面值为m的货币有多少种方案。
 */
import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();  
        } 
        System.out.println(solve(v, m));
    }

    public static long solve(int[] v, int m) {
        int n = v.length;
        long[] dp = new long[m+1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = v[i]; j <= m; j++) {
                dp[j] += dp[j-v[i]];
            }
        }
        return dp[m];
    }
}