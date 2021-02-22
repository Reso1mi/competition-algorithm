import java.util.*;
import java.io.*;

/*
多叉树的存储（左孩子，右兄弟）+ 记忆化递归
 */
class Main {

    static int N, M;
    static int[][] dp;
    //二叉树节点
    static int[] left, right;
    static int[] son;
    static int[] v, w;

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        N = in[0]; M = in[1];
        left = new int[N+1]; right = new int[N+1];
        v = new int[N+1]; w = new int[N+1];
        son = new int[N+1];
        dp = new int[N+1][M+1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }
        int root = 0;
        for (int i = 1; i <= N; i++) {
            int[] t = read(br);
            v[i] = t[0]; w[i] = t[1];
            if (t[2] == -1) {
                root = i;
            } else {
                if (son[t[2]] == 0) {
                    left[t[2]] = i;
                } else {
                    right[son[t[2]]] = i;
                }
                son[t[2]] = i;
            }
        }
        out.println(dfs(root, M));
        out.flush();
    }

    //在i子树下，体积不超过j的情况下，能得到的最大收益
    //子树的选取不是孤立的，是可以一起选的，所以应该按照体积分配划分子树
    public static int dfs(int i, int j) {
        // i=0子树不存在
        if (i==0 || j < 0) return 0;
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        //不选当前节点，体积j全部分配给兄弟节点
        dp[i][j] = dfs(right[i], j);
        //当前节点必选，分给子节点k，兄弟节点j-v[i]-k
        for (int k = 0; k <= j-v[i]; k++) {
            dp[i][j] = Math.max(dp[i][j], dfs(left[i], k) + dfs(right[i], j-v[i]-k) + w[i]);
        }
        return dp[i][j];
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}


// class Main {

//     static int N, V;
//     static int[] vs;
//     static int[] ws;
//     static int idx;
//     static int[] h, ne, e;
//     static int[][] dp;
//     //添加边a->b
//     public static void add(int a, int b) {
//         e[idx] = b; ne[idx] = h[a]; h[a] = idx++;
//     }

//     public static void main(String... args) throws Exception {
//         PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int[] in = read(br);
//         N = in[0]; V = in[1];
//         vs = new int[N+1]; ws = new int[N+1];
//         h = new int[N+1]; ne = new int[N+1]; e = new int[N+1];
//         Arrays.fill(h, -1);
//         dp = new int[N+1][V+1];
//         int root = 0;
//         for (int i = 1; i <= N; i++) {
//             int[] t = read(br);
//             vs[i] = t[0]; ws[i] = t[1];
//             if (t[2] == -1) {
//                 root = i;
//             } else {
//                 add(t[2], i);
//             }
//         }
//         dfs(root);
//         out.println(dp[root][V]);
//         out.flush();
//     }

//     //dp[i][j]: 在i子树下，体积不操作j的时候最大的价值
//     public static void dfs(int u) {
//         for (int i = h[u]; i != -1; i = ne[i]) {
//             dfs(e[i]);
//             //先将根节点的体积空出来，根节点是必选的
//             for (int j = V-vs[u]; j >= 0; j--) {
//                 for (int k = 0; k <= j; k++) {
//                     //这里为什么可以直接用根节点+子节点？难道不会重复吗？理解不能...
//                     dp[u][j] = Math.max(dp[u][j], dp[u][j-k]+dp[e[i]][k]);
//                 }
//             }
//         }
//         for (int j = V; j >= 0; j--) {
//             if (j < vs[u]) {
//                 dp[u][j] = 0;
//             } else {
//                 dp[u][j] = dp[u][j-vs[u]] + ws[u];
//             }
//         }
//     }

//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }
// 
/*
10 10
3 4 9
1 3 3
4 5 7
5 9 1
4 4 10
2 3 1
2 6 1
2 1 1
2 1 -1
4 6 2
14

20 50
18 23 10
4 31 10
1 27 4
22 21 7
16 1 10
22 31 14
7 39 -1
25 16 3
2 40 8
7 44 3
3 10 3
21 45 8
20 29 19
11 17 4
20 46 18
14 48 13
9 48 8
15 21 3
5 30 3
10 32 18

202
 */