import java.util.*;
import java.io.*;

class Main {

    static int INF = 0x3f3f3f3f;
    static int N, M;
    static int[][] w;

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        M = in[0]; N = in[1];
        w = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(w[i], INF);
        }
        //M*N*N
        for (int i = 1; i <= M; i++) {
            int[] t = read(br);
            for (int j = 0; j < t.length; j++) {
                for (int k = j+1; k < t.length; k++) {
                    w[t[j]][t[k]] = 1;
                }
            }
        }
        int[] dis = new int[N+1];
        boolean[] vis = new boolean[N+1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->dis[a]-dis[b]);
        Arrays.fill(dis, INF);
        dis[1] = 0; pq.add(1);
        while (!pq.isEmpty()) {
            int i = pq.poll();
            if (vis[i]) continue;
            vis[i] = true;
            for (int j = 1; j <= N; j++) {
                if (w[i][j] == INF) continue;
                if (dis[j] > dis[i] + w[i][j]) {
                    dis[j] = dis[i] + w[i][j];
                    pq.add(j);
                }
            }
        }
        out.println(dis[N]==INF ? "NO" : dis[N]-1);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}