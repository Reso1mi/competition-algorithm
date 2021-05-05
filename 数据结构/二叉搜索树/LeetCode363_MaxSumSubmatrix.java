import java.util.*;

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = matrix[i-1][j-1] + s[i-1][j] + s[i][j-1] -s[i-1][j-1];
            }
        }
        TreeSet<Integer> set = new TreeSet<>();
        int res = -0x3f3f3f3f;
        for (int u = 1; u <= m; u++) {
            for (int d = u; d <= m; d++) {
                set.clear();
                set.add(0);
                //0 lv rv, rv-lv <= k  lv >= rv-k
                for (int r = 1; r <= n; r++) {
                    int rv = s[d][r] - s[u-1][r];
                    Integer lv = set.ceiling(rv-k);
                    set.add(rv);
                    if (lv == null) continue;
                    res = Math.max(res, rv-lv);
                }
            }
        }
        return res;
    }
}