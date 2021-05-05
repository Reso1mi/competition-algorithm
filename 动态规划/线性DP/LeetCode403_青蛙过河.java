import java.util.*;

class Solution {

    //官解
    public boolean canCross(int[] s) {
        int n = s.length;
        if (s[1] != 1) return false;
        //推论: 从i位置起跳的跳跃距离不会超过i+1
        //一共n个石头，每次跳跃最多使跳跃步长+1，初始步长为0，所以到第i个位置的石头，跳跃距离最多增加i+1
        //dp[i][j]: 能否到达j位置，且上一跳步长为i
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;
        for (int i = 1; i < n; i++) {
            // 枚举上一个落脚点
            for (int j = i-1; j >= 0; j--) {
                int k = s[i]-s[j];
                if (k > j+1) {
                    break;
                }
                dp[i][k] = dp[j][k-1] || dp[j][k] || dp[j][k+1];
                if (dp[n-1][k]) return true;
            }
        }
        return false;
    }

    //自己想的解法，没想出上面的推论，转换了一下思路
    public boolean canCross2(int[] s) {
        int n = s.length;
        if (s[1] != 1) return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(s[i], i);
        }
        //dp[i][j]: 上一跳在第i个石头，能否到达第j个石头
        boolean[][] dp = new boolean[n][n];
        dp[0][1] = true;
        for (int i = 1; i < n; i++) {
            // 枚举上一个落脚点
            for (int j = i-1; j >= 0; j--) {
                int k = s[i]-s[j];
                if (dp[j][i]) {
                    // 枚举下一跳的落脚点
                    Integer a = map.get(s[i]+k-1), b = map.get(s[i]+k), c = map.get(s[i]+k+1);
                    if (a != null) dp[i][a] =  true;
                    if (b != null) dp[i][b] =  true;
                    if (c != null) dp[i][c] =  true;
                }
            }
            if (dp[i][n-1]) return true;
        }
        return false;
    }
}