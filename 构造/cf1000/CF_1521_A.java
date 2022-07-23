// https://codeforces.com/problemset/problem/1521/A

// x+y = z --> A + AB = A(B+1)

import java.util.*;
import java.io.*;

public class CF_1521_A {
    public static void main(String[] args) throws Exception{
        // 输入重定向
        // System.setIn(new FileInputStream("./input.txt"));
        new Main().main();
    }
}

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = read(br)[0];

        while (T-- > 0) {
            int[] in = read(br);
            long a = (long)in[0], b = (long)in[1];
            if (b == 1) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
                // A + AB = A(B+1)
                System.out.println(a + " " + a*b + " " + a*(b+1));
            }
        }
        out.flush();
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

