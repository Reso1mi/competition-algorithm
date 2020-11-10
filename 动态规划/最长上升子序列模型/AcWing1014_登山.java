import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] w = new int[N];
        for (int i = 0; i < N; i++) {
            w[i] = sc.nextInt();
        }
        System.out.println(solve(w, N));
    }

    //186 186 150 200 160 130 197 220  := 4
    public static int solve(int[] w, int N) {
        int[] dp1 = new int[N];
        int[] dp2 = new int[N];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);
        int res = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (w[j] < w[i]) {
                    dp1[i] = Math.max(dp1[j]+1, dp1[i]);          
                }
            }
        }
        for (int i = N-1; i >= 0; i--) {
            for (int j = N-1; j > i; j--) {
                if (w[j] < w[i]) {
                    dp2[i] = Math.max(dp2[j]+1, dp2[i]);          
                }
            }
            res = Math.max(res, dp1[i]+dp2[i]-1);
        }
        return res;
    }
}