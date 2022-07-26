import java.util.*;
import java.io.*;

// https://codeforces.com/problemset/problem/1433/C

// AtCoder/AcWing 提交上面部分即可，CF需要将JavaMain移到上面然后提交
public class CF_1433_C {
    public static void main(String[] args) throws Exception{
        // 输入重定向，通过jvm参数判断环境
        if (args.length > 0 && "Resolmi_DEBUG".equals(args[0])) {
            System.setIn(new FileInputStream("./input.txt"));
        }
        new Main().main(args);
    }
}


class Main {

    // 5 3 4 4 5
    // 4 4 3 4 4
    // 2 3 1 1 2 4
    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = read(br)[0];
        while (T-- > 0) {
            read(br);
            var t = read(br);
            int max = 0;
            for (int i = 0; i < t.length; i++) {
                max = Math.max(max, t[i]);
            }
            int res = -1;
            for (int i = 0; i < t.length; i++) {
                if (t[i] != max) continue;
                if (i > 0 && t[i] > t[i-1]) {
                    res = i+1;
                    break;
                }
                if (i < t.length-1 && t[i] > t[i+1]) {
                    res = i+1;
                    break;
                }
            }
            out.println(res);
        }
        out.flush();
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}