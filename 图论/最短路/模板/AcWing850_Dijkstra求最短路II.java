import java.util.*;
import java.io.*;

class Main {

    static class Node {
        int idx, val;
        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    static int idx;
    static int N, M;
    static int[] h, e, ne, w;
    //a->b
    static void add(int a, int b, int c) {
        w[idx] = c; e[idx] = b; 
        ne[idx] = h[a]; h[a] = idx++;
    }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int INF = 0x3f3f3f3f;
        int[] in = read(br);
        N = in[0]; M = in[1];
        h = new int[N+1]; e = new int[M+1]; ne = new int[M+1]; w = new int[M+1];
        Arrays.fill(h, -1);
        for (int i = 0; i < M; i++) {
            int[] t = read(br);
            add(t[0], t[1], t[2]);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2)->n1.val-n2.val);
        int[] dis = new int[N+1];
        //记录已经确定最短路的点集s
        boolean[] vis = new boolean[N+1];
        Arrays.fill(dis, INF);
        dis[1] = 0;
        pq.add(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            //从未确定最短路的点中找dis最小的（如果弹出来的是已经确定过的就会跳过）
            int i = node.idx, v = node.val;
            if (vis[i]) continue;
            //标记该点已经确定最短路
            vis[i] = true;
            //松弛该点出边
            for (int j = h[i]; j != -1; j = ne[j]) {
                if (v+w[j] < dis[e[j]]) {
                    dis[e[j]] = v+w[j];
                    pq.add(new Node(e[j], v+w[j]));
                }
            }
        }
        out.println(dis[N] == INF ? -1 : dis[N]);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}