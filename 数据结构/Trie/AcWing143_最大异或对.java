import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int[][] son;
    static int idx;

    public static void insert(int a) {
        int cur = 0;
        for (int i = 31; i >= 0; i--) {
            int c = (a>>>i)&1;
            if (son[cur][c]==0) {
                son[cur][c] = ++idx;
            }
            cur = son[cur][c];
        }
    }

    public static int query(int a) {
        int cur = 0, tar = 0;
        for (int i = 31; i >= 0; i--) {
            int c = (a>>>i)&1;
            // 与c相异的存在
            if (son[cur][c^1] != 0) {
                tar = tar*2 + c^1;
                cur = son[cur][c^1];
            } else {
                tar = tar*2 + c;
                cur = son[cur][c];
            }
        }
        return a^tar;
    }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int N = read(br)[0];
        son = new int[31*N][2];
        int[] A = read(br);
        for (int i = 0; i < N; i++) insert(A[i]);
        int res = 0;
        for (int i = 0; i < N; i++) {
            res = Math.max(res, query(A[i]));
        }
        out.println(res);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}