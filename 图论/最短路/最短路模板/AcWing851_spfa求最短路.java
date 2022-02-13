/*
给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。

请你求出1号点到n号点的最短距离，如果无法从1号点走到n号点，则输出impossible。

数据保证不存在负权回路。

输入格式
第一行包含整数n和m。

接下来m行每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。

输出格式
输出一个整数，表示1号点到n号点的最短距离。

如果路径不存在，则输出”impossible”。

数据范围
1≤n,m≤105,
图中涉及边长绝对值均不超过10000。
 */
import java.util.*;
import java.io.*;

class Main {

    static int idx;
    static int[] e, h, ne, w;
    //a->b
    public static void add(int a, int b, int c){
        e[idx] = b; w[idx] = c;
        ne[idx] = h[a]; h[a] = idx++;
    }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int INF = 0x3f3f3f3f;
        int[] in = read(br);
        int N = in[0], M = in[1];
        e = new int[M+1]; ne = new int[M+1]; w = new int[M+1];
        h = new int[N+1]; Arrays.fill(h, -1);
        for (int i = 0; i < M; i++) {
            int[] t = read(br);
            add(t[0], t[1], t[2]);
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] vis = new boolean[N+1];
        int[] dis = new int[N+1];
        Arrays.fill(dis, INF);
        dis[1] = 0; vis[1] = true;
        queue.add(1);
        while (!queue.isEmpty()) {
            int top = queue.poll();
            vis[top] = false;
            for (int i = h[top]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[top] + w[i]) {
                    dis[j] = dis[top] + w[i];
                    if (!vis[j]) {
                        queue.add(j);
                        vis[j] = true;
                    }
                }
            }
        }
        out.println(dis[N] == INF ? "impossible" : dis[N]);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}