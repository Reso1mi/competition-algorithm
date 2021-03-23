import java.util.*;
import java.io.*;

class Main {

    static class Node {
        int i, v;
        int max, min;
        public Node(int i, int v, int max, int min) {
            this.i = i; this.v = v;
            this.max = max; this.min = min;
        }
    }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int INF = 0x3f3f3f3f;
        int M = in[0], N = in[1];
        int[][] w = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(w[i], INF);
        }
        // 记录每个物品的价格和主人等级
        int[] price = new int[N+1];
        int[] rank = new int[N+1];
        for (int i = 1; i <= N; i++) {
            int[] plx = read(br);
            price[i] = plx[0]; rank[i] = plx[1];
            for (int j = 1; j <= plx[2]; j++) {
                int[] tv = read(br);
                w[i][tv[0]] = tv[1];
            }
        }
        int res = INF;
        int[] dis = new int[N+1];
        boolean[] vis = new boolean[N+1];
        // pq中存储节点的编号，以及路径上的最大和最小等级
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.v-b.v);
        Arrays.fill(dis, INF);
        dis[1] = 0; pq.add(new Node(1, 0, rank[1], rank[1]));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int i = node.i, v = node.v;
            if (vis[i]) continue;
            vis[i] = true;
            // 在当前位置停止替换
            res = Math.min(res, dis[i]+price[i]);
            for (int j = 1; j <= N; j++) {
                if (w[i][j] == INF) continue;
                // 等级限制无法交易
                if (Math.abs(rank[j]-node.min) > M || 
                    Math.abs(rank[j]-node.max) > M ) continue; 
                if (dis[j] > v + w[i][j]) {
                    dis[j] = v + w[i][j];
                    pq.add(new Node(j, dis[j], Math.max(rank[j], node.max), Math.min(rank[j], node.min)));
                }
            }
        }
        out.println(res);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}