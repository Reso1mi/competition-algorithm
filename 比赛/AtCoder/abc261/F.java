import java.util.*;
import java.io.*;

class Main {

    static int[] tree;
    
    static int len;
    
    public static int lowbit(int i){
        return i & -i;
    }

    public static void add(int i, int v) {
        while (i < len) {
            tree[i] += v;
            i += lowbit(i);
        }
    }

    public static long sum(int i) {
        long res = 0;
        while (i > 0) {
            res += tree[i];
            i -= lowbit(i);
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = read(br)[0];
        var C = read(br);
        var X = read(br);

        tree = new int[N+1];
        len = N;
        
        List<Integer>[] cls = new ArrayList[N+1];
        for (int i = 0; i < N; i++) {
            if (cls[C[i]] == null) {
                cls[C[i]] = new ArrayList<>();
            }
            cls[C[i]].add(X[i]);
        }

        long res = 0;
        for (int i = N-1; i >= 0; i--) {
            add(X[i], 1);
            res += sum(X[i]-1);
        }
        // 重置，统计相同气球逆序对
        tree = new int[N+1];

        // 注意从1开始，在这里WA了好几发
        for (int i = 1; i <= N; i++) {
            if (cls[i] == null) continue;
            int sz = cls[i].size();
            for (int j = sz-1; j >= 0; j--) {
                int c = cls[i].get(j);
                add(c, 1);
                res -= sum(c-1);
            }
            // 还原，用于下一组
            for (Integer c : cls[i]) {
                add(c, -1);
            }
        }

        out.println(res);
        out.flush();
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// class Main {

//     static int[] tree;
    
//     static int len;

//     static HashMap<Integer, Integer>[] map;

    
//     public static int lowbit(int i){
//         return i & -i;
//     }

//     public static void add(int i, int v, int c) {
//         while (i < len) {
//             tree[i] += v;
//             map[i].put(c, map[i].getOrDefault(c, 0) + 1);
//             i += lowbit(i);
//         }
//     }

//     public static long sum(int i) {
//         long res = 0;
//         while (i > 0) {
//             res += tree[i];
//             i -= lowbit(i);
//         }
//         return res;
//     }

//     public static long sumColor(int i, int c) {
//         long res = 0;
//         while (i > 0) {
//             res += map[i].getOrDefault(c, 0);
//             i -= lowbit(i);
//         }
//         return res;
//     }


//     public static void main(String[] args) throws Exception {
//         PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         int N = read(br)[0];
//         var C = read(br);
//         var X = read(br);

//         tree = new int[N+1];
//         len = N;
//         // map[i][c]，索引i位置放了多少个颜色c的气球
//         map = new HashMap[N];
//         for (int i = 0; i < N; i++) {
//             map[i] = new HashMap<>();
//         }

//         long res = 0;
//         for (int i = N-1; i >= 0; i--) {
//             add(X[i], 1, C[i]);
//             res += sum(X[i]-1) - sumColor(X[i]-1, C[i]);
//         }

//         out.println(res);
//         out.flush();
//     }


//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }

// AtCoder/AcWing 提交上面部分即可，CF需要将JavaMain移到上面然后提交
public class F {
    public static void main(String[] args) throws Exception{
        // 输入重定向，通过jvm参数判断环境
        if (args.length > 0 && "Resolmi_DEBUG".equals(args[0])) {
            System.setIn(new FileInputStream("./input.txt"));
        }
        new Main().main(args);
    }
}