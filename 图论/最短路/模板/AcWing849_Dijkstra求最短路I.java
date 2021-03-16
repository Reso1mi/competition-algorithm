import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int INF = 0x3f3f3f3f;
        int[] in = read(br);
        int N = in[0], M = in[1];
        //邻接矩阵
        int[][] w = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(w[i], INF);
        }
        for (int i = 0; i < M; i++) {
            int[] t = read(br); //t[0]->t[1]
            int x = t[0]-1, y = t[1]-1;
            w[x][y] = Math.min(t[2], w[x][y]);
        }
        //dis[i]: 源点s到i的最短距离
        int[] dis = new int[N];
        boolean[] vis = new boolean[N];
        Arrays.fill(dis, INF);
        dis[0] = 0;
        for (int i = 0; i < N; i++) {
            int min = -1;
            //已确定点集外距离s最近的点
            for (int j = 0; j < N; j++) {
                if (!vis[j] && (min==-1 || dis[j] < dis[min])) {
                    min = j;
                }
            }
            //源点能到达的最近的点
            vis[min] = true;
            for (int j = 0; j < N; j++) {
                if (!vis[j]) {
                    dis[j] = Math.min(dis[j], dis[min] + w[min][j]);
                }
            }
        }
        out.println(dis[N-1] == INF ? -1 : dis[N-1]);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}