import java.util.*;
import java.io.*;
/*
Palmia国有一条横贯东西的大河，河有笔直的南北两岸，岸上各有位置各不相同的N个城市。

北岸的每个城市有且仅有一个友好城市在南岸，而且不同城市的友好城市不相同。

每对友好城市都向政府申请在河上开辟一条直线航道连接两个城市，但是由于河上雾太大，政府决定避免任意两条航道交叉，以避免事故。

编程帮助政府做出一些批准和拒绝申请的决定，使得在保证任意两条航线不相交的情况下，被批准的申请尽量多。

输入格式
第1行，一个整数N，表示城市数。

第2行到第n+1行，每行两个整数，中间用1个空格隔开，分别表示南岸和北岸的一对友好城市的坐标。

输出格式
仅一行，输出一个整数，表示政府所能批准的最多申请数。

数据范围
1≤N≤5000,
0≤xi≤10000
输入样例：
7
22 4
2 6
10 3
15 12
9 8
17 17
4 2
输出样例：
4
 */
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] w = new int[N][2];
        for (int i = 0; i < N; i++) {
            w[i][0] = sc.nextInt();
            w[i][1] = sc.nextInt();
        }
        System.out.println(solve2(w, N));
    }

    //5000 * 5000 = 2500 0000，能过但是有点慢
    public static int solve(int[][] w, int N) {
        Arrays.sort(w, (w1, w2)->w1[0]-w2[0]);
        int res = 1;
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (w[j][0] < w[i][0] && w[j][1] < w[i][1]) {
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //NlogN贪心二分的做法
    public static int solve2(int[][] w, int N) {
        Arrays.sort(w, (w1, w2)->w1[0]-w2[0]);
        int[] tail = new int[N];
        int len = 0;
        for (int i = 0; i < N; i++) {
            int idx = search(tail, len, w[i][1]);
            if (idx == -1) {
                tail[len++] = w[i][1];
            } else {
                tail[idx] = w[i][1];
            }
        }
        return len;
    }

    //从左到右找第一个大于target的，后面替换它，使得结尾更小
    public static int search(int[] tail, int len, int target) {
        int left = 0, right = len-1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (tail[mid] > target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}