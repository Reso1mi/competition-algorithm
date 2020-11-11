/*
N位同学站成一排，音乐老师要请其中的(N-K)位同学出列，使得剩下的K位同学排成合唱队形。     
合唱队形是指这样的一种队形：设K位同学从左到右依次编号为
1，2…，K，他们的身高分别为T1，T2，…，TK，则他们的身高满足T1 <…< Ti > Ti+1 >…> TK (1≤i≤K)。
你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，
可以使得剩下的同学排成合唱队形。

8
186 186 150 200 160 130 197 220
输出样例：
4

和登山一样，代码拷过来就ac了
 */
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
        System.out.println(N-solve(w, N));
    }

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