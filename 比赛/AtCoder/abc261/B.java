import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = read(br)[0];
        char[][] w = new char[N][N];
        for (int i = 0; i < N; i++) {
            w[i] = br.readLine().toCharArray();
        }

        boolean flag = false;
        for(int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (w[i][j] == 'D') {
                    if (w[i][j] != w[j][i]) {
                        flag = true;
                        break;
                    }
                } else if (w[i][j] == 'L') {
                    if (w[j][i] != 'W') {
                        flag = true;
                        break;
                    }
                } else if (w[i][j] == 'W') {
                    if (w[j][i] != 'L') {
                        flag = true;
                        break;
                    }
                }
            }
        }
        out.println(flag ? "incorrect" : "correct");
        out.flush();
    }


    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// AtCoder/AcWing 提交上面部分即可，CF需要将JavaMain移到上面然后提交
public class B {
    public static void main(String[] args) throws Exception{
        // 输入重定向，通过jvm参数判断环境
        if (args.length > 0 && "Resolmi_DEBUG".equals(args[0])) {
            System.setIn(new FileInputStream("./input.txt"));
        }
        new Main().main(args);
    }
}