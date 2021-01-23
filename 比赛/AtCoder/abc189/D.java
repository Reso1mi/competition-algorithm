import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int[] w;

    static HashMap<String, Long> dp = new HashMap<>();

    public static void main(String... args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int res = 0;
        w = new int[N];
        for (int i = 0; i < N; i++) {
            w[i] = "AND".equals(sc.next()) ? 1 : 0;
        }
        System.out.println(dfs(-1, true, true));
    }

    public static long dfs(int i, boolean x, boolean lasty) {
        if (i == N) {
            return lasty?1:0;
        }
        if (dp.containsKey(i+""+x+""+lasty)) {
            return dp.get(i+""+x+""+lasty);
        }
        if (i == -1) {
            long cnt = dfs(i+1, x, x) + dfs(i+1, !x, !x);
            dp.put(i+""+x+""+lasty, cnt);
            return cnt;
        }
        long cnt = dfs(i+1, x, op(w[i], x, lasty)) + dfs(i+1, !x, op(w[i], !x, lasty));
        dp.put(i+""+x+""+lasty, cnt);
        return cnt;
    }

    public static boolean op (int op, boolean x, boolean y) {
        if (op == 1) {
            return x && y;
        }
        return x || y;
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}


public class D {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}