import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int N = read(br);
        int[] diff = new int[N+2];
        for (int i = 1; i <= N; i++) {
            int t = read(br);
            diff[i] += t; diff[i+1] -= t;
        }
        long p = 0, q = 0;
        for (int i = 2; i <= N; i++) {
            if (diff[i] > 0) {
                p += diff[i];
            } else {
                q -= diff[i];
            }
        }
        out.println(Math.max(p, q));
        out.println(Math.abs(p-q)+1);
        out.flush();
    }

    public static int read(BufferedReader br) throws Exception {
        return Integer.valueOf(br.readLine());
    }
}