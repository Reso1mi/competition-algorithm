import java.util.*;
import java.io.*;

// AtCoder/AcWing 提交上面部分即可，CF需要将JavaMain移到上面然后提交
public class G {
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
    static long suma, sumb;
    static int si;
    static long[] stack;
    static int[] res;

    public static void add(int a, int b, int i, int j) {
        e[idx] = b; ne[idx] = h[a];
        wa[idx] = i; wb[idx] = j;
        h[a] = idx++;
    }

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = read(br)[0];
        while (T-- > 0) {
            int N = read(br)[0];
            idx = 0;
            suma = 0; sumb = 0;
            
            h = new int[N+1]; Arrays.fill(h, -1);

            e = new int[N+1]; ne = new int[N+1];
            wa = new int[N+1]; wb = new int[N+1];

            stack = new long[N+1];
            si = 0;

            res = new int[N+1];
            for (int i = 2; i <= N; i++) {
                int[] t = read(br);
                add(t[0], i, t[1], t[2]);
            }
            dfs(1, 0, 0);
            for (int i = 2; i <= N; i++) {
                out.print(res[i] + " ");
            }
            out.println();
        }
        out.flush();
    }

    public static void dfs(int x, int a, int b) {
        suma += a;
        sumb += b;
        stack[si++] = sumb;
        
        // 二分找suma
        int left = 0, right = si-1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (stack[mid] <= suma) {
                res[x] = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        for (int i = h[x]; i != -1; i = ne[i]) {
            dfs(e[i], wa[i], wb[i]);
        }
        
        si--;
        suma -= a;
        sumb -= b;
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}