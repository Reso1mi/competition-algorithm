import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int N = in[0], M = in[1], Q = in[2];
        int[][] sum = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            int[] t = read(br);
            for (int j = 1; j <= M; j++) {
                sum[i][j] = t[j-1] + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];
            }
        }
        for (int i = 0; i < Q; i++) {
            int[] q = read(br);
            int x1 = q[0], y1 = q[1];
            int x2 = q[2], y2 = q[3];
            out.println(sum[x2][y2]-(sum[x1-1][y2]+sum[x2][y1-1])+sum[x1-1][y1-1]);
        }
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}