import java.util.*;
import java.io.*;

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

class Main {

    // 1  2  4  8  16  22  24  28  36  42  44 ...
    // 3  6  12 14  18  26  32  34  38  46  52 ...
    // 5  10 10 10 ....
    // 15 20 20 20 ...
    // 7 14 18 26 ....
    // 9 18
    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = read(br)[0];
        var s1 = new HashSet<>();
        s1.add(1); s1.add(2); s1.add(4); s1.add(8); s1.add(13); s1.add(16); s1.add(17); s1.add(19);
        var s2 = new HashSet<>();
        s2.add(3); s2.add(6); s2.add(7); s2.add(9); s2.add(11); s2.add(12); s2.add(14); s2.add(18); 
        while (T-- > 0) {
            read(br);
            var a = read(br);
            var set5 = new HashSet<Integer>();
            var cnt5 = 0;

            var ins1 = false;
            var ins2 = false;
            for (Integer v : a) {
                if (v%10 == 5 || v%10 == 0) {
                    cnt5++;
                    set5.add(v + v%10);
                }
                if (s1.contains(v%20)) {
                    ins1 = true;
                }
                if (s2.contains(v%20)) {
                    ins2 = true;
                }
            }
            if ((ins2 && ins1) || (cnt5 != 0 && cnt5 != a.length) || (cnt5 == a.length && set5.size() != 1)) {
                out.println("No");
            } else {
                out.println("Yes");
            }
        }
        out.flush();
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}