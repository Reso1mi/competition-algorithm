import java.util.*;
import java.io.*;

class Main {

    static int INF = 0x3f3f3f3f;
    static int N, M;
    static int idx;
    static int[] e, ne, h, w;
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
        int N = in[0], M = in[1];
        e = new int[2*M+1]; ne = new int[2*M+1]; w = new int[2*M+1];
        h = new int[N+1]; Arrays.fill(h, -1);
        for (int i = 0; i < M; i++) {
            int[] t = read(br);
            add(t[0], t[1], 100-t[2]);
            add(t[1], t[0], 100-t[2]);
        }
        int[] t = read(br);
        int A = t[0], B = t[1];
        double[] dis = new double[N+1];
        boolean[] vis = new boolean[N+1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->(dis[b]>dis[a])?1:-1);
        dis[A] = 1.0; pq.add(A);
        while (!pq.isEmpty()) {
            int i = pq.poll();
            if (vis[i]) continue;
            vis[i] = true;
            for (int j = h[i]; j != -1; j = ne[j]) {
                int k = e[j];
                if (dis[k] < dis[i] * (w[j]/100.0)) {
                   dis[k] = dis[i] * (w[j]/100.0);
                   pq.add(k);
                }
            }
        }
        out.printf("%.8f", 100/dis[B]);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}