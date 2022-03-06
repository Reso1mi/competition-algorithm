import java.util.*;
import java.io.*;

class Main {

    static int INF = 0x3f3f3f3f;
    static int [] price;
    static int N, M;
    static int idx;
    static int[] e, ne, h1, h2;
    public static void add(int[] h, int a, int b) {
        e[idx] = b; ne[idx] = h[a];
        h[a] = idx++;
    }

    public static void main(String... args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] in = read(br);
        N = in[0]; M = in[1];
        h1 = new int[N+1]; h2 = new int[N+1];
        Arrays.fill(h1, -1); Arrays.fill(h2, -1);
        e = new int[(M+1)*4]; ne = new int[(M+1)*4];
        int[] pMax = new int[N+1];
        int[] pMin = new int[N+1];
        //i-1
        price = read(br);

        for (int i = 0; i < M; i++) {
            int[] t = read(br);
            int a = t[0], b = t[1];
            add(h1, a, b); add(h2, b, a);
            if (t[2] == 2) {
                add(h1, b, a); add(h2, a, b);
            }
        }

        spfa(h1, pMin, 1);
        spfa(h2, pMax, N);

        int res = 0;
        for (int i = 1; i <= N; i++) {
            res = Math.max(res, pMax[i] - pMin[i]);
        }
        System.out.println(res);        
    }


    public static void spfa(int[] h, int[] dp, int s) {
        boolean[] vis = new boolean[N+1];
        boolean flag = s == 1;
        Arrays.fill(dp, flag ? INF : -INF);
        Queue<Integer> que = new LinkedList<>();
        que.add(s);
        dp[s] = price[s-1];
        vis[s] = true;
        while (!que.isEmpty()) {
            int i = que.poll();
            vis[i] = false;
            for (int j = h[i]; j != -1; j = ne[j]) {
                int k = e[j];
                if (flag) {
                    if (dp[k] > Math.min(dp[i], price[k-1])) {
                        dp[k] = Math.min(dp[i], price[k-1]);
                        if (!vis[k]) {
                            que.add(k);
                            vis[k] = true;
                        }
                    }
                } else {
                    if (dp[k] < Math.max(dp[i], price[k-1])) {
                        dp[k] = Math.max(dp[i], price[k-1]);
                        if (!vis[k]) {
                            que.add(k);
                            vis[k] = true;
                        }
                    }
                }
            }
        }
    }

    public static int[] read(BufferedReader reader) throws Exception {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

}


public class AcWing341_最优贸易 {
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./input.txt"));
        new Main().main();   
    }
}