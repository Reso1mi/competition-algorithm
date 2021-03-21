import java.util.*;
import java.io.*;

class Main {

    static int INF = 0x3f3f3f3f;
    static int N, P, C;
    static int[] cow;
    static int[] e, h, ne, w;
    static int idx;
    //a->b
    public static void add(int a, int b, int c) {
        w[idx] = c; e[idx] = b;
        ne[idx] = h[a]; h[a] = idx++;
    }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] in = read(br);
        N = in[0]; P = in[1]; C = in[2];
        //边数: 2*C
        e = new int[C*2+1]; ne = new int[C*2+1]; w = new int[C*2+1];
        //点数: P
        h = new int[P+1]; Arrays.fill(h, -1);
        cow = new int[N+1]; // 奶牛所在牧场
        for (int i = 1; i <= N; i++) {
            cow[i] = read(br)[0];
        }
        for (int i = 0; i < C; i++) {
            int[] t = read(br);
            add(t[0], t[1], t[2]);
            add(t[1], t[0], t[2]);
        }
        int res = INF;
        for (int i = 1; i <= P; i++) {
            res = Math.min(dijkstra(i), res);
        }
        out.println(res);
        out.flush();
    }

    public static int dijkstra(int s) {
        int[] dis = new int[P+1];
        boolean[] vis = new boolean[P+1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->dis[a]-dis[b]);
        Arrays.fill(dis, INF);
        dis[s] = 0; pq.add(s);
        while (!pq.isEmpty()) {
            int i = pq.poll();
            if (vis[i]) continue;
            vis[i] = true;
            for (int j = h[i]; j != -1; j = ne[j]) { //边编号
                int k = e[j]; //点编号
                if (w[j] == INF || dis[i] == INF) continue; 
                if (dis[k] > dis[i] + w[j]) {
                    dis[k] = dis[i] + w[j];
                    pq.add(k);
                }
            }
        }
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            if (dis[cow[i]] == INF) return INF;
            sum += dis[cow[i]];
        }
        return sum;
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}