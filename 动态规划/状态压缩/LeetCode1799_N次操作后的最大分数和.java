import java.util.*;

class Solution {

    public int maxScore(int[] nums) {
        int len = nums.length;
        int n = len/2;
        int[] dp = new int[2<<len];
        int[][] cache = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                cache[i][j] = gcd(nums[i], nums[j]);
            }
        }
        for (int s = 0; s < (1<<len); s++) {
            int cnt = countBit(s);
            // 选取个数一定是偶数
            if ((cnt&1)==1) continue;
            for (int j = 0; j < len; j++) {
                //s必须选取j, k
                if (((s>>>j)&1)==0) continue;
                for (int k = j+1; k < len; k++) {
                    if (((s>>>k)&1)==0) continue;
                    dp[s] = Math.max(dp[s], (countBit(s)/2)*cache[j][k] + dp[s^(1<<j)^(1<<k)]);
                }
            }
        }
        return dp[(1<<len)-1];
    }

    public int maxScore2(int[] nums) {
        int len = nums.length;
        int n = len/2;
        int[] dp = new int[2<<len];
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                dp[(1<<i)|(1<<j)] = gcd(nums[i], nums[j]);
            }
        }
        //2^len
        for (int s = 0; s < (1<<len); s++) {
            //2^k k是s中1的个数
            for (int i = s; i != 0; i=(i-1)&s) {
                if (countBit(s)-countBit(i)==2) {
                    dp[s] = Math.max(dp[s], countBit(s)/2*dp[s^i]);
                }
            }
        }
        return dp[(1<<len)-1];
    }

    public int countBit(int a) {
        int cnt = 0;
        while (a != 0) {
            a = a&(a-1);
            cnt++;
        }
        return cnt;
    }

    public int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b, a%b);
    }
}