/*
为了庆贺班级在校运动会上取得全校第一名成绩，班主任决定开一场庆功会，为此拨款购买奖品犒劳运动员。

期望拨款金额能购买最大价值的奖品，可以补充他们的精力和体力。

输入格式
第一行二个数n，m，其中n代表希望购买的奖品的种数，m表示拨款金额。

接下来n行，每行3个数，v、w、s，分别表示第I种奖品的价格、价值（价格与价值是不同的概念）和能购买的最大数量（买0件到s件均可）。

输出格式
一行：一个数，表示此次购买能获得的最大的价值（注意！不是价格）。

数据范围
n≤500,m≤6000,
v≤100,w≤1000,s≤10
输入样例：
5 1000
80 20 4
40 50 9
30 50 7
40 30 6
20 20 1
输出样例：
1040
 */
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] in = read(bf);
        int n = in[0], m = in[1];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int[] temp = read(bf);
            int v = temp[0], w = temp[1], s = temp[2];
            for (int r = 0; r < v; r++) {
                
            }
        }
        
    }

    public static int[] read(BufferedReader bf) throws Exception {
        return Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }


    //暴力解法，每个物品有k个数量的限制后，直接当成01背包做
    //dp[i][j] = Max(dp[i-1][j], dp[i-1][j-v[i]]+w[i], dp[i-1][j-2*v[i]]+2*w[i], ... dp[i-1][j-s[i]*v[i]] + s[i]*w[i]) 
    public static int solve (int m, int[] v, int[] w, int[] s) {
        int n = v.length;
        int[] dp = new int[m+1];
        for (int i = 0; i < n; i++) {
            //逆序避免覆盖
            for (int j = m; j >= v[i]; j--) {
                for (int k = 0; k <= s[i] && j >= k*v[i]; k++) {
                    dp[j] = Math.max(dp[j], dp[j-k*v[i]] + k*w[i]);
                }
            }
        }
        return dp[m];
    }
}