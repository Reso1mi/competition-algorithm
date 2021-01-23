/*
儿童节那天有 K 位小朋友到小明家做客。

小明拿出了珍藏的巧克力招待小朋友们。

小明一共有 N 块巧克力，其中第 i 块是 Hi×Wi 的方格组成的长方形。

为了公平起见，小明需要从这 N 块巧克力中切出 K 块巧克力分给小朋友们。

切出的巧克力需要满足：

形状是正方形，边长是整数
大小相同
例如一块 6×5 的巧克力可以切出 6 块 2×2 的巧克力或者 2 块 3×3 的巧克力。

当然小朋友们都希望得到的巧克力尽可能大，你能帮小明计算出最大的边长是多少么？

输入格式
第一行包含两个整数 N 和 K。

以下 N 行每行包含两个整数 Hi 和 Wi。

输入保证每位小朋友至少能获得一块 1×1 的巧克力。

输出格式
输出切出的正方形巧克力最大可能的边长。

数据范围
1≤N,K≤105,
1≤Hi,Wi≤105
输入样例：
2 10
6 5
5 6
输出样例：
2
 */

import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] in = read(br);
        int N = in[0], K = in[1];
        int[][] cl = new int[N][2];
        for (int i = 0; i < N; i++) {
            int[] t = read(br);
            cl[i][0] = t[0]; cl[i][1] = t[1];
        }
        int left = 1, right = Integer.MAX_VALUE;
        int res = left;
        while (left <= right) {
            int mid = left + (right-left)/2;
            int cnt = check(mid, cl);
            if (cnt >= K) {
                res = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        System.out.println(res);
    }

    public static int check(int mid, int[][] cl) {
        int cnt = 0;
        for (int i = 0; i < cl.length; i++) {
            cnt += (cl[i][0]/mid) * (cl[i][1]/mid);
        }
        return cnt;
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class AcWing1227_分巧克力 {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            new Main().main();
            System.out.println("\n---------------");
        }
    }
}