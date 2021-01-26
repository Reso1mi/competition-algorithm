import java.util.*;
import java.io.*;


class Main {
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        //前i个元素，yi为F和T
        long[][] dp = new long[N+1][2];
        dp[0][0] = dp[0][1] = 1;
        for (int i = 1; i <= N; i++) {
            String op = sc.next();
            if ("AND".equals(op)) {
                dp[i][1] = dp[i-1][1];
                //yi=F      (       xi=F        )    (xi=T yi-1=F)
                dp[i][0] = dp[i-1][1] + dp[i-1][0] + dp[i-1][0];
            } else {
                //or yi=T   (       xi=T        )    (  xi=F  )
                dp[i][1] = dp[i-1][0] + dp[i-1][1] + dp[i-1][1];
                dp[i][0] = dp[i-1][0];
            }
        }
        System.out.println(dp[N][1]);
    }
}

// class Main {
//     public static void main(String... args) {
//         Scanner sc = new Scanner(System.in);
//         int N = sc.nextInt();
//         //前i个元素，xi为F和T
//         long[][][] dp = new long[N+1][2][2];
//         dp[0][0][0] = dp[0][1][1] = 1;
//         //or(yi= xi || yi-1): dp[i][1][1]
//         //and(yi= xi & yi-1) : dp[i][0][1] + dp[i][1][0] + dp[i][1][0] + dp[i][1][1]
//         for (int i = 1; i <= N; i++) {
//             String op = sc.next();
//             if ("AND".equals(op)) {
//                 //xi=T yi=T
//                 dp[i][1][1] = dp[i-1][1][1] + dp[i-1][0][1];
//                 //xi=T yi=F 
//                 dp[i][1][0] = dp[i-1][1][0] + dp[i-1][0][0];
//                 //xi=F yi=F
//                 dp[i][0][0] = dp[i-1][1][0] + dp[i-1][0][1] + dp[i-1][1][1] + dp[i-1][0][0];
//                 //xi=F yi=T
//                 //dp[i][0][1] = 0; 
//             } else {
//                 //xi=T yi=T
//                 dp[i][1][1] = dp[i-1][1][0] + dp[i-1][0][1] + dp[i-1][1][1] + dp[i-1][0][0];
//                 //xi=F yi=T
//                 dp[i][0][1] = dp[i-1][0][1] + dp[i-1][1][1];
//                 //xi=F yi=F
//                 dp[i][0][0] = dp[i-1][0][0] + dp[i-1][1][0];
//                 //xi=T yi=F
//                 // dp[i][1][0] = 0;
//             }
//         }
//         System.out.println(dp[N][0][1]+dp[N][1][1]);
//     }
// }

// class Main {

//     static int N;
//     static int[] w;

//     static HashMap<String, Long> dp = new HashMap<>();

//     public static void main(String... args) throws Exception {
//         // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         Scanner sc = new Scanner(System.in);
//         N = sc.nextInt();
//         int res = 0;
//         w = new int[N];
//         for (int i = 0; i < N; i++) {
//             w[i] = "AND".equals(sc.next()) ? 1 : 0;
//         }
//         System.out.println(dfs(-1, true, true));
//     }

//     public static long dfs(int i, boolean x, boolean lasty) {
//         if (i == N) {
//             return lasty?1:0;
//         }
//         if (dp.containsKey(i+""+x+""+lasty)) {
//             return dp.get(i+""+x+""+lasty);
//         }
//         if (i == -1) {
//             long cnt = dfs(i+1, x, x) + dfs(i+1, !x, !x);
//             dp.put(i+""+x+""+lasty, cnt);
//             return cnt;
//         }
//         long cnt = dfs(i+1, x, op(w[i], x, lasty)) + dfs(i+1, !x, op(w[i], !x, lasty));
//         dp.put(i+""+x+""+lasty, cnt);
//         return cnt;
//     }

//     public static boolean op (int op, boolean x, boolean y) {
//         if (op == 1) {
//             return x && y;
//         }
//         return x || y;
//     }

//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }


public class D {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}