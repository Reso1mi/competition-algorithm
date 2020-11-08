public class Main {
    public static void main(String[] args) {

    }

    public static int solve(int[][] w, int N) {
        int[][] dp = new int[N][N];
        dp[0][0] = w[0][0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i-1][0] + w[i][0];
            dp[0][i] = dp[0][i-1] + w[0][i];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + w[i][j];
            }
        }
        return dp[N-1][N-1];
    }
}