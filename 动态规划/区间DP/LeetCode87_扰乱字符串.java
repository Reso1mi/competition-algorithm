class Solution {

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
        boolean[][][] dp = new boolean[n+1][n+1][n+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = c1[i] == c2[j];
            }
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i+len-1 < n; i++) {
                for (int j = 0; j+len-1 < n; j++) {
                    for (int k = 1; k < len; k++) {
                        if (dp[i][j][len]) break;
                        boolean a = dp[i][j][k] & dp[i+k][j+k][len-k];
                        // j+len-1 - k + 1
                        boolean b = dp[i][j+len-1-k+1][k] & dp[i+k][j][len-k];
                        dp[i][j][len] = a | b;
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}

// class Solution {

//     char[] c1, c2;
    
//     Boolean [][][] dp = null;

//     public boolean isScramble(String s1, String s2) {
//         int n = s1.length();
//         dp = new Boolean[n+1][n+1][n+1];
//         c1 = s1.toCharArray(); c2 = s2.toCharArray();
//         return dfs(0, 0, n);
//     }

//     public boolean dfs(int i1, int i2, int len) {
//         if (!check(i1, i2, len)) {
//             return dp[i1][i2][len] = false;
//         }
//         if (len == 1) {
//             return dp[i1][i2][len] = c1[i1] == c2[i2];
//         }
//         if (new String(c1, i1, len).equals(new String(c2, i2, len))) {
//             return dp[i1][i2][len] = true;
//         }
//         if (dp[i1][i2][len] != null) {
//             return dp[i1][i2][len];
//         }
//         int k = i1, p = i2;
//         int j1 = i1+len-1, j2 = i2+len-1;
//         while (k < j1 && p < j2) {
//             // j1-k+k-i1+1
//             if (dfs(i1, i2, k-i1+1) && dfs(k+1, p+1, j1-k)) {
//                 return dp[i1][i2][len] = true;
//             }
//             if (dfs(i1, j2-(k-i1+1)+1, k-i1+1) && dfs(k+1, i2, j1-k)) {
//                 return dp[i1][i2][len] = true;
//             }
//             k++; p++;
//         }
//         return dp[i1][i2][len] = false;
//     }

//     public boolean check(int i1, int i2, int len) {
//         int[] cnt1 = new int[26];
//         int[] cnt2 = new int[26];
//         int j1 = i1+len-1, j2 = i2+len-1;
//         while (i1 <= j1 && i2 <= j2) {
//             cnt1[c1[i1++]-'a']++;
//             cnt2[c2[i2++]-'a']++;
//         }
//         for (int i = 0; i < 26; i++) {
//             if (cnt1[i] != cnt2[i]) {
//                 return false;
//             }
//         }
//         return true;
//     }
// }

// class Solution {

//     char[] c1, c2;
    
//     Boolean[][][][] dp = null;

//     public boolean isScramble(String s1, String s2) {
//         int m = s1.length();
//         int n = s2.length();
//         dp = new Boolean[m+1][m+1][n+1][n+1];
//         c1 = s1.toCharArray();
//         c2 = s2.toCharArray();
//         if (!check(0, m-1, 0, n-1)) return false;
//         return dfs(0, m-1, 0, n-1);
//     }

//     public boolean dfs(int i1, int j1, int i2, int j2) {
//         if (!check(i1, j1, i2, j2)) {
//             return dp[i1][j1][i2][j2] = false;
//         }
//         if (i1 == j1) {
//             if (c1[i1] == c2[i2]) {
//                 return true;
//             }
//             return false;
//         }
//         if (new String(c1, i1, j1-i1+1).equals(new String(c2, i2, j1-i1+1))) {
//             // System.out.println(new String(c1, i1, j1-i1+1));
//             return true;
//         }
//         if (dp[i1][j1][i2][j2] != null) {
//             return dp[i1][j1][i2][j2];
//         }
//         int k = i1, p = i2;
//         while (k < j1 && p < j2) {
//             if (dfs(i1, k, i2, p) && dfs(k+1, j1, p+1, j2)) {
//                 dp[i1][k][i2][p] = true;
//                 dp[k+1][j1][p+1][j2] = true;
//                 return true;
//             }
//             k++; p++;
//         }
//         k = i1; p = j2;
//         while (k < j1 && p > 0) {
//             if (dfs(i1, k, p, j2) && dfs(k+1, j1, i2, p-1)) {
//                 dp[i1][k][p][j2] = true;
//                 dp[k+1][j1][i2][p-1] = true;
//                 return true;
//             }
//             k++; p--;
//         }
//         return dp[i1][j1][i2][j2] = false;
//     }

//     public boolean check(int i1, int j1, int i2, int j2) {
//         if (j1-i1 != j2-i2) {
//             return false;
//         }
//         int[] cnt1 = new int[26];
//         int[] cnt2 = new int[26];
//         while (i1 <= j1 && i2 <= j2) {
//             cnt1[c1[i1++]-'a']++;
//             cnt2[c2[i2++]-'a']++;
//         }
//         for (int i = 0; i < 26; i++) {
//             if (cnt1[i] != cnt2[i]) {
//                 return false;
//             }
//         }
//         return true;
//     }
// }