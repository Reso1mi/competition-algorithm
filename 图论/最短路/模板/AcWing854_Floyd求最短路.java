import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int N = in[0], M = in[1], K = in[2];
        int INF = 0x3f3f3f3f;
        int[][] dis = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dis[i], INF);
            dis[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            int[] t = read(br);
            int x = t[0], y = t[1];
            dis[x][y] = Math.min(dis[x][y], t[2]);
        }
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dis[i][k] == INF || dis[k][j] == INF) continue;
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }
        for (int i = 0; i < K; i++) {
            int[] q = read(br);
            int res = dis[q[0]][q[1]];
            out.println(res == INF ? "impossible" : res);
        }
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}