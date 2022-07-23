import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = read(br)[0];
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (map.containsKey(str)) {
                int idx = map.get(str);
                out.println(str + "(" + idx + ")");
            } else {
                out.println(str);
            }
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        out.flush();
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// AtCoder/AcWing 提交上面部分即可，CF需要将JavaMain移到上面然后提交
public class C {
    public static void main(String[] args) throws Exception{
        // 输入重定向，通过jvm参数判断环境
        if (args.length > 0 && "Resolmi_DEBUG".equals(args[0])) {
            System.setIn(new FileInputStream("./input.txt"));
        }
        new Main().main(args);
    }
}