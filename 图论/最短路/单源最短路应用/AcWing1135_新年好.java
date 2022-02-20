import java.io.*;
import java.util.*;

class Main {

    static int idx;
    static int[] e, ne, h, w;
    static int N, M;
    static int[] tar;
    static int INF = 0x3f3f3f3f;
    static boolean[] vis;
    static HashMap<String, Integer> dp = new HashMap<>();

    //a->b 
    public static void add(int a, int b, int c){ 
        e[idx] = b; ne[idx] = h[a];
        w[idx] = c; h[a] = idx++;
    }

    public static void main(String... args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int[] in = read(br);
        N = in[0]; M = in[1];
        h = new int[N+1]; e = new int[2*M+1]; ne = new int[2*M+1]; w = new int[2*M+1];
        Arrays.fill(h, -1);
        tar = read(br);
        for (int i = 0; i < M; i++) {
            int[] _in = read(br);
            add(_in[0], _in[1], _in[2]);
            add(_in[1], _in[0], _in[2]);
        }

        // 初始化以各个点为源点的最短路
        dijkstra(1);
        for (int i = 0; i < tar.length; i++) {
            dijkstra(tar[i]);
        }

        vis = new boolean[tar.length];
        int res = INF;
        for (int i = 0; i < tar.length; i++) {
            vis[i] = true;
            res = Math.min(res, dp.get(1 + "," + tar[i]) + dfs(i));
            vis[i] = false;
        }
        out.println(res);
        out.flush();
    }

    public static int dfs(int c) {
        boolean flag = false;
        int res = INF;
        for (int i = 0; i < tar.length; i++) {
            if (vis[i]) continue;
            vis[i] = true;
            res = Math.min(res, dp.get(tar[c] + "," + tar[i]) + dfs(i));
            vis[i] = false;
            flag = true;
        }
        if (!flag) {
            return 0;
        }
        return res;
    }

    public static void dijkstra(int s) {
        int[] dis = new int[N+1];
        Arrays.fill(dis, INF);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> dis[a] - dis[b]);
        dis[s] = 0; pq.add(s);
        boolean[] vis = new boolean[N+1];
        while (!pq.isEmpty()) {
            int i = pq.poll();
            if (vis[i]) continue;
            vis[i] = true;
            for (int j = h[i]; j != -1; j = ne[j]) {
                if (dis[i] + w[j] < dis[e[j]]) {
                    dis[e[j]] = dis[i] + w[j];
                    pq.add(e[j]);
                }
            }
        }
        for (int i = 0; i < tar.length; i++) {
            dp.put(s + "," + tar[i], dis[tar[i]]);
        }
    }
    

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}


public class AcWing1135_新年好 {
    public static void main(String[] args) throws Exception{
        new Main().main();
    }
}