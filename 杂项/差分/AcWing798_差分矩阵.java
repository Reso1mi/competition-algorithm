import java.util.*;
import java.io.*;

class Main {

    static int[][] diff;
    static int n, m, q;
    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        n = in[0]; m = in[1]; q = in[2];
        diff = new int[n+2][m+2];
        for (int i = 1; i <= n; i++) {
            int[] t = read(br);
            for (int j = 1; j <= m; j++) {
                incr(i, j, i, j, t[j-1]);
            }
        }
        for (int i = 0; i < q; i++) {
            int[] q = read(br);
            incr(q[0], q[1], q[2], q[3], q[4]);
        }
        int[][] matrix = new int[n+2][m+2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                matrix[i][j] = diff[i][j] + matrix[i-1][j] + matrix[i][j-1] - matrix[i-1][j-1];
                out.print(matrix[i][j] + " ");
            }
            out.println();
        }
        out.flush();
    }

    public static void incr(int x1, int y1, int x2, int y2, int c) {
        diff[x1][y1] += c;
        diff[x1][y2+1] -= c;
        diff[x2+1][y1] -= c;
        diff[x2+1][y2+1] += c;
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}