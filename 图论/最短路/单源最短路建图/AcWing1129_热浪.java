import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int INF = 0x3f3f3f3f;
        int T = in[0], C = in[1], Ts = in[2], Te = in[3];
        int[][] w = new int[T+1][T+1];
        for (int i = 1; i <= T; i++) {
            Arrays.fill(w[i], INF);
        }
        for (int i = 0; i < C; i++) {
            int[] t = read(br);
            int x = t[0], y = t[1];
            w[x][y] = Math.min(w[x][y], t[2]);
            w[y][x] = Math.min(w[y][x], t[2]);
        }
        int[] dis = new int[T+1];
        Arrays.fill(dis, INF);
        dis[Ts] = 0;
        boolean[] vis = new boolean[T+1];
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b)->dis[a]-dis[b]);
        queue.add(Ts);
        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (vis[i]) continue;
            vis[i] = true;
            for (int j = 1; j <= T; j++) {
                if (w[i][j] == INF) continue;
                if (dis[j] > dis[i] + w[i][j]) {
                    dis[j] = dis[i] + w[i][j];
                    queue.add(j);
                }
            }
        }
        out.println(dis[Te]);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}