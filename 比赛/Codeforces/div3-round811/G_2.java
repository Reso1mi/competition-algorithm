import java.util.*;
import java.io.*;

// AtCoder/AcWing 提交上面部分即可，CF需要将JavaMain移到上面然后提交
public class G_2 {
    public static void main(String[] args) throws Exception{
        // 输入重定向，通过jvm参数判断环境
        if (args.length > 0 && "Resolmi_DEBUG".equals(args[0])) {
            System.setIn(new FileInputStream("./input.txt"));
        }
        new Main().main(args);
    }
}

class Main {

    static int idx;
    static int[] e, ne, h, wa, wb;
    static int[] res;
    // fa[i][j]，节点i向上跳2^j的节点
    static int[][] fa;
    // fsb[i][j]，节点i向上跳2^j的节点sumb总和
    static long[][] fsb;
    static int[] dep;
    static long[] suma, sumb;

    public static void add(int a, int b, int i, int j) {
        e[idx] = b; ne[idx] = h[a];
        wa[idx] = i; wb[idx] = j;
        h[a] = idx++;
    }

    // 倍增做法
    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = read(br)[0];
        while (T-- > 0) {
            int N = read(br)[0];
            idx = 0;   
            h = new int[N+1]; Arrays.fill(h, -1);
            e = new int[N+1]; ne = new int[N+1];
            wa = new int[N+1]; wb = new int[N+1];

            suma = new long[N+1]; sumb = new long[N+1];
            dep = new int[N+1];
            // fa[i][j]: i向上跳2^j的节点
            fa = new int[N+1][31];
            fsb = new long[N+1][31];

            for (int i = 2; i <= N; i++) {
                int[] t = read(br);
                add(t[0], i, t[1], t[2]);
            }
            // 构建倍增数组
            dfs(1);
            for (int i = 2; i <= N; i++) {
                int res = dep[i];
                if (suma[i] < sumb[i]) {
                    // 向上走大于等于t距离的第一个
                    long t = sumb[i]-suma[i];
                    int x = i;
                    for (int k = 30; k >= 0; k--) {
                        if (fsb[x][k] <= t) {
                            t -= fsb[x][k];
                            x = fa[x][k];
                            res = dep[x];
                        }
                    }
                    // 找不到sumb刚好相差t的节点，再往上走一步
                    if (t > 0) res--;
                }
                out.print(res+" ");
            }
            out.println();
        }
        out.flush();
    }

    public static void dfs(int root) {

        for (int i = 1; i < 31; i++) {
            fa[root][i] = fa[fa[root][i-1]][i-1];
            // root跳2^i = root跳到2^(i-1) + 2^(i-1)到2^i
            fsb[root][i] = fsb[root][i-1] + fsb[fa[root][i-1]][i-1];
        }

        for (int i = h[root]; i != -1; i = ne[i]) {
            int t = e[i];
            suma[t] = suma[root] + wa[i];
            sumb[t] = sumb[root] + wb[i];
            dep[t] = dep[root] + 1;
            fa[t][0] = root;
            fsb[t][0] = wb[i];
            dfs(t);
        }
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}