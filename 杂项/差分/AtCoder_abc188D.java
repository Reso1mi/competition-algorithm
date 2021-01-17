import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int N = in[0];
        int C = in[1];
        HashMap<Integer, Long> diff = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int[] abc = read(br);
            int a = abc[0], b = abc[1], c = abc[2];
            // Java的map确实蛋疼...
            diff.put(a, diff.getOrDefault(a, 0l)+c); diff.put(b+1, diff.getOrDefault(b+1, 0l)-c);
        }
        long res = 0;
        int last = 0; // 上一个时间点
        long sum = 0; // 差分前缀和
        Integer[] keys = diff.keySet().toArray(new Integer[0]);
        // 按照时间排序
        Arrays.sort(keys);
        for (Integer key : keys) {
            long min = Math.min(sum, C);
            res += min * (key-last);
            last = key;
            sum += diff.get(key);
        }
        System.out.println(res);
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class D {
    public static void main(String[] args) throws Exception {
        new Main().main();
    }
}