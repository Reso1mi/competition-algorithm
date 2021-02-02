import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int N = in[0], M = in[1];
        int INF = 0x3f3f3f3f;
        List<Integer>[] adj = new ArrayList[N];
        for (int i = 0; i < M; i++) {
            int[] t = read(br);
            int x = t[0]-1, y = t[1]-1;
            if (adj[x] == null) {
                adj[x] = new ArrayList<>();
            }
            if (adj[y] == null) {
                adj[y] = new ArrayList<>();
            }
            adj[x].add(y); adj[y].add(x);
        }
        int K = read(br)[0];
        int[] C = read(br);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < K; i++) map.put(--C[i], i);
        //K个关键点之间的最短距离
        int[][] dis = new int[K][K];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            Arrays.fill(dis[i], INF);
            queue.clear();
            queue.add(new int[]{C[i], 0});
            boolean[] vis = new boolean[N];
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                if (map.containsKey(cur[0])) {
                    dis[i][map.get(cur[0])] = cur[1];
                }
                if (adj[cur[0]] == null) continue;
                for (Integer next : adj[cur[0]]) {
                    if (vis[next]) continue;
                    queue.add(new int[]{next, cur[1]+1});
                    vis[next] = true;
                }
            }
        }
        int[][] dp = new int[1<<K][K];
        for (int i = 0; i < (1<<K); i++) {
            Arrays.fill(dp[i], INF);
        } 
        for (int i = 0; i < K; i++) {
            dp[1<<i][i] = 1;
        }
        //枚举所有状态递推
        for (int mask = 0; mask < (1<<K); mask++) {
            for (int i = 0; i < K; i++) {
                //C[i]被选取，C[j]未被选取（判断去掉也可AC）
                if ((mask&(1<<i))==0) continue;
                for (int j = 0; j < K; j++) {
                    if ((mask&(1<<j))==1 || dis[i][j] == INF || dp[mask][i] == INF) continue;
                    dp[mask|(1<<j)][j] = Math.min(dp[mask|(1<<j)][j], dp[mask][i] + dis[i][j]);
                }
            }
        }
        int res = INF;
        for (int i = 0; i < K; i++) {
            res = Math.min(res, dp[(1<<K)-1][i]);
        }
        if (res == INF) out.println(-1);  
        else out.println(res);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

//  //bfs求法不一样，其他都差不多
//  class Main {

//     public static void main(String... args) throws Exception {
//         PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int[] in = read(br);
//         int N = in[0], M = in[1];
//         int INF = 0x3f3f3f3f;
//         List<Integer>[] adj = new ArrayList[N];
//         for (int i = 0; i < M; i++) {
//             int[] t = read(br);
//             int x = t[0]-1, y = t[1]-1;
//             if (adj[x] == null) {
//                 adj[x] = new ArrayList<>();
//             }
//             if (adj[y] == null) {
//                 adj[y] = new ArrayList<>();
//             }
//             adj[x].add(y); adj[y].add(x);
//         }
//         int K = read(br)[0];
//         int[] C = read(br);
//         for (int i = 0; i < K; i++) --C[i];
//         //K个关键点之间的最短距离
//         int[][] dis = new int[K][N];
//         Queue<Integer> queue = new LinkedList<>();
//         for (int i = 0; i < K; i++) {
//             Arrays.fill(dis[i], INF);
//             queue.clear();
//             queue.add(C[i]);
//             dis[i][C[i]] = 0;
//             while (!queue.isEmpty()) {
//                 int cur = queue.poll();
//                 if (adj[cur] == null) continue;
//                 for (Integer next : adj[cur]) {
//                     if (dis[i][next] == INF) {
//                         dis[i][next] = dis[i][cur] + 1;
//                         queue.add(next);
//                     }
//                 }
//             }
//         }
//         int[][] dp = new int[1<<K][K];
//         for (int i = 0; i < (1<<K); i++) {
//             Arrays.fill(dp[i], INF);
//         } 
//         for (int i = 0; i < K; i++) {
//             dp[1<<i][i] = 1;
//         }
//         //枚举所有状态递推
//         for (int mask = 0; mask < (1<<K); mask++) {
//             for (int i = 0; i < K; i++) {
//                 //C[i]被选取，C[j]未被选取
//                 if ((mask&(1<<i))==0) continue;
//                 for (int j = 0; j < K; j++) {
//                     if ((mask&(1<<j))==1 || dis[i][C[j]] == INF || dp[mask][i] == INF) continue;
//                     dp[mask|(1<<j)][j] = Math.min(dp[mask|(1<<j)][j], dp[mask][i] + dis[i][C[j]]);
//                 }
//             }
//         }
//         int res = INF;
//         for (int i = 0; i < K; i++) {
//             res = Math.min(res, dp[(1<<K)-1][i]);
//         }
//         if (res == INF) out.println(-1);  
//         else out.println(res);
//         out.flush();
//     }

//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }

public class E {
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}