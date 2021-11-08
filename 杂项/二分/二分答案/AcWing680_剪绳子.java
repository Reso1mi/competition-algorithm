/*
有N根绳子，第i根绳子长度为Li，现在需要M根等长的绳子，你可以对N根绳子进行任意裁剪（不能拼接），请你帮忙计算出这M根绳子最长的长度是多少。

输入格式
第一行包含2个正整数N、M，表示原始绳子的数量和需求绳子的数量。

第二行包含N个整数，其中第 i 个整数Li表示第 i 根绳子的长度。

输出格式
输出一个数字，表示裁剪后最长的长度，保留两位小数。

数据范围
1≤N,M≤100000,
0<Li<109
输入样例：
3 4
3 5 4
输出样例：
2.50
样例解释
第一根和第三根分别裁剪出一根2.50长度的绳子，第二根剪成2根2.50长度的绳子，刚好4根。
 */

import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] in = read(br);
        int N = in[0], M = in[1];
        int[] L = read(br);
        double left = 0, right = 1e9+1;
        double res = left;
        while (right-left > 1e-6) {
            double mid = left + (right-left)/2.0;
            int cnt = check(mid, L);
            if (cnt >= M) {
                res = mid;
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.printf("%.2f", res);
    }

    public static int check(double mid, int[] L) {
        int sum = 0;
        for (int i = 0; i < L.length; i++) {
            sum += (int)(L[i]/mid);
        }
        return sum;
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class AcWing680_剪绳子{
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            new Main().main();
            System.out.println("\n---------------");
        }
    }
}