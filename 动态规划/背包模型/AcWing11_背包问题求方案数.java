import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int N = in[0], V = in[1];
        int INF = 0x3f3f3f3f;
        int MOD = (int)1e9+7;
        // dp[j]: 体积刚好为j时，最大的价值
        int[] dp = new int[V+1];
        // cnt[j]: 体积刚好为j时，最大价值方案数
        int[] cnt = new int[V+1];
        Arrays.fill(dp, -INF);
        dp[0] = 0; cnt[0] = 1;
        for (int i = 1; i <= N; i++) {
            int[] t = read(br);
            int vi = t[0], wi = t[1];
            for (int j = V; j >= vi; j--) {
                int val = dp[j-vi] + wi;
                if (val > dp[j]) { // 从dp[i-1][j-vi]推过来，val比当前的大，所以cnt[j]被取代
                    cnt[j] = cnt[j-vi];
                    dp[j] = val;
                } else if (val == dp[j]) { // 从dp[i-1][j-vi]推过来，val和当前相同，叠加起来
                    cnt[j] = (cnt[j] + cnt[j-vi]) % MOD;
                }
            }
        }
        int maxW = 0;
        for (int i = 0; i <= V; i++) maxW = Math.max(maxW, dp[i]);
        long res = 0;
        for (int i = 0; i <= V; i++) {
            if (dp[i] == maxW) {
                res = (res + cnt[i]) % MOD;
            }
        }
        out.println(res);
        out.flush();
        out.close();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// class Main {

//     public static void main(String... args) throws Exception {
//         PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int[] in = read(br);
//         int N = in[0], V = in[1];
//         int MOD = (int)1e9+7;
//         //dp[j]: 体积小于等于j时，最大的价值
//         int[] dp = new int[V+1];
//         //cnt[j]: 体积小于等于j时，最大价值方案数
//         int[] cnt = new int[V+1];
//         //这里其实初始化的是dp[0][i]=1，此时最大价值就是0，什么都不装
//         Arrays.fill(cnt, 1);
//         for (int i = 0; i < N; i++) {
//             int[] t = read(br);
//             int vi = t[0], wi = t[1];
//             for (int j = V; j >= vi; j--) {
//                 int val = dp[j-vi] + wi;
//                 if (val > dp[j]) {
//                     dp[j] = val;
//                     cnt[j] = cnt[j-vi];
//                 } else if (val == dp[j]) {
//                     cnt[j] = (cnt[j] + cnt[j-vi]) % MOD;
//                 }
//             }
//         }
//         out.println(cnt[V]);
//         out.flush();
//         out.close();
//     }

//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }

public class AcWing11_背包问题求方案数 {
    public static void main(String[] args) throws Exception {
        new Main().main();
    }
}