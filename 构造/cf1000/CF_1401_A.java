import java.util.*;
import java.io.*;
// https://codeforces.com/problemset/problem/1401/A
// AtCoder/AcWing 提交上面部分即可，CF需要将JavaMain移到上面然后提交
public class CF_1401_A {
    public static void main(String[] args) throws Exception{
        // 输入重定向，通过jvm参数判断环境
        if (args.length > 0 && "Resolmi_DEBUG".equals(args[0])) {
            System.setIn(new FileInputStream("./input.txt"));
        }
        new Main().main(args);
    }
}

class Main {

    // k > n: OB-AB = OA = k
    // k < n: OB+BA=OA OB-BA=k 2OB = k + OA
    //                 BA-OB=k 2BA = k + OA
    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = read(br)[0];
        while (T-- > 0) {
            int[] in = read(br);
            int n = in[0], k = in[1];
            if (k >= n) {
                out.println(k-n);
            } else {
                if (((k+n)&1) == 0) {
                    out.println(0);
                } else {
                    out.println(1);
                }
            }
        }
        out.flush();
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}