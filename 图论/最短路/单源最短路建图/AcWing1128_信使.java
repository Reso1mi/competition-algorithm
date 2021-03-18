import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] in = read(br);
        int INF = 0x3f3f3f3f;
        int N = in[0], M = in[1];
        int[][] w = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(w[i], INF);
        }
        for (int i = 0; i < M; i++) {
            int[] t = read(br);
            int x = t[0], y = t[1];
            w[x][y] = Math.min(w[x][y], t[2]);
            w[y][x] = Math.min(w[y][x], t[2]);
        }
        int[] dis = new int[N+1];
        boolean[] vis = new boolean[N+1];
        Arrays.fill(dis, INF);
        dis[1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); vis[1] = true;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            vis[i] = false;
            for (int j = 1; j <= N; j++) {
                if (w[i][j]==INF) continue; //其实dis[i]不会为INF
                if (dis[j] > dis[i] + w[i][j]) {
                    dis[j] = dis[i] + w[i][j];
                    if (!vis[j]) {
                        queue.add(j);
                        vis[j] = true;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= N; i++) {
            res = Math.max(res, dis[i]);
        }
        out.println(res==INF ? -1 : res);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}