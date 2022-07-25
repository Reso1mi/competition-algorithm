import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] in = read(br);
        int N = in[0], C = in[1];
        var op = new int[N][2];
        for (int i = 0; i < N; i++) {
            op[i] = read(br);
        }
        var res = new int[N+1];
        // f[i][j]，当前位初始为j(0/1) 经历 i 次操作后的结果
        var f = new int[N+1][2];
        f[0][0] = 0;
        f[0][1] = 1;
        // 枚举每一位
        for (int i = 0; i < 30; i++) {
            int cur = (C>>>i) & 1;
            // 枚举每种操作
            for (int j = 1; j <= N; j++) {
                int t = op[j-1][0],  a = op[j-1][1];
                int x = (a>>>i) & 1;
                if (t == 1) {
                    f[j] = new int[]{ f[j-1][0] & x, f[j-1][1] & x};
                }
                
                if (t == 2) {
                    f[j] = new int[]{ f[j-1][0] | x, f[j-1][1] | x};
                }

                if (t == 3) {
                    f[j] = new int[]{ f[j-1][0] ^ x, f[j-1][1] ^ x};
                }
                // 初始为cur，经历j次操作后的值（重放）
                cur = f[j][cur];
                res[j] |= (cur<<i);
            }
        }

        for (int i = 1; i <= N; i++) {
            out.println(res[i]);
        }

        out.flush();
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// AtCoder/AcWing 提交上面部分即可，CF需要将JavaMain移到上面然后提交
public class E {
    public static void main(String[] args) throws Exception{
        // 输入重定向，通过jvm参数判断环境
        if (args.length > 0 && "Resolmi_DEBUG".equals(args[0])) {
            System.setIn(new FileInputStream("./input.txt"));
        }
        new Main().main(args);
    }
}