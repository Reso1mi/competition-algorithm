import java.util.*;
import java.io.*;

// AtCoder/AcWing 提交上面部分即可，CF需要将JavaMain移到上面然后提交
public class A {
    public static void main(String[] args) throws Exception{
        // 输入重定向，通过jvm参数判断环境
        if (args.length > 0 && "Resolmi_DEBUG".equals(args[0])) {
            System.setIn(new FileInputStream("./input.txt"));
        }
        new Main().main(args);
    }
}

class Main {

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = read(br)[0];
        while (T-- > 0) {
            int[] t = read(br);
            int N = t[0], H = t[1], M = t[2];
            int minMs = 0x3f3f3f3f;
            int resH = 0, resM = 0;
            for (int i = 0; i < N; i++) {
                int[] hm = read(br);
                int dh = 0, dm = 0;
                if (hm[0] > H || (hm[0] == H && hm[1] >= M)) {
                    if (hm[0] >= H && hm[1] >= M) {
                        dh = hm[0] - H; dm = hm[1] - M;
                    } else {
                        //
                        dh = hm[0] - H - 1;
                        dm = 60 - M + hm[1];
                        if (dm >= 60) {
                            dm -= 60;
                            dh += 1;
                        }
                    }
                } else {
                    dh = 24 - H + hm[0] - 1;
                    dm = 60 - M + hm[1];
                    if (dm >= 60) {
                        dm -= 60;
                        dh += 1;
                    }
                }

                int ms = dh*60 + dm;
                if (ms < minMs) {
                    minMs = ms;
                    resH = dh;
                    resM = dm;
                }
            }
            out.println(resH + " " + resM);
        }
        out.flush();
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}