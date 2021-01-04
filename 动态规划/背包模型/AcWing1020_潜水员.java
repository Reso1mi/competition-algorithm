/*
潜水员为了潜水要使用特殊的装备。

他有一个带2种气体的气缸：一个为氧气，一个为氮气。

让潜水员下潜的深度需要各种数量的氧和氮。

潜水员有一定数量的气缸。

每个气缸都有重量和气体容量。

潜水员为了完成他的工作需要特定数量的氧和氮。

他完成工作所需气缸的总重的最低限度的是多少？

例如：潜水员有5个气缸。每行三个数字为：氧，氮的（升）量和气缸的重量：

3 36 120

10 25 129

5 50 250

1 45 130

4 20 119
如果潜水员需要5升的氧和60升的氮则总重最小为249（1，2或者4，5号气缸）。

你的任务就是计算潜水员为了完成他的工作需要的气缸的重量的最低值。

输入格式
第一行有2个整数 m，n。它们表示氧，氮各自需要的量。

第二行为整数 k 表示气缸的个数。

此后的 k 行，每行包括ai，bi，ci，3个整数。这些各自是：第 i 个气缸里的氧和氮的容量及气缸重量。

输出格式
仅一行包含一个整数，为潜水员完成工作所需的气缸的重量总和的最低值。

数据范围
1≤m≤21,
1≤n≤79,
1≤k≤1000,
1≤ai≤21,
1≤bi≤79,
1≤ci≤800
输入样例：
5 60
5
3 36 120
10 25 129
5 50 250
1 45 130
4 20 119
输出样例：
249
 */
import java.util.*;
import java.io.*;

public class AcWing1020_潜水员 {
    public static void main(String[] args) throws Exception {
        new Main().main();
    }
}

class Main {
    
    //dp[i][j] = Math.min(dp[i][j], dp[i-y][j-d]+w);
    public static void main(String... args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int INF = 0x3f3f3f3f;
        int[] in = read(br);
        int Y = in[0], D = in[1], N = Integer.parseInt(br.readLine());
        //dp[i][j]: 氧气至少i，氮气至少j，需要的最小重量
        int[][] dp = new int[Y+1][D+1];
        for (int i = 0; i <= Y; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;
        for (int i = 0; i < N; i++) {
            int[] ydw = read(br);
            int yi = ydw[0], di = ydw[1], wi = ydw[2];
            for (int j = Y; j >= 0; j--) {
                for (int k = D; k >= 0; k--) {
                    // if (j < yi || k < di) {
                    //     dp[j][k] = Math.min(dp[j][k], wi);
                    // } else {
                    //     dp[j][k] = Math.min(dp[j][k], dp[j-yi][k-di] + wi);
                    // }
                    // 上面的递推缺乏考虑，如果氧气和氮气只有一种超出了范围，另一维的状态不应该跟着按照0算
                    dp[j][k] = Math.min(dp[j][k], dp[Math.max(j-yi, 0)][Math.max(k-di, 0)] + wi);
                }
            }
        }
        System.out.println(dp[Y][D]);
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}