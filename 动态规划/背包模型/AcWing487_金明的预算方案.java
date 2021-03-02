import java.util.*;
import java.io.*;

/*
2000 10
500 1 0
400 4 0
300 5 1
400 5 1
200 5 0
500 4 5
400 4 0
320 2 0
410 3 0
400 3 5

7430
 */
class Main {

    static int[] v, w, p;

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int N = in[0], M = in[1];
        v = new int[M+1]; w = new int[M+1]; p = new int[M+1];
        //依赖关系
        List<Integer>[] adj = new ArrayList[M+1];
        for (int i = 1; i <= M; i++) {
            int[] t = read(br);
            v[i] = t[0]; w[i] = t[1]; p[i] = t[2];
            if (adj[p[i]] == null) {
                adj[p[i]] = new ArrayList<>();
            }
            adj[p[i]].add(i);
        }
        int Z = adj[0].size();
        int[] dp = new int[N+1];
        //将依赖关系进行分组枚举所有情况，题目中有一条关键信息就是附件最多只有两件
        //adj[0]就是所有的主件
        // N = 10^4
        for (int i = 1; i <= Z; i++) {
            int pi = adj[0].get(i-1); //pi不是必选，但是要选pi的附件，pi就必选了
            for (int j = N; j >= 0; j--) {
                int s = adj[pi] == null ? 0 : adj[pi].size(); //附件个数,0,1,2
                //枚举当前组内的所有情况
                for (int k = 0; k < (1<<s); k++) {
                    //当前组某项的体积和价值和
                    int t = v[pi]*w[pi];
                    int sv = v[pi];
                    for (int c = 0; c < s; c++) {
                        if (((k>>>c)&1)==1) {
                            int d = adj[pi].get(c);
                            t += v[d]*w[d];
                            sv += v[d];
                        }
                    }
                    if (j >= sv) {
                        dp[j] = Math.max(dp[j], dp[j-sv] + t);
                    }
                }
            }
        }
        out.println(dp[N]);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// class Main {

//     static int[] v, w, p;

//     public static void main(String... args) throws Exception {
//         PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int[] in = read(br);
//         int N = in[0], M = in[1];
//         v = new int[M+1]; w = new int[M+1]; p = new int[M+1];
//         //依赖关系
//         List<Integer>[] adj = new ArrayList[M+1];
//         for (int i = 1; i <= M; i++) {
//             int[] t = read(br);
//             v[i] = t[0]; w[i] = t[1]; p[i] = t[2];
//             if (adj[p[i]] == null) {
//                 adj[p[i]] = new ArrayList<>();
//             }
//             adj[p[i]].add(i);
//         }
//         int Z = adj[0].size();
//         int[][] dp = new int[Z+1][N+1];
//         //将依赖关系进行分组枚举所有情况，题目中有一条关键信息就是附件最多只有两件
//         //adj[0]就是所有的主件
//         // N = 10^4
//         for (int i = 1; i <= Z; i++) {
//             int pi = adj[0].get(i-1); //pi不是必选，但是要选pi的附件，pi就必选了
//             for (int j = N; j >= 0; j--) {
//                 dp[i][j] = dp[i-1][j];
//                 int s = adj[pi] == null ? 0 : adj[pi].size(); //附件个数,0,1,2
//                 //枚举当前组内的所有情况
//                 for (int k = 0; k < (1<<s); k++) {
//                     //当前组某项的体积和价值和
//                     int t = v[pi]*w[pi];
//                     int sv = v[pi];
//                     for (int c = 0; c < s; c++) {
//                         if (((k>>>c)&1)==1) {
//                             int d = adj[pi].get(c);
//                             t += v[d]*w[d];
//                             sv += v[d];
//                         }
//                     }
//                     if (j >= sv) {
//                         dp[i][j] = Math.max(dp[i-1][j], Math.max(dp[i][j],dp[i-1][j-sv] + t));
//                     }
//                 }
//             }
//         }
//         out.println(dp[Z][N]);
//         out.flush();
//     }

//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }