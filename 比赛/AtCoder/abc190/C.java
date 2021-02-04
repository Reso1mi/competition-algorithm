import java.util.*;
import java.io.*;

class Main {

    //2^16*100 = 1024 * 100 * 100 = 1000 0000
    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int N = in[0], M = in[1];
        int[][] cond = new int[M][2];
        //注意输入都是从1开始的
        for (int i = 0; i < M; i++) {
            int[] t = read(br);
            cond[i] = new int[]{t[0]-1, t[1]-1};
        }
        int K = read(br)[0];
        int[][] kn = new int[K][2];
        for (int i = 0; i < K; i++) {
            int[] t = read(br);
            kn[i] = new int[]{t[0]-1, t[1]-1};
        }
        int res = 0;
        //000 001 011
        for (int i = 0; i < (1<<K); i++) {
            int[] dish = new int[N];
            for (int j = 0; j < K; j++) {
                //这里不用考虑无符号右移
                dish[kn[K-1-j][(i>>j)&1]]++;
            }
            int cnt = 0;
            for (int j = 0; j < M; j++) {
                if (dish[cond[j][0]] >= 1 && dish[cond[j][1]] >= 1) {
                    cnt++;
                }
            }
            res = Math.max(res, cnt);
        }
        out.println(res);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// class Main {

//     static int[] dish;
//     static int[][] w;
//     static int[][] kn;
//     static int K;
//     static int N, M;
//     static int res = 0;
//     //2^16*100 = 1024 * 100 * 100 = 1000 0000
//     public static void main(String... args) throws Exception {
//         PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int[] in = read(br);
//         N = in[0]; M = in[1];
//         dish = new int[N];
//         w = new int[M][2];
//         for (int i = 0; i < M; i++) {
//             int[] t = read(br);
//             w[i][0] = t[0]; w[i][1] = t[1];
//         }
//         K = read(br)[0];
//         kn = new int[K][2];
//         for (int i = 0; i < K; i++) {
//             int[] t = read(br);
//             kn[i][0] = t[0]; kn[i][1] = t[1];
//         }
//         dfs(0);
//         out.println(res);
//         out.flush();
//     }

//     public static void dfs(int i) {
//         if (i == K) {
//             res = Math.max(res, check());
//             return;
//         }
//         dish[kn[i][0]-1]++;
//         dfs(i+1);
//         dish[kn[i][0]-1]--;

//         dish[kn[i][1]-1]++;
//         dfs(i+1);
//         dish[kn[i][1]-1]--;
//     }

//     public static int check() {
//         int cnt = 0;
//         for (int i = 0; i < M; i++) {
//             if (dish[w[i][0]-1] >= 1 && dish[w[i][1]-1] >= 1) {
//                 cnt++;
//             }
//         }
//         return cnt;
//     }

//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }

public class C {
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}