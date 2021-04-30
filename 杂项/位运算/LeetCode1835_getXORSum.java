class Solution {
    //1100
    //0100
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
}