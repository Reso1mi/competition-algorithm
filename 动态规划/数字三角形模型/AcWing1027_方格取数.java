import java.io.*;
import java.util.*; 
public class AcWing1027_方格取数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] w = new int[N][N];
        while(sc.hasNext()){
            int i = sc.nextInt();
            if (i == 0) break; 
            int j = sc.nextInt();
            w[i-1][j-1] = sc.nextInt(); 
        } 
        System.out.println(slove2(w, N));
    }

    //N^4 dp
    public static int slove(int[][] w, int N) {
        int[][][][] dp = new int[N+1][N+1][N+1][N+1];
        for (int i1 = 1; i1 <= N; i1++) {
            for (int j1 = 1; j1 <= N; j1++) {
                for (int i2 = 1; i2 <= N; i2++) {
                    for (int j2 = 1; j2 <= N; j2++) {
                        int max1 = dp[i1-1][j1][i2-1][j2];
                        int max2 = dp[i1-1][j1][i2][j2-1];
                        int max3 = dp[i1][j1-1][i2-1][j2];
                        int max4 = dp[i1][j1-1][i2][j2-1];
                        dp[i1][j1][i2][j2] = Math.max(Math.max(max1, max2), Math.max(max3, max4)) + ((i1==i2&&j1==j2) ? w[i1-1][j1-1] : (w[i1-1][j1-1] + w[i2-1][j2-1])); 
                    }
                }
            }
        }
        return dp[N][N][N][N];
    }

    //N^3优化，根据走的总步数计算另一个坐标位置
    public static int slove2(int[][] w, int N) {
        int[][][] dp = new int[2*N+1][N+1][N+1];
        for (int k = 2; k <= 2*N; k++) {
            for (int i1 = 1; i1 <= N; i1++) {
                for (int i2 = 1; i2 <= N; i2++) {
                    int j1 = k-i1, j2 = k-i2;
                    if (1 <= j1 && j1 <= N && 1 <= j2 && j2 <= N) {
                        int max1 = dp[k-1][i1-1][i2-1];
                        int max2 = dp[k-1][i1-1][i2];
                        int max3 = dp[k-1][i1][i2-1];
                        int max4 = dp[k-1][i1][i2];
                        dp[k][i1][i2] = Math.max(Math.max(max1, max2), Math.max(max3, max4)) + ((i1==i2&&j1==j2) ? w[i1-1][j1-1] : (w[i1-1][j1-1] + w[i2-1][j2-1])); 
                    }
                }
            }
        }
        return dp[2*N][N][N];
    }
}