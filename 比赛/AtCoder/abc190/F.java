import java.util.*;
import java.io.*;

class Main {

    static int[] tree;

    public static int lowbit(int x) {
        return x & -x;
    }

    //q[1001] = t[1001] + t[1000]
    public static int query(int i) {
        int res = 0;
        while (i > 0) {
            res += tree[i];
            i -= lowbit(i);
        }
        return res;
    }

    public static void add(int i, int val) {
        while (i < tree.length) {
            tree[i] += val;
            i += lowbit(i);
        }
    }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int N = read(br)[0];
        int[] A = read(br);
        tree = new int[N+1];
        //这题不用离散化，序列值就是Rank
        long res = 0;
        //树状数组或者归并都可
        for (int i = N-1; i >= 0; i--) {
            add(A[i]+1, 1);
            res += query(A[i]);
        }
        out.println(res);
        //-k+(N-1-k) = N-1-2*k
        for (int i = 0; i < N-1; i++) {
            res += N-1-2*A[i];
            out.println(res);
        }
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// 更加通用的解法，对于任意序列执行上述操作都OK
// class Main {

//     static int[] tree;

//     public static int lowbit(int x) {
//         return x & -x;
//     }

//     //q[1001] = t[1001] + t[1000]
//     public static int query(int i) {
//         int res = 0;
//         while (i > 0) {
//             res += tree[i];
//             i -= lowbit(i);
//         }
//         return res;
//     }

//     public static void add(int i, int val) {
//         while (i < tree.length) {
//             tree[i] += val;
//             i += lowbit(i);
//         }
//     }

//     public static void main(String... args) throws Exception {
//         PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int N = read(br)[0];
//         int[] A = read(br);
//         tree = new int[N+1];
//         //离散化（这题不用离散化，为了更加通用）
//         int[][] temp = new int[N][2];
//         for (int i = 0; i < N; i++) temp[i] = new int[]{A[i], i};
//         Arrays.sort(temp, (t1, t2)->t1[0]-t2[0]);
//         int[] rank = new int[N];
//         for (int i = 0; i < N; i++) {
//             rank[temp[i][1]] = i+1;
//         }
//         long res = 0;
//         //树状数组或者归并都可
//         for (int i = N-1; i >= 0; i--) {
//             add(rank[i], 1);
//             res += query(rank[i]-1);
//         }
//         out.println(res);
//         for (int i = 0; i < N-1; i++) {
//             res -= query(rank[i]-1);
//             res += query(N) - query(rank[i]);
//             out.println(res);
//         }
//         out.flush();
//     }

//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }

public class F {
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}