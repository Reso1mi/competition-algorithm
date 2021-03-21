import java.util.*;
import java.io.*;

class Main {

    static int INF = 0x3f3f3f3f;
    static int N, M;
    static int[] e, ne, w, h;
    static int idx;
    static int MAX = 100010;
    //a->b
    public static void add(int a, int b, int c){
        e[idx] = b; ne[idx] = h[a];
        w[idx] = c; h[a] = idx++;
    }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        M = in[0]; N = in[1];
        e = new int[MAX]; ne = new int[MAX]; w = new int[MAX];
        h = new int[N+1]; Arrays.fill(h, -1);
        for (int i = 0; i < M; i++) {
            int[] t = read(br);
            for (int j = 0; j < t.length-1; j++) {

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
            for (int j = h[i]; j != -1; j = ne[j]) {
                int k = e[j];
                if (dis[k] > dis[i] + w[j]) {
                    dis[k] = dis[i] + w[j];
                    pq.add(k);
                }
            }
        }
        out.println(dis[N]==INF ? "No" : dis[N]);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}