import java.util.*;
import java.io.*;

// AtCoder/AcWing 提交上面部分即可，CF需要将JavaMain移到上面然后提交
public class D {
    public static void main(String[] args) throws Exception {
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
        int Q = read(br)[0];
        while (Q-- > 0) {
            String t = br.readLine();
            int n = read(br)[0];
            // match[i]: t第i个位置最远能匹配到哪里
            var match = new int[t.length()][2];

            String[] s = new String[n];
            for (int i = 0; i < n; i++) {
                s[i] = br.readLine();
                // 预处理每个s[i]在t中能匹配的位置
                for (int j = 0; j < t.length(); j++) {
                    var flag = true;
                    for (int k = 0; k < s[i].length(); k++) {
                        if (j + k >= t.length() || t.charAt(j+k) != s[i].charAt(k)) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) continue;
                    if (match[j][1] <= s[i].length() + j) {
                        match[j] = new int[]{i, s[i].length() + j};
                    }
                }
            }
            // 下一次开始匹配的位置
            var last = 0;
            var flag = true;
            var cnt = 0;
            var lis = new ArrayList<String>();
            while (last < t.length()) {
                var temp = last;
                int w = 0, p = 0;
                for (int i = 0; i <= temp; i++) {
                    if (match[i][1] >= last) {
                        last = match[i][1];
                        w = match[i][0]+1;
                        p = i+1;
                    }
                }
                // 没有能cover last的区间
                if (last == temp) {
                    flag = false;
                    break;
                }
                cnt++;
                lis.add(w + " " + p);
            }
            if (flag) {
                out.println(cnt);
                lis.stream().forEach(out::println);
            } else {
                out.println(-1);
            }

        }
        out.flush();
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

