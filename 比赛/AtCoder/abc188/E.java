import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int INF = 0x3f3f3f3f;
        int[] nm = read(br);
        int N = nm[0], M = nm[1];
        int[] w = read(br);
        List<Integer>[] adj = new ArrayList[N];
        for (int i = 0; i < M; i++) {
            int[] xy = read(br);
            int x = xy[0]-1, y = xy[1]-1;
            if (adj[x] == null) {
                adj[x] = new ArrayList<>();
            }
            adj[x].add(y);
        }
        int[] dp = new int[N];
        Arrays.fill(dp, INF);
        int res = -INF;
        //题目说了x<y，其实也就说明了只能从前向后走，所以直接顺序遍历是没问题的
        for (int i = 0; i < N; i++) {
            //更新当前节点的dp[i]
            dp[i] = Math.min(dp[i], w[i]);
            if (adj[i]==null) continue;
            for (Integer j : adj[i]) {
                res = Math.max(res, w[j] - dp[i]);
                //这里WA了好几发...这里dp[j]不只在这里计算一次，前面可能计算过了，所以需要对过往的dp[j]取min
                dp[j] = Math.min(dp[j], Math.min(w[j], dp[i]));
            }
        }
        System.out.println(res);
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// import java.util.*;
// import java.io.*;

// class Main {

//     public static void main(String... args) throws Exception {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int INF = 0x3f3f3f3f;
//         int[] nm = read(br);
//         int N = nm[0], M = nm[1];
//         int[] w = read(br);
//         //节点入度数
//         int[] in = new int[N];
//         List<Integer>[] adj = new ArrayList[N];
//         for (int i = 0; i < M; i++) {
//             int[] xy = read(br);
//             int x = xy[0]-1, y = xy[1]-1;
//             if (adj[x] == null) {
//                 adj[x] = new ArrayList<>();
//             }
//             adj[x].add(y);
//             in[y]++;
//         }
//         int[] dp = new int[N];
//         Arrays.fill(dp, INF);
//         Queue<Integer> queue = new LinkedList<>();
//         for (int i = 0; i < N; i++) {
//             if (in[i] == 0) {
//                 queue.add(i);
//                 dp[i] = w[i];
//             }
//         }
//         int res = -INF;
//         while (!queue.isEmpty()) {
//             int i = queue.poll();
//             if (adj[i]==null) continue;
//             for (Integer j : adj[i]) {
//                 res = Math.max(res, w[j] - dp[i]);
//                 //这里WA了好几发...这里dp[j]不只在这里计算一次，前面可能计算过了，所以需要对过往的dp[j]取min
//                 dp[j] = Math.min(dp[j], Math.min(w[j], dp[i]));
//                 in[j]--;
//                 if (in[j] == 0) {
//                     queue.add(j);
//                 }
//             }
//         }
//         System.out.println(res);
//     }

//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }


// import java.util.*;
// import java.io.*;

// class Main {

//     public static void main(String... args) throws Exception {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int INF = 0x3f3f3f3f;
//         int[] nm = read(br);
//         int N = nm[0], M = nm[1];
//         int[] w = read(br);
//         //节点入度数
//         int[] in = new int[N];
//         HashMap<Integer, List<Integer>> map = new HashMap<>();
//         for (int i = 0; i < M; i++) {
//             int[] xy = read(br);
//             map.computeIfAbsent(xy[0]-1, k->new ArrayList<>()).add(xy[1]-1);
//             in[xy[1]-1]++;
//         }
//         int[] dp = new int[N];
//         Arrays.fill(dp, INF);
//         Queue<Integer> queue = new LinkedList<>();
//         for (int i = 0; i < N; i++) {
//             if (in[i] == 0 && map.containsKey(i)) {
//                 queue.add(i);
//                 dp[i] = w[i];
//             }
//         }
//         int res = -INF;
//         while (!queue.isEmpty()) {
//             int i = queue.poll();
//             if (!map.containsKey(i)) continue;
//             for (Integer j : map.get(i)) {
//                 res = Math.max(res, w[j] - dp[i]);
//                 //这里WA了好几发...这里dp[j]不只在这里计算一次，前面可能计算过了，所以需要对过往的dp[j]取min
//                 dp[j] = Math.min(dp[j], Math.min(w[j], dp[i]));
//                 in[j]--;
//                 if (in[j] == 0) {
//                     queue.add(j);
//                 }
//             }
//         }
//         System.out.println(res);
//     }

//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }

public class E {
    public static void main(String[] args) throws Exception{
        new Main().main();
    }
}