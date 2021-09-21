import java.util.*;
import java.io.*;


class Main {

    /*
    6 2
    3 2 6 5 0 3
    */

    static int INF = -0x3f3f3f3f;

    public static void main(String ...args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int[] in = read(br);
        int N = in[0], K = in[1];
        int[] price = read(br);
        if (K > N/2) {
            out.println(maxProfit(price));
            out.flush();
            return;
        }
        // dp[N][K][2]的写法
        int[][][] dp = new int[N+1][K+1][2];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                Arrays.fill(dp[i][j], INF);
            }
            dp[i][0][0] = 0;
        }
        int res = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j][0] = Math.max(dp[i-1][j][1] + price[i-1], dp[i-1][j][0]);
                dp[i][j][1] = Math.max(dp[i-1][j-1][0] - price[i-1], dp[i-1][j][1]);
                res = Math.max(dp[i][j][0], res);
            }
        }
        out.println(res);
        out.flush();
    }

    public static int maxProfit(int[] price) {
        int N = price.length;
        int[][] dp = new int[N+1][2];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;
        int res = 0;
        for (int i = 1; i <= N; i++) {
            dp[i][0] = Math.max(dp[i-1][1] + price[i-1], dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][0] - price[i-1], dp[i-1][1]);
            res = Math.max(dp[i][0], res);
        }
        return res;
    }

    // dp[K][N][2]的写法
    // public static void main(String ...args) throws Exception {
    //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //     PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    //     int[] in = read(br);
    //     int N = in[0], K = in[1];
    //     int INF = -0x3f3f3f3f;
    //     int[] price = read(br);
    //     int[][][] dp = new int[K+1][N+1][2];
    //     for (int i = 0; i <= K; i++) {
    //         for (int j = 0; j <= N; j++) {
    //             Arrays.fill(dp[i][j], INF);
    //             dp[0][j][0] = 0;
    //         }
    //     }
    //     int res = 0;
    //     for (int i = 1; i <= K; i++) {
    //         for (int j = 1; j <= N; j++) {
    //             dp[i][j][0] = Math.max(dp[i][j-1][1] + price[j-1], dp[i][j-1][0]);
    //             dp[i][j][1] = Math.max(dp[i-1][j-1][0] - price[j-1], dp[i][j-1][1]);

    //             out.printf("dp[%d][%d][0] = %d\n", i, j, dp[i][j][0]);
    //             out.printf("dp[%d][%d][1] = %d\n", i, j, dp[i][j][1]);
    //             out.println();
    //             res = Math.max(dp[i][j][0], res);
    //         }
    //     }
    //     out.println(dp[1][1][0] + " " + dp[1][1][1]);
    //     out.println(res);
    //     out.flush();
    // }

    public static int[] read(BufferedReader br) throws Exception{
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); 
    }
}

public class AcWing1057_股票买卖IV {
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./input.txt"));
        new Main().main();
    }
}