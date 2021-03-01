import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int N = in[0], M = in[1];
        int[] v = new int[M+1];
        int[] w = new int[M+1];
        int[] p = new int[M+1];
        List<Integer>[] adj = new ArrayList[M+1];
        for (int i = 1; i <= M; i++) {
            int[] t = read(br);
            v[i] = t[0]; w[i] = t[1]; p[i] = t[2];
            if (adj[p[i]] == null) {
                adj[p[i]] = new ArrayList<>();
            }
            adj[p[i]].add(i);
        }
        int[] dp = new int[N+1];
        //adj[0]就是所有的主件
        for (int i = 0; i < adj[0].size(); i++) {
            int pi = adj[0].get(i); //pi不是必选，但是要选pi的附件，pi就必选了
            //先考虑选不选当前节点
            for (int j = N; j >= v[pi]; j--) {
                dp[j] = Math.max(dp[j], dp[j-v[pi]] + v[pi]*w[pi] + );
            }
            for (int j = N; j >= 0; j--) {
                if (adj[pi] == null) break;
                for (Integer k : adj[pi]) {
                    if (j-v[k] < 0) continue;
                    dp[j] = Math.max(dp[j], dp[j-v[k]]+v[k]*w[k]);
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