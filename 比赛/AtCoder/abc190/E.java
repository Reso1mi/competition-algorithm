import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int N = in[0], M = in[1];
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
            
        int[] w = read(br);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < K; i++) map.put(w[i], i);
        Queue<int[]> queue = new LinkedList<>();
        boolean[] vis = new boolean[N+1];

        queue.add(new int[]{w[0], 1});
        vis[w[0]] = true;
        //选取mask点，并且以j结尾的最短序列长度
        int[][] dp = new int[1<<K][N+1];
        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            for (Integer next : adj[top[0]]) {
                if (map.containsKey(next)) {
                    int x = map.get(next);
                    dp[top[1]|(1<<x)][next] = Math.min(dp[top[1]|(1<<x)][next], dp[top[1]][top[0]] + 1);
                    if (!vis[next]) {
                        queue.add(new int[]{next, top[1]|(1<<x)});
                    }
                } else {
                    dp[top[1]][next] = Math.min(dp[top[1]][next], dp[top[1]][top[0]] + 1);
                    if (!vis[next]) {
                        queue.add(new int[]{next, top[1]});
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            res = Math.min(res, dp[(1<<K)-1][i]);
        }
        out.println(res);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class E {
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}