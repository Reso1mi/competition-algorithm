/*
总公司拥有M台 相同 的高效设备，准备分给下属的N个分公司。

各分公司若获得这些设备，可以为国家提供一定的盈利。盈利与分配的设备数量有关。

问：如何分配这M台设备才能使国家得到的盈利最大？

求出最大盈利值。

分配原则：每个公司有权获得任意数目的设备，但总台数不超过设备数M。

输入格式
第一行有两个数，第一个数是分公司数N，第二个数是设备台数M；

接下来是一个N*M的矩阵，矩阵中的第 i 行第 j 列的整数表示第 i 个公司分配 j 台机器时的盈利。

输出格式
第一行输出最大盈利值；

接下N行，每行有2个数，即分公司编号和该分公司获得设备台数。

答案不唯一，输入任意合法方案即可。

数据范围
1≤N≤10,
1≤M≤15
输入样例：
3 3
30 40 50
20 30 50
20 25 30
输出样例：
70
1 1
2 1
3 1
 */
import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int N = in[0], M = in[1];
        int[] dp = new int[M+1];
        int[][] back = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            int[] w = read(br);
            for (int j = M; j >= 1; j--) {
                for (int k = 1; k <= j; k++) {
                    int temp = dp[j-k]+w[k-1];
                    if (temp > dp[j]) {
                        //记录当前子公司最优分配多少个，然后从后往前推
                        back[i][j] = k;
                        dp[j] = temp;
                    }
                }
            }
        }
        int[] res = new int[N];
        int idx = 0;
        int x = N, y = M;
        while(idx < N) {
            int k = back[x][y];
            res[idx++] = k;
            x--; y-=k;
        }
        System.out.println(dp[M]);
        for (int i = N-1; i >= 0; i--) {
            System.out.println((N-i)+ " " + res[i]);
        }
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class AcWing1013_机器分配 {
    public static void main(String[] args) throws Exception {
        new Main().main();
    }
}