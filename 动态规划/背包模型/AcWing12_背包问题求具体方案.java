/*
有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。

第 i 件物品的体积是 vi，价值是 wi。

求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。

输出 字典序最小的方案。这里的字典序是指：所选物品的编号所构成的序列。物品的编号范围是 1…N。

输入格式
第一行两个整数，N，V，用空格隔开，分别表示物品数量和背包容积。

接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 件物品的体积和价值。

输出格式
输出一行，包含若干个用空格隔开的整数，表示最优解中所选物品的编号序列，且该编号序列的字典序最小。

物品编号范围是 1…N。

数据范围
0<N,V≤1000
0<vi,wi≤1000
 */
import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int N = in[0], M = in[1];
        int[] v = new int[N];
        int[] w = new int[N];
        for (int i = 0; i < N; i++) {
            int[] t = read(br);
            v[i] = t[0]; w[i] = t[1];
        }
        //第i个物品到最后（包括i），体积不超过j的情况下最大收益（状态不同于之前的状态）
        //dp[i][j] = min(dp[i+1][j], dp[i+1][j-v]+w)
        int[][] dp = new int[N+1][M+1];
        for (int i = N-1; i >= 0; i--) {
            for (int j = M; j >= 0; j--) {
                dp[i][j] = dp[i+1][j];
                if (j >= v[i]) {
                    dp[i][j] = Math.max(dp[i+1][j-v[i]] + w[i], dp[i+1][j]);;
                }
            }
        }
        for (int i = 0; i <= N-1; i++) {
            if (M-v[i] < 0) continue;
            if (dp[i][M] == dp[i+1][M-v[i]] + w[i]) {
                out.print((i+1)+" ");
                M -= v[i];
            }
        }
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class AcWing12_背包问题求具体方案 {
    public static void main(String[] args) throws Exception {
        new Main().main();
    }
}


// import java.util.*;
// import java.io.*;

// class Main {

//     static int[][] back;
//     static int[] v, w;
//     static List<String> res = new ArrayList<>();

//     public static void main(String... args) throws Exception {
//         PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int[] in = read(br);
//         int N = in[0], M = in[1];
//         v = new int[N];
//         w = new int[N];
//         for (int i = 0; i < N; i++) {
//             int[] t = read(br);
//             v[i] = t[0]; w[i] = t[1];
//         }
//         //前i个物品，体积不超过j的情况下最大收益
//         int[][] dp = new int[N+1][M+1];
//         //前i个物品，体积不操作j的情况下，第i个物品拿不拿
//         back = new int[N+1][M+1];
//         for (int i = 1; i <= N; i++) {
//             // int[] t = read(br);
//             // int v = t[0], w = t[1];
//             for (int j = M; j >= v[i-1]; j--) {
//                 int temp = dp[i-1][j-v[i-1]] + w[i-1];
//                 if (dp[i-1][j] <= temp) {
//                     if (dp[i-1][j] == temp) {
//                         //可拿可不拿
//                         back[i][j] = 2;
//                     } else {
//                         //必须拿
//                         back[i][j] = 1;
//                     }
//                     dp[i][j] = temp;
//                 } else {
//                     dp[i][j] = dp[i-1][j];
//                 }
//             }
//         }
//         //倒推，需要倒推求出所有的最优情况，然后再求字典序最小的
//         // int tN = N, tM = M;
//         // while (tN > 0) {
//         //     if (back[tN][tM] == 1) {
//         //         res.add(tN);
//         //         tM-=v[tN-1];
//         //     }
//         //     if (back[tN][tM] == 2) {

//         //     }
//         //     tN--;
//         // }
//         for (int i = 1; i <= N; i++) {
//             for (int j = 1; j <= M; j++) {
//                 out.printf("dp[%d][%d]=%d ", i, j, dp[i][j]);
//             }
//             out.println();
//         }

//         for (int i = 1; i <= N; i++) {
//             for (int j = 1; j <= M; j++) {
//                 out.printf("back[%d][%d]=%d ", i, j, back[i][j]);
//             }
//             out.println();
//         }
//         out.println(back[N][M]);
//         dfs(N, M, "");
//         res.forEach(r->out.print(r+" "));
//         out.flush();
//     }

//     public static void dfs(int n, int m, String str) {
//         if (n <= 0) {
//             res.add(str);
//             return;
//         }
//         if (back[n][m] == 1) {
//             dfs(n-1, m-=v[n-1], n+str);
//         } else if (back[n][m] == 2) {
//             dfs(n-1, m-=v[n-1], n+str);
//             dfs(n-1, m, str);
//         } else {
//             dfs(n-1, m, str);
//         }
//     }

//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }