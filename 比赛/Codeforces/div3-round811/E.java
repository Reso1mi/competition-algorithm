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
        // 分析发现20为一个循环，我们将20以内的元素进行分组，两组元素序列完全平行，无法相交
        // 所以如果元素 mod 20在同一组中，那么就一定可以转化成一样的，否则一定不行
        // 除此之外，需要额外注意5和0结尾的元素，它们最多只能转化一次，所以如果有5/0结尾的元素，那么所有元素都必须是5/0结尾，且5结尾经过转换后与0结尾相同
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
            // 既有s1中的元素，又有s2中的元素 || 有5/0结尾的但是不全是 || 全是5/0结尾的，但是转换后不想等
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