import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int P;
    static int K;
    static int[] A, B, L;
    static int idx;
    static int[] e, ne, h, w;
    static Deque<Integer> queue = new ArrayDeque<>();
    static int INF = 0x3f3f3f3f;
    static int[] dis;
    static boolean[] vis;

    //a->b
    static void add(int a, int b, int c) {
        e[idx] = b; w[idx] = c;
        ne[idx] = h[a]; h[a] = idx++;
    }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        N = in[0]; P = in[1]; K = in[2];
        L = new int[P+1]; A = new int[P+1]; B = new int[P+1];
        e = new int[2*P+1]; ne = new int[2*P+1]; w = new int[2*P+1]; h = new int[N+1];
        for (int i = 0; i < P; i++) {
            int[] t = read(br);
            A[i] = t[0]; B[i] = t[1]; L[i] = t[2];
        }
        int left = 0, right = 1000000;
        int res = -1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            //System.out.println(mid);
            if (bfs(mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        out.println(res);
        out.flush();
    }

    public static boolean bfs(int cost) {
        queue.clear();
        idx = 0;
        Arrays.fill(e, 0);
        Arrays.fill(w, 0);
        Arrays.fill(ne, 0);
        Arrays.fill(h, -1);
        dis = new int[N+1];
        vis = new boolean[N+1];
        Arrays.fill(dis, INF);
        for (int i = 0; i < P; i++) {
            // 大于cost的不付钱，算在免费的K条电缆中，消耗免费额度，权值为1
            // 小于等于cost的部分付cost，不算在免费额度中，权值为0（权值为消耗免费额度的数量，最后判断最短路能否小于k）
            add(A[i], B[i], (L[i] > cost) ? 1 : 0);
            add(B[i], A[i], (L[i] > cost) ? 1 : 0);
        }
        queue.add(1);
        dis[1] = 0;
        while (!queue.isEmpty()) {
            int i = queue.pop();
            if (vis[i]) continue;
            vis[i] = true;
            for (int j = h[i]; j != -1; j = ne[j]) {
                if (dis[e[j]] > dis[i] + w[j]) {
                    dis[e[j]] = dis[i] + w[j];
                    if (w[j] == 0) {
                        queue.addFirst(e[j]); 
                    } else {
                        queue.add(e[j]);
                    }
                }
            }
        }
        //System.out.printf("cost = %d, dis[N] = %d \n", cost, dis[N]);
        return dis[N] <= K;
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// public class AcWing340_通信线路 {
//     public static void main(String[] args) throws Exception{
//         new Main().main();
//     }
// }