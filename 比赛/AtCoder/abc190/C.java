import java.util.*;
import java.io.*;

class Main {

    static int[] dish;
    static int[][] w;
    static int[][] kn;
    static int K;
    static int N, M;
    static int res = 0;
    //2^16*100 = 1024 * 100 * 100 = 1000 0000
    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        N = in[0]; M = in[1];
        dish = new int[N];
        w = new int[M][2];
        for (int i = 0; i < M; i++) {
            int[] t = read(br);
            w[i][0] = t[0]; w[i][1] = t[1];
        }
        K = read(br)[0];
        kn = new int[K][2];
        for (int i = 0; i < K; i++) {
            int[] t = read(br);
            kn[i][0] = t[0]; kn[i][1] = t[1];
        }
        dfs(0);
        out.println(res);
        out.flush();
    }

    public static void dfs(int i) {
        if (i == K) {
            res = Math.max(res, check());
            return;
        }
        dish[kn[i][0]-1]++;
        dfs(i+1);
        dish[kn[i][0]-1]--;

        dish[kn[i][1]-1]++;
        dfs(i+1);
        dish[kn[i][1]-1]--;
    }

    public static int check() {
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if (dish[w[i][0]-1] >= 1 && dish[w[i][1]-1] >= 1) {
                cnt++;
            }
        }
        return cnt;
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class C {
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}