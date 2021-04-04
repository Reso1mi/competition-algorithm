import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int MAX = 5010;
        int N = in[0], R = in[1];
        int[][] w = new int[MAX][MAX];
        for (int i = 0; i < N; i++) {
            int[] t = read(br);
            w[t[0]][t[1]] += t[2];
        }
        int[][] sum = new int[MAX][MAX];
        for (int i = 1; i < MAX; i++) {
            for (int j = 1; j < MAX; j++) {
                sum[i][j] = w[i-1][j-1] + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];
            }
        }
        int res = 0;
        for (int i = 1; i < MAX; i++) {
            if (i+R-1 >= MAX) break;
            for (int j = 1; j < MAX; j++) {
                if (j+R-1 >= MAX) break;
                int i2 = i+R-1, j2 = j+R-1;
                res = Math.max(res, sum[i2][j2]-sum[i-1][j2]-sum[i2][j-1]+sum[i-1][j-1]);
            }
        }
        out.println(res);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}