import java.util.*;
import java.io.*;

/*
1703. 得到连续 K 个 1 的最少相邻交换次数
给你一个整数数组 nums 和一个整数 k 。 nums 仅包含 0 和 1 。每一次移动你可以选择 相邻 两个数字并将它们交换。
请你返回使 nums 中包含 k 个 连续 1 的 最少 交换次数。
 */

class Solution {
    public int minMoves(int[] nums, int k) {
        int n = nums.length;
        // int[] w = new int[k];
        List<Integer> lis = new ArrayList<>();
        int x = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i]==1) {
                lis.add(i-x);
                x++;
            }
        }
        int[] w = lis.stream().mapToInt(Integer::valueOf).toArray();
        int m = w.length;
        int[] sum = new int[m+1];
        for (int i = 1; i <= m; i++) {
            sum[i] = sum[i-1] + w[i-1];
        }
        //sum[i]: [0,i-1]
        int res = Integer.MAX_VALUE;
        int left = 0;
        for (int right = k-1; right < m; right++) {
            int mid = left+(right-left)/2; //左中位数
            res = Math.min(res, w[mid]*(2*mid-left-right)-(sum[mid]-sum[left])+(sum[right+1]-sum[mid+1]));
            left++;
        }
        return res;
    }
}