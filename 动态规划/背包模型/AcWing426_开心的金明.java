/*
金明今天很开心，家里购置的新房就要领钥匙了，新房里有一间他自己专用的很宽敞的房间。

更让他高兴的是，妈妈昨天对他说：“你的房间需要购买哪些物品，怎么布置，你说了算，只要不超过N元钱就行”。

今天一早金明就开始做预算，但是他想买的东西太多了，肯定会超过妈妈限定的N元。

于是，他把每件物品规定了一个重要度，分为5等：用整数1~5表示，第5等最重要。

他还从因特网上查到了每件物品的价格（都是整数元）。

他希望在不超过N元（可以等于N元）的前提下，使每件物品的价格与重要度的乘积的总和最大。 

设第j件物品的价格为v[j]，重要度为w[j]，共选中了k件物品，编号依次为j1，j2，…，jk，则所求的总和为： 

v[j1]∗w[j1]+v[j2]∗w[j2]+…+v[jk]∗w[jk]
请你帮助金明设计一个满足要求的购物单。

输入格式
输入文件的第1行，为两个正整数N和m，用一个空格隔开。（其中N表示总钱数，m为希望购买物品的个数） 

从第2行到第m+1行，第j行给出了编号为j-1的物品的基本数据，每行有2个非负整数v和p。（其中v表示该物品的价格，p表示该物品的重要度）

输出格式
输出文件只有一个正整数，为不超过总钱数的物品的价格与重要度乘积的总和的最大值（数据保证结果不超过100000000）。

数据范围
1≤N<30000,
1≤m<25,
0≤v≤10000 ,
1≤p≤5
输入样例：
1000 5
800 2
400 5
300 5
400 3
200 2
输出样例：
3900
 */
import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int M = in[0], N = in[1];
        int[] dp = new int[M+1];
        for (int i = 1; i <= N; i++) {
            int[] vp = read(br);
            int v = vp[0], p = vp[1];
            for (int j = M; j >= v; j--) {
                dp[j] = Math.max(dp[j], dp[j-v]+v*p);
            }
        }
        System.out.println(dp[M]);
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}