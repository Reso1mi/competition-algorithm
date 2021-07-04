class Solution {

    // nums = [1, 1, 2]
    int N;
    int[] w;
    boolean[] vis;

    public boolean xorGame(int[] nums) {
        w = nums; N = nums.length;
        vis = new boolean[N];
        int v = 0;
        for (int i = 0; i < N; i++) {
            v ^= w[i];
        }
        return dfs(v);
    }

    //[1,1,2,3,4]
    public boolean dfs(int v) {
        if (v == 0) return true;
        for (int i = 0; i < N; i++) {
            if (vis[i]) continue;
            vis[i] = true;
            //Alice拿走w[i], Bob从剩余中继续拿
            if (!dfs(v^w[i])) {
                vis[i] = false;
                return true;
            }
            vis[i] = false;
        }
        return false;
    }
}