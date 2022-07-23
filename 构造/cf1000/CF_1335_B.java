// https://codeforces.com/problemset/problem/1335/B


import java.util.*;
import java.io.*;

public class CF_1335_B {
    public static void main(String[] args) throws Exception{
        // 输入重定向
        if (args.length > 0 && "Resolmi_DEBUG".equals(args[0])) {
            System.setIn(new FileInputStream("./input.txt"));
        }
        new Main().main(args);
    }
}

class Main {

    // 1,2,3...b,1,2,3...b,
    // 每个子串恰好包含b个不同字符
    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = read(br)[0];
        while (T-- > 0) {
            int[] in = read(br);
            int n = in[0], a = in[1], b = in[2];

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append((char)(i % b + 'a'));
            }

            out.println(sb);
        }
        out.flush();
    }


    // public static void main(String... args) throws Exception {
    //     PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //     int T = read(br)[0];
    //     while (T-- > 0) {
    //         int[] in = read(br);
    //         int n = in[0], a = in[1], b = in[2];
            
    //         StringBuilder sb= new StringBuilder();
    //         char ch = 'a';
    //         for (int i = 0; i < b; i++) {
    //             sb.append(ch++);
    //         }
            
    //         int idx = 0;
    //         for (int i = 0; i < a-b; i++) {
    //             sb.append(sb.charAt(idx++ % b));
    //         }
    //         StringBuilder res = new StringBuilder();
            
    //         idx = 0;
    //         for (int i = 0; i < n; i++) {
    //             res.append(sb.charAt(idx++ % a));
    //         }
    //         out.println(res);
    //     }
    //     out.flush();
    // }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// 7 5 3 abcab abcab  --> a-b
// 5 2 2 ababa
// 7 5 4 abcda abcda