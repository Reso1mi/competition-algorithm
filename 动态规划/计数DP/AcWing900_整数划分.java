import java.util.*;
import java.io.*;

class Main {

    // public static void main(String... args) throws Exception {
    //     PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //     // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
    //     int MOD = (int)1e9+7;
    //     int N = Integer.valueOf(br.readLine());
    //     //问题可以转换成: i个物品，体积分别为[1~i]，凑成j的方案数量，物品数量不限制
    //     //dp[i][j]   = dp[i-1][j] + dp[i-1][j-v] + ... + dp[i-1][j mod v]
    //     //dp[i][j-v] =              dp[i-1][j-v] + ... + dp[i-1][(j-v) mod v]
    //     //dp[i][j] = dp[i-1][j] + dp[i][j-v]
    //     long[] dp = new long[N+1];
    //     dp[0] = 1;
    //     for (int i = 1; i <= N; i++) {
    //         for (int j = i; j <= N; j++) {
    //             dp[j] = (dp[j] + dp[j-i]) % MOD;
    //         }
    //     }
    //     out.println(dp[N]);
    //     out.flush();
    // }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int MOD = (int)1e9+7;
        int N = Integer.valueOf(br.readLine());
        // 将i划分为恰好j份的的方案数量
        long[][] dp = new long[N+1][N+1];
        // 1. 最小值是1，等价于dp[i-1][j-1]
        // 2. 最小值不是1，等价于dp[i-j][j]
        // Arrays.fill(dp[0], 1);
        dp[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = (dp[i-1][j-1] + dp[i-j][j]) % MOD;
            }
        }
        long res = 0;
        for (int i = 1; i <= N; i++) {
            res = (res + dp[N][i]) % MOD;
        }
        out.println(res);
        out.flush();
    }

    // public static void main(String... args) throws Exception {
    //     PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //     // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
    //     int MOD = (int)1e9+7;
    //     int N = Integer.valueOf(br.readLine());
    //     // 将i划分为不超过j份的的方案数量
    //     long[][] dp = new long[N+1][N+1];
    //     // 1. 最小值是1，等价于dp[i-1][j-1]
    //     // 2. 最小值不是1，等价于dp[i-j][j]
    //     Arrays.fill(dp[0], 1);
    //     for (int i = 1; i <= N; i++) {
    //         for (int j = 1; j <= N; j++) {
    //             dp[i][j] = dp[i][j-1];
    //             if (i >= j) {
    //                 dp[i][j] = (dp[i][j] + dp[i-j][j]) % MOD;
    //             }
    //         }
    //     }
    //     out.println(dp[N][N]);
    //     out.flush();
    // }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}