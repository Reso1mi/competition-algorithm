import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int N = in[0], X = in[1];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int[] t = read(br);
            sum += t[0] * t[1];
            if (sum > X*100) {
                System.out.println(i+1);
                return;
            }
        }
        System.out.println(-1);
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class B {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}