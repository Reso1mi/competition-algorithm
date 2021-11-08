import java.util.*;
import java.io.*;

class Main {

    static int[] w;
    static int N, M;
    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] t = read(br);
        N = t[0]; M = t[1];
        w = read(br);
        for (int i = N/2; i >= 0; i--) {
            down(i, N);
        }
        List<Integer> res = new ArrayList<>();
        int tail = N-1;
        for (int _i = 0; _i < M; _i++) {
            res.add(w[0]);
            swap(0, tail);
            down(0, tail--);
        }
        res.forEach(r -> out.print(r + " "));
        out.flush();
    }

    //下移
    public static void down(int i, int len) {
        while (i*2+1 < len) {
            int left = i*2+1;
            int right = left + 1;
            int min = right < len && w[right] <= w[left] ? right : left;
            if (w[i] <= w[min]) {
                return;
            }
            swap(i, min);
            i = min;
            left = i*2+1;
            right = left+1;
        }
    }

    //下移
    public static void downdown(int i, int len) {
        int left = i*2+1;
        int right = left + 1;
        if (left >= len) return;
        int min = right < len && w[right] <= w[left] ? right : left;
        if (w[i] <= w[min]) {
            return;
        }
        swap(i, min);
        downdown(min, len);
        
    }

    public static void swap(int i, int j) {
        int t = w[i];
        w[i] = w[j];
        w[j] = t;
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class AcWing838_堆排序 {
    public static void main(String[] args) throws Exception{
        // 输入重定向
        System.setIn(new FileInputStream("./input.txt"));
        new Main().main();
    }
}