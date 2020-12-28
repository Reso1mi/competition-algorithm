/*
有 N 种物品和一个容量是 M 的背包。

第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。

求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
输出最大价值。

输入格式
第一行两个整数，N，M (0<N≤1000, 0<M≤20000)，用空格隔开，分别表示物品种数和背包容积。

接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。

输出格式
输出一个整数，表示最大价值。

数据范围
0<N≤1000
0<M≤20000
0<vi,wi,si≤20000
提示
本题考查多重背包的单调队列优化方法。
输入样例
4 5
1 2 3
2 4 1
3 4 3
4 5 2
输出样例：
10
 */
import java.util.*;
import java.io.*;
class Main {

    //dp[i][j]   = Max(dp[i-1][j], dp[i-1][j-v]+w, dp[i-1][j-2*v]+2*w,... dp[i-1][j-s*v]+s*w)
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] in = read(br);
        int N = in[0];
        int M = in[1];
        int[][] dp = new int[N+1][M+1];
        //单调队列求最大值
        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            int[] temp = read(br);
            int v = temp[0], w = temp[1], s = temp[2];
            //枚举余数[0, v)
            for (int r = 0; r < v; r++) {
                queue.clear();
                //枚举同余所有状态dp[j] dp[j+v] dp[j+2v]....
                for (int k = 0; r+k*v <= M; k++) {
                    int val = dp[i-1][r+k*v] - k*w;
                    while (!queue.isEmpty() && queue.getLast()[0] < val) {
                        queue.removeLast();
                    }
                    //同余的数组元素数量可能超过同一物品的使用次数s
                    //区间内使用次数: ((r+k*v)-(r+q.first()[1]*v)) / v = k-q.first()[1]
                    if (!queue.isEmpty() && k - queue.getFirst()[1] > s) {
                        queue.removeFirst();
                    }
                    queue.addLast(new int[]{val, k});
                    dp[i][r+k*v] = queue.getFirst()[0] + k*w;
                }
            }

        }
        System.out.println(dp[N][M]);
    }

    private static int[] read(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}