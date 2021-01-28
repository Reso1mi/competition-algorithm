import java.util.*;
import java.io.*;

class Main {

    public static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int N = readOne(br);
        int[] x = new int[N];
        int[] y = new int[N];
        for (int i = 0; i < N; i++) {
            int[] t = read(br);
            x[i] = t[0]; y[i] = t[1];
        }
        int M = readOne(br);
        //将操作叠加起来
        //1.(x, y) --> (y, -x)
        //2.(x, y) --> (-y, x)
        //3.(x, y) --> (-x+2p, y)
        //4.(x, y) --> (x, -y+2p)
        boolean[] swap = new boolean[M+1];
        long[] addx = new long[M+1];
        long[] addy = new long[M+1];
        long[] mulx = new long[M+1];
        long[] muly = new long[M+1];
        mulx[0] = muly[0] = 1;
        for (int i = 1; i <= M; i++) {
            int[] t = read(br);
            if (t[0] == 1) {
                swap[i] = !swap[i-1];
                mulx[i] = muly[i-1]; muly[i] = -mulx[i-1];
                addx[i] = addy[i-1]; addy[i] = -addx[i-1];
            } else if (t[0] == 2) {
                swap[i] = !swap[i-1];
                mulx[i] = -muly[i-1]; muly[i] = mulx[i-1];
                addx[i] = -addy[i-1]; addy[i] = addx[i-1];
            } else if (t[0] == 3) {
                swap[i] = swap[i-1];
                mulx[i] = -mulx[i-1]; muly[i] = muly[i-1];
                addx[i] = 2l*t[1] - addx[i-1]; addy[i] = addy[i-1];
            } else if (t[0] == 4) {
                swap[i] = swap[i-1];
                mulx[i] = mulx[i-1]; muly[i] = -muly[i-1];
                addx[i] = addx[i-1]; addy[i] = 2l*t[1] - addy[i-1];
            }
        }
        int Q = readOne(br);
        for (int i = 0; i < Q; i++) {
            int[] t = read(br);
            int a = t[0], b = t[1]-1;
            if (!swap[a]) {
                out.println((x[b]*mulx[a] + addx[a]) + " " + (y[b]*muly[a] + addy[a]));
            } else {
                out.println((y[b]*mulx[a] + addx[a]) + " " + (x[b]*muly[a] + addy[a]));
            }
        }
        out.flush();
        out.close();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static int readOne(BufferedReader br) throws Exception {
        return Integer.parseInt(br.readLine());
    }
}

// class Main {

//     public static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

//     public static void main(String... args) throws Exception {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int N = readOne(br);
//         int[] x = new int[N];
//         int[] y = new int[N];
//         for (int i = 0; i < N; i++) {
//             String[] t = br.readLine().split(" ");
//             x[i] = Integer.parseInt(t[0]); y[i] = Integer.parseInt(t[1]);
//         }
//         int M = readOne(br);
//         //将操作叠加起来
//         //1.(x, y) --> (y, -x)
//         //2.(x, y) --> (-y, x)
//         //3.(x, y) --> (-x+2p, y)
//         //4.(x, y) --> (x, -y+2p)
//         boolean[] swap = new boolean[M+1];
//         long[] addx = new long[M+1];
//         long[] addy = new long[M+1];
//         long[] mulx = new long[M+1];
//         long[] muly = new long[M+1];
//         mulx[0] = muly[0] = 1;
//         for (int i = 1; i <= M; i++) {
//             String[] s = br.readLine().split(" ");
//             int t = Integer.parseInt(s[0]);
//             if (t == 1) {
//                 swap[i] = !swap[i-1];
//                 mulx[i] = muly[i-1]; muly[i] = -mulx[i-1];
//                 addx[i] = addy[i-1]; addy[i] = -addx[i-1];
//             } else if (t == 2) {
//                 swap[i] = !swap[i-1];
//                 mulx[i] = -muly[i-1]; muly[i] = mulx[i-1];
//                 addx[i] = -addy[i-1]; addy[i] = addx[i-1];
//             } else if (t == 3) {
//                 int p = Integer.parseInt(s[1]);
//                 swap[i] = swap[i-1];
//                 mulx[i] = -mulx[i-1]; muly[i] = muly[i-1];
//                 addx[i] = 2l*p - addx[i-1]; addy[i] = addy[i-1];
//             } else if (t == 4) {
//                 int p = Integer.parseInt(s[1]);
//                 swap[i] = swap[i-1];
//                 mulx[i] = mulx[i-1]; muly[i] = -muly[i-1];
//                 addx[i] = addx[i-1]; addy[i] = 2l*p - addy[i-1];
//             }
//         }
//         int Q = readOne(br);
//         for (int i = 0; i < Q; i++) {
//             String[] t = br.readLine().split(" ");
//             int a = Integer.parseInt(t[0]) , b = Integer.parseInt(t[1])-1;
//             if (!swap[a]) {
//                 out.println((x[b]*mulx[a] + addx[a]) + " " + (y[b]*muly[a] + addy[a]));
//                 // out.printf("%d %d\n", x[b]*mulx[a] + addx[a], y[b]*muly[a] + addy[a]);
//             } else {
//                 out.println((y[b]*mulx[a] + addx[a]) + " " + (x[b]*muly[a] + addy[a]));
//                 // out.printf("%d %d\n", y[b]*mulx[a] + addx[a], x[b]*muly[a] + addy[a]);
//             }
//         }
//         out.flush();
//         out.close();
//         br.close();
//     }

//     public static int readOne(BufferedReader br) throws Exception {
//         return Integer.parseInt(br.readLine());
//     }
// }

// class Main {

//     public static void main(String... args) throws Exception {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int N = readOne(br);
//         int[][] B = new int[N][2];
//         for (int i = 0; i < N; i++) {
//             int[] t =read(br);
//             B[i][0] = t[0]; B[i][1] = t[1];
//         }
//         int M = readOne(br);
//         //将操作叠加起来
//         //1.(x, y) --> (y, -x)
//         //2.(x, y) --> (-y, x)
//         //3.(x, y) --> (-x+2p, y)
//         //4.(x, y) --> (x, -y+2p)
//         Operation[] op = new Operation[M+1];
//         op[0] = new Operation();
//         op[0].muly = op[0].mulx = 1;
//         for (int i = 1; i <= M; i++) {
//             op[i] = new Operation();
//             int[] t = read(br);
//             if (t[0] == 1) {
//                 op[i].swap = !op[i-1].swap;
//                 op[i].mulx = op[i-1].muly; op[i].muly = -op[i-1].mulx;
//                 op[i].addx = op[i-1].addy; op[i].addy = -op[i-1].addx;
//             } else if (t[0] == 2) {
//                 op[i].swap = !op[i-1].swap;
//                 op[i].mulx = -op[i-1].muly; op[i].muly = op[i-1].mulx;
//                 op[i].addx = -op[i-1].addy; op[i].addy = op[i-1].addx;
//             } else if (t[0] == 3) {
//                 op[i].swap = op[i-1].swap;
//                 op[i].mulx = -op[i-1].mulx; op[i].muly = op[i-1].muly;
//                 op[i].addx = 2l*t[1] - op[i-1].addx; op[i].addy = op[i-1].addy;
//             } else if (t[0] == 4) {
//                 op[i].swap = op[i-1].swap;
//                 op[i].mulx = op[i-1].mulx; op[i].muly = -op[i-1].muly;
//                 op[i].addx = op[i-1].addx; op[i].addy = 2l*t[1] - op[i-1].addy;
//             }
//         }
//         int Q = readOne(br);
//         for (int i = 0; i < Q; i++) {
//             int[] ab = read(br);
//             if (!op[ab[0]].swap) {
//                 int x = B[ab[1]-1][0], y = B[ab[1]-1][1];
//                 System.out.println((x*op[ab[0]].mulx + op[ab[0]].addx) + " " + (y*op[ab[0]].muly + op[ab[0]].addy));
//                 // System.out.printf("%d %d\n", x*op[ab[0]].mulx + op[ab[0]].addx, y*op[ab[0]].muly + op[ab[0]].addy);
//             } else {
//                 int x = B[ab[1]-1][0], y = B[ab[1]-1][1];
//                 System.out.println((y*op[ab[0]].mulx + op[ab[0]].addx) + " " + (x*op[ab[0]].muly + op[ab[0]].addy));
//                 // System.out.printf("%d %d\n", y*op[ab[0]].mulx + op[ab[0]].addx, x*op[ab[0]].muly + op[ab[0]].addy);
//             }
//         }
//     }

//     static class Operation {
//         boolean swap;
//         long mulx, muly;
//         long addx, addy;
//     }


//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }

//     public static int readOne(BufferedReader br) throws Exception {
//         return Integer.parseInt(br.readLine());
//     }
// }

public class E {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}