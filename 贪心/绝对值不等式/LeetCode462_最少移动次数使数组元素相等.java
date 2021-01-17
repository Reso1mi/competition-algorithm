import java.util.*;

class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.abs(nums[i]-nums[n>>1]);
        }
        return res;
    }
}