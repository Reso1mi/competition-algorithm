/*
给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。

请你求出从1号点到n号点的最多经过k条边的最短距离，如果无法从1号点走到n号点，输出impossible。

注意：图中可能 存在负权回路 。

输入格式
第一行包含三个整数n，m，k。

接下来m行，每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。

输出格式
输出一个整数，表示从1号点到n号点的最多经过k条边的最短距离。

如果不存在满足条件的路径，则输出“impossible”。
 */
import java.util.*;
import java.io.*;

class Main {

    static class Edge {
        int s, e;
        int v;
        public Edge(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
    }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int INF = 0x3f3f3f3f;
        int N = in[0], M = in[1], K = in[2];
        Edge[] eds = new Edge[M];
        for (int i = 0; i < M; i++) {
            int[] t = read(br);
            eds[i] = new Edge(t[0]-1, t[1]-1, t[2]);
        }
        //dis[i][j]: 源点最多经过i条边，到达j点的最短路
        int[] dis = new int[N];
        //上一轮经过(i-1)次松弛后的结果，避免串联更新（当前轮更新当前轮），因为这题需要保证最多只能经过K条边
        int[] last = new int[N];
        Arrays.fill(dis, INF);
        dis[0] = 0;
        for (int i = 0; i < K; i++) {
            System.arraycopy(dis, 0, last, 0, N);
            for (int j = 0; j < M; j++) {
                int s = eds[j].s, e = eds[j].e;
                if (last[s] == INF) continue;
                dis[e] = Math.min(dis[e], last[s] + eds[j].v);
            }
        }
        out.println(dis[N-1] == INF ? "impossible" : dis[N-1]);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// import java.util.*;
// import java.io.*;

// class Main {

//     static class Edge {
//         int s, e;
//         int v;
//         public Edge(int s, int e, int v) {
//             this.s = s;
//             this.e = e;
//             this.v = v;
//         }
//     }

//     public static void main(String... args) throws Exception {
//         PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int[] in = read(br);
//         int INF = 0x3f3f3f3f;
//         int N = in[0], M = in[1], K = in[2];
//         Edge[] eds = new Edge[M];
//         for (int i = 0; i < M; i++) {
//             int[] t = read(br);
//             eds[i] = new Edge(t[0], t[1], t[2]);
//         }
//         //恰好经过i条边
//         int[][] dis = new int[K+1][N+1];
//         for (int i = 0; i <= K; i++) {
//             Arrays.fill(dis[i], INF);
//         }
//         dis[0][1] = 0;
//         for (int i = 1; i <= K; i++) {
//             for (int j = 0; j < M; j++) {
//                 int s = eds[j].s, e = eds[j].e;
//                 if (dis[i-1][s] == INF) continue;
//                 dis[i][e] = Math.min(dis[i][e], dis[i-1][s] + eds[j].v);
//             }
//         }
//         int res = INF;
//         for (int i = 0; i <= K; i++) {
//             res = Math.min(res, dis[i][N]);
//         }
//         out.println(res == INF ? "impossible" : res);
//         out.flush();
//     }

//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }