import java.util.*;
import java.io.*;

class Main {


    static int INF = 0x3f3f3f3f;
    static int[] dir = {1, 0, -1, 0, 1};
    static int N, M, P, K, S;
    // 第i个房间的钥匙
    static int[] key;
    // 邻接矩阵，权值为门的种类
    static int[][] w;

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] in = read(br);
        N = in[0]; M = in[1]; P = in[2];
        K = read(br)[0];

        w = new int[N * M + 1][N * M + 1];
        key = new int[N * M + 2];
        for (int i = 0; i <= N * M; i++) {
            Arrays.fill(w[i], -1);
        }

        for (int i = 0; i < K; i++) {
            int[] t = read(br);
            int a = flat(t[0], t[1]);
            int b = flat(t[2], t[3]);
            int g = t[4];
            // a,b两点之间有门或者墙
            w[a][b] = w[b][a] =  g;
        }

        S = read(br)[0];
        for (int i = 0; i < S; i++) {
            int[] t = read(br);
            key[flat(t[0], t[1])] |= 1 << t[2];
        }

        out.println(bfs());
        out.flush();
    }

    public static int bfs() {
        // 最短距离以及手上钥匙的状态
        Deque<int[]> queue = new ArrayDeque<>();
        int[][] dis = new int[N * M + 1][1 << (P + 1)];
        for (int i = 0; i < N * M + 1; i++) {
            Arrays.fill(dis[i], INF);
        }
        boolean[][] vis = new boolean[N * M + 1][1 << (P + 1)];
        dis[1][key[1]] = 0;
        queue.offer(new int[] {1, key[1]});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int[] t = unflat(cur[0]);
            int i = t[0], j = t[1];
            int st = cur[1];
            if (vis[cur[0]][st]) {
                continue;
            }
            vis[cur[0]][st] = true;

            if (cur[0] == N * M) {
                return dis[cur[0]][cur[1]];
            }

            for (int k = 0; k < 4; k++) {
                int nx = i + dir[k];
                int ny = j + dir[k + 1];
                int nn = flat(nx, ny);
                if (nx < 1 || nx > N || ny < 1 || ny > M) {
                    continue;
                }

                // 墙或者没有对应的钥匙
                if (w[cur[0]][nn] == 0 || (w[cur[0]][nn] != -1 && ((st >>> w[cur[0]][nn]) & 1) == 0)) {
                    continue;
                }

                int nst = st | key[nn];
                if (dis[nn][nst] > dis[cur[0]][st] + 1) {
                    dis[nn][nst] = dis[cur[0]][st] + 1;
                    queue.offer(new int[] {nn, nst});
                }
            }
        }
        return -1;
    }


    public static int flat(int x, int y) {
        return (x - 1) * M + y;
    }


    public static int[] unflat(int i) {
        return new int[] {(i + M - 1) / M, (i - 1) % M + 1};
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class AcWing1131_拯救大兵瑞恩 {
    public static void main(String[] args) throws Exception {
        // 输入重定向
        System.setIn(new FileInputStream("./input.txt"));
        new Main().main();
    }
}