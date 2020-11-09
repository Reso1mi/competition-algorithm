import java.io.*;
import java.util.*;
//275. 传纸条 https://www.acwing.com/problem/content/description/277/
class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] w = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                w[i][j] = sc.nextInt();
            }
        }
        System.out.println(solve(w, m, n));
    }

    //N^3
    public static int solve(int[][] w, int m, int n){
        int[][][] dp = new int[m+n+1][m+1][m+1];
        for (int k = 2; k <= m+n; k++) {
            for (int i1 = 1; i1 <= m; i1++) {
                for (int i2 = 1; i2 <= m; i2++) {
                    int j1 = k - i1, j2 = k - i2;
                    if (j1 < 1 || j1 > n || j2 < 1 || j2 > n) continue;
                    int temp = dp[k][i1][i2]; 
                    temp = Math.max(temp, dp[k-1][i1-1][i2]);
                    temp = Math.max(temp, dp[k-1][i1][i2-1]);
                    temp = Math.max(temp, dp[k-1][i1][i2]);
                    temp = Math.max(temp, dp[k-1][i1-1][i2-1]);
                    dp[k][i1][i2] = temp + (i1==i2 ? w[i1-1][j1-1] : (w[i1-1][j1-1] + w[i2-1][j2-1]));
                }
            }
        }
        return dp[m+n][m][m];
    }
}   