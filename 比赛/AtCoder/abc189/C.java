import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int N = read(br)[0];
        int[] w = read(br);
        int res = 0;
        for (int i = 0; i < N; i++) {
            if (i > 0 && w[i] == w[i-1]) continue;
            int min = Integer.MAX_VALUE;
            for (int j = i; j < N; j++) {
                min = Math.min(w[j], min);
                res = Math.max(res, min*(j-i+1));
            }
        }
        System.out.println(res);
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class C {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}