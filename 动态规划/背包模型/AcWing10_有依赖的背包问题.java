import java.util.*;
import java.io.*;

class Main {

    static int N, V;
    static int[] vs;
    static int[] ws;
    static int idx;
    static int[] h, ne, e;
    static int[][] dp;
    //添加边a->b
    public static void add(int a, int b) {
        e[idx] = b; ne[idx] = h[a]; h[a] = idx++;
    }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        N = in[0]; V = in[1];
        vs = new int[N+1]; ws = new int[N+1];
        h = new int[N+1]; ne = new int[N+1]; e = new int[N+1];
        Arrays.fill(h, -1);
        dp = new int[N+1][V+1];
        int root = 0;
        for (int i = 1; i <= N; i++) {
            int[] t = read(br);
            vs[i] = t[0]; ws[i] = t[1];
            if (t[2] == -1) {
                root = i;
            } else {
                add(t[2], i);
            }
        }
        dfs(root);
        out.println(dp[root][V]);
        out.flush();
    }

    //dp[i][j]: 在i子树下，体积不操作j的时候最大的价值
    public static void dfs(int u) {
        for (int i = h[u]; i != -1; i = ne[i]) {
            dfs(e[i]);
            //先将根节点的体积空出来，根节点是必选的
            for (int j = V-vs[u]; j >= 0; j--) {
                for (int k = 0; k <= j; k++) {
                    //这里为什么可以直接用根节点+子节点？难道不会重复吗？理解不能...
                    dp[u][j] = Math.max(dp[u][j], dp[u][j-k]+dp[e[i]][k]);
                }
            }
        }
        for (int j = V; j >= 0; j--) {
            if (j < vs[u]) {
                dp[u][j] = 0;
            } else {
                dp[u][j] = dp[u][j-vs[u]] + ws[u];
            }
        }
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}