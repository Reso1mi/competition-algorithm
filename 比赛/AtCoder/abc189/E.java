import java.util.*;
import java.io.*;

class Main {

    static int M;
    static Op[] ops;

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int N = read(br)[0];
        int[][] B = new int[N][2];
        for (int i = 0; i < N; i++) {
            int[] t =read(br);
            B[i][0] = t[0]; B[i][1] = t[1];
        }
        M = read(br)[0];
        ops = new Op[M];
        for (int i = 0; i < M; i++) {
            ops[i] = read(br);
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            int[] query = read(br);
        }
        System.out.println(res);
    }

    public static int[] dfs (int x, int y) {
        for (int i = 0; i < M; i++) {
            if (ops[i] == 1) {
                
            }
        }
    }

    static class Op {
        int t, p;
        public Op (int[] in) {
            this.t = in[0];
            if (in.length == 2) {
                this.p = in[1];
            }
        }
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class E {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}