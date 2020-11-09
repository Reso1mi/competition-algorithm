import java.io.*;
import java.util.*;
//AcWing 1017. 怪盗基德的滑翔翼 https://www.acwing.com/problem/content/1019/
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int[] w = new int[n];
            for (int i = 0; i < n; i++) {
                w[i] = sc.nextInt(); 
            }
            System.out.println(Math.max(solve(w, n), solve(reverse(w), n))); 
        }
    }

    //DP(N^2)的解法
    public static int solve(int[] w, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (w[j] > w[i]) {
                    dp[i] = Math.max(dp[j]+1, dp[i]);  
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res; 
    }

    //tail[i]: 长度为i的递减子序列最大结尾
    public static int solve2(int[] w, int n) {
        int[] tail = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int idx = search(tail, len, w[i]);
            if (idx == -1) {
                tail[len++] = w[i];
            } else {
                tail[idx] = w[i];
            }
        }
        return len;
    }

    //从左到右找第一个小于target的元素下标，替换它，使得结尾更大，长度更长
    public static int search(int[] tail, int len, int target){
        int left = 0, right = len-1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (tail[mid] <= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public static int[] reverse(int[] w) {
        for (int i = 0, j = w.length-1; i < j; i++, j--) {
            int temp = w[i];
            w[i] = w[j];
            w[j] = temp;
        }
        return w;
    }
}