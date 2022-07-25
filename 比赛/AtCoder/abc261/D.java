import java.util.*;
import java.io.*;

class Main {

    /*
        6 3
        2 7 1 8 2 8
        2 10
        3 1
        5 5
    */
    // 2500 0000
    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] in = read(br);
        int N = in[0], M = in[1];
        int[] X = read(br);
        int[] C = new int[N+1];
        for (int i = 0; i < M; i++) {
            int[] t = read(br);
            C[t[0]] = t[1];
        }

        // 投掷i次硬币，cnt为j的最大收益
        long[][] dp = new long[N+1][N+1];
        long lastMax = 0;
        for(int i = 1; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = lastMax;
                    lastMax = 0;
                } else {
                    dp[i][j] = dp[i-1][j-1] + C[j] + X[i-1];
                }
                lastMax = Math.max(dp[i][j], lastMax);
            }
        }
        long max = 0;
        for (int i = 0; i <= N; i++) {
            max = Math.max(max, dp[N][i]);
        }
        out.println(max);
        out.flush();
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// AtCoder/AcWing 提交上面部分即可，CF需要将JavaMain移到上面然后提交
public class D {
    public static void main(String[] args) throws Exception{
        // 输入重定向，通过jvm参数判断环境
        if (args.length > 0 && "Resolmi_DEBUG".equals(args[0])) {
            System.setIn(new FileInputStream("./input.txt"));
        }
        new Main().main(args);
    }
}