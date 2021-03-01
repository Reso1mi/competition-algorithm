/*
岩石怪物杜达生活在魔法森林中，他在午餐时收集了N块能量石准备开吃。

由于他的嘴很小，所以一次只能吃一块能量石。

能量石很硬，吃完需要花不少时间。

吃完第 i 块能量石需要花费的时间为Si秒。

杜达靠吃能量石来获取能量。

不同的能量石包含的能量可能不同。

此外，能量石会随着时间流逝逐渐失去能量。

第 i 块能量石最初包含Ei单位的能量，并且每秒将失去Li单位的能量。

当杜达开始吃一块能量石时，他就会立即获得该能量石所含的全部能量（无论实际吃完该石头需要多少时间）。

能量石中包含的能量最多降低至0。

请问杜达通过吃能量石可以获得的最大能量是多少？

输入格式
第一行包含整数T，表示共有T组测试数据。

每组数据第一行包含整数N，表示能量石的数量。

接下来N行，每行包含三个整数Si,Ei,Li。
*/

/*
4
20 10 1
5 30 5
100 30 1
5 80 60

优先吃时间短，损耗高的

4
20 10 1
5 90 5
100 30 1
5 80 60
 */
import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int T = read(br)[0];
        int idx = 0;
        while (T-- > 0) {
            idx++;
            int N = read(br)[0];
            int[] s = new int[N], e = new int[N], l = new int[N];
            for (int i = 0; i < N; i++) {
                int[] t = read(br);
                s[i] = t[0]; e[i] = t[1]; l[i] = t[2];
            }
            out.printf("Case #%d: %d\n", idx, solve(N, s, e, l));
        }
        out.flush();
    }

    //按照Si/Li排序
    public static int solve(int N, int[] s, int[] e, int[] lo) {
        int MAX = 10005;
        Integer[] id = new Integer[N];
        for (int i = 0; i < N; i++) id[i]=i;
        Arrays.sort(id, (i1, i2)->s[i1]*lo[i2]-s[i2]*lo[i1]);
        int[] dp = new int[MAX];
        int res = 0;
        for (int k = 0; k < N; k++) {
            int i = id[k];
            for (int j = MAX-1; j >= s[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-s[i]] + Math.max(0, e[i]-(j-s[i])*lo[i]));
                res = Math.max(res, dp[j]);
            }
        }
        return res;
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}