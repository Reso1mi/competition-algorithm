import java.util.*;

class Solution {
    
    //逐位确定结果
    public int getXORSum(int[] arr1, int[] arr2) {
        int m = arr1.length, n = arr2.length;
        int[] cnt1 = new int[32];
        int[] cnt2 = new int[32];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= 31; j++) {
                if ((arr1[i]&(1<<j))!=0) {
                    cnt1[j]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 31; j++) {
                if ((arr2[i]&(1<<j))!=0) {
                    cnt2[j]++;
                }
            }
        }
        // for (int i = 0; i <= 31; i++) System.out.print(cnt1[i] + " ");
        // System.out.println();
        // for (int i = 0; i <= 31; i++) System.out.print(cnt2[i] + " ");
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            long t = cnt1[i] * (long)cnt2[i];
            res = res*2 + (int)t%2;
        }
        return res;
    }

    // 当cnt1[k]和cnt1[k]同时为奇数时，结果的第k位为1
    // 等价于arr1所有元素第k位的异或和为1，且arr2所有元素第k位的异或和为1，结果该位为1
    // 最终结果就等价于arr1的异或和 & arr2的异或和
    public int getXORSum2(int[] arr1, int[] arr2) {
        return Arrays.stream(arr1).reduce(0, (a,b)->a^b) &
               Arrays.stream(arr2).reduce(0, (a,b)->a^b);
    }
}