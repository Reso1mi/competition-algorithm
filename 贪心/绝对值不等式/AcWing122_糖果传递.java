/*
有n个小朋友坐成一圈，每人有a[i]个糖果。

每人只能给左右两人传递糖果。

每人每次传递一个糖果代价为1。

求使所有人获得均等糖果的最小代价。

输入格式
第一行输入一个正整数n，表示小朋友的个数。

接下来n行，每行一个整数a[i]，表示第i个小朋友初始得到的糖果的颗数。

输出格式
输出一个整数，表示最小代价。

数据范围
1≤n≤1000000,
0≤a[i]≤2×109,
数据保证一定有解。
 */
import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int N = read(br)[0];
        long[] c = new long[N];
        int[] A = new int[N];
        long sum = 0;
        for (int i = 0; i < N; i++) {
            A[i] = Integer.valueOf(br.readLine());
            sum += A[i];
        }
        long k = sum/N;
        sum = 0;
        for (int i = 0; i < N; i++) {
            sum += A[i];
            c[i] = (i+1)*k - sum;
        }
        Arrays.sort(c);
        long res = 0;
        for (int i = 0; i < N; i++) {
            res += Math.abs(c[i]-c[N>>1]);
        }
        out.println(res);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}