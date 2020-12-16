/*
在网友的国度中共有 n 种不同面额的货币，第 i 种货币的面额为 a[i]，你可以假设每一种货币都有无穷多张。

为了方便，我们把货币种数为 n、面额数组为 a[1..n] 的货币系统记作 (n,a)。 

在一个完善的货币系统中，每一个非负整数的金额 x 都应该可以被表示出，即对每一个非负整数 x，都存在 n 个非负整数 t[i] 满足 a[i]× t[i] 的和为 x。

然而，在网友的国度中，货币系统可能是不完善的，即可能存在金额 x 不能被该货币系统表示出。

例如在货币系统 n=3, a=[2,5,9] 中，金额 1,3 就无法被表示出来。 

两个货币系统 (n,a) 和 (m,b) 是等价的，当且仅当对于任意非负整数 x，它要么均可以被两个货币系统表出，要么不能被其中任何一个表出。 

现在网友们打算简化一下货币系统。

他们希望找到一个货币系统 (m,b)，满足 (m,b) 与原来的货币系统 (n,a) 等价，且 m 尽可能的小。

他们希望你来协助完成这个艰巨的任务：找到最小的 m。
1≤n≤100,
1≤a[i]≤25000,
1≤T≤20

输入样例：
2 
4 
3 19 10 6 
5 
11 29 13 19 17 
输出样例：
2
5
 */
import java.util.*;
import java.io.*;

public class AcWing531_货币系统 {
    public static void main(String... args) throws Exception {
        new Main().main();
    }
}
class Main {
    public static void main(String... args) throws Exception {
        Scanner sc = new Scanner(new File("./input.txt"));
        // Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int[] w = new int[n];
            for (int i = 0; i < n; i++) {
                w[i] = sc.nextInt();
            }
            System.out.println(solve(w));
        }
    }

    public static int solve(int[] w) {
        int n = w.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, w[i]);
        }
        int[] dp = new int[25001];
        dp[0] = 1;
        //完全背包求构成每个值的方案数
        for (int i = 0; i < n; i++) {
            for (int j = w[i]; j <= max; j++) {
                dp[j] += dp[j-w[i]];
            }
        }
        int res = 0;
        //方案数为1的就说明是不能丢掉的，统计一下就ok
        for (int i = 0; i < n; i++) {
            if (dp[w[i]] == 1) {
                res++;
            }
        }
        return res;
    }
}