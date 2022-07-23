import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] in = read(br);
        int a = in[0], b = in[1];
        int c = in[2], d = in[3];

        Arrays.sort(in);
        
        if ((a == in[0] && b == in[1]) || (c == in[0] && d == in[1])) {
            out.println(0);
        } else {
            out.println(in[2]-in[1]);
        }
        out.flush();
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

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