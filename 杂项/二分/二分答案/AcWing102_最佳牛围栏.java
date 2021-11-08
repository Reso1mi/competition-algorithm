/*
农夫约翰的农场由 N 块田地组成，每块地里都有一定数量的牛,其数量不会少于1头，也不会超过2000头。

约翰希望用围栏将一部分连续的田地围起来，并使得围起来的区域内每块地包含的牛的数量的平均值达到最大。

围起区域内至少需要包含 F 块地，其中 F 会在输入中给出。

在给定条件下，计算围起区域内每块地包含的牛的数量的平均值可能的最大值是多少。

输入格式
第一行输入整数 N 和 F ，数据间用空格隔开。

接下来 N 行，每行输入一个整数，第i+1行输入的整数代表第i片区域内包含的牛的数目。

输出格式
输出一个整数，表示平均值的最大值乘以1000再 向下取整 之后得到的结果。

数据范围
1≤N≤100000
1≤F≤N
 */
import java.util.*;
import java.io.*;

class Main {

    static int N, F;
    static int[] w;
    static double[] sum;
    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        N = in[0]; F = in[1];
        w = new int[N];
        sum = new double[N+1];
        for (int i = 1; i <= N; i++) {
            w[i-1] = Integer.valueOf(br.readLine().trim());
        }
        double left = 0, right = 2001;
        while (right-left > 1e-6) {
            double mid = left + (right-left)/2;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        //这里其实有点坑的...
        //真正的答案应该是在[left, right]中间，这里left和right差距非常小，通常我们直接返回left
        //但是题目要求的是答案*1000后向下取整，所以取left*1000下取整肯定会有问题，答案可能会小于真实答案
        //所以我们取right*1000然后下取整，这样就保证答案一定在精度范围内
        //eg res=3, l=2.999999 r=3.0 这个时候取left就不对了，又或者l=3.0 r=3.000001这样取left也行，但是right也是对的
        out.println((long)(right*1000));
        out.flush();
    }

    //判断有没有一个区间长度大于F，且前缀和大于0
    public static boolean check(double mid) {
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i-1] + w[i-1] - mid;
        }
        double min = Integer.MAX_VALUE;
        for (int i = 0, j = i+F-1; i < N & j < N; i++, j++) {
            // for (int j = i+F; j < N; j++) {
            //     if (sum[j+1]-sum[i] >= 0) {
            //         return true;
            //     }
            // }
            min = Math.min(min, sum[i]);
            if (sum[j+1]-min >= 0) {
                return true;
            }
        }
        return false;
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}