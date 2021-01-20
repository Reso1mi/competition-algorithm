import java.util.*;
import java.io.*;

class Main {

    static HashMap<Long, Long> dp = new HashMap<>();

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        long[] xy = read(br);
        long x = xy[0], y = xy[1];
        if (x >= y) {
            System.out.println(x-y);
            return;
        }
        System.out.println(dfs(x, y));
    }

    public static long dfs (long x, long y) {
        if (x > y) return x-y;
        if (x == y)  return 0;
        if (dp.containsKey(y)) {
            return dp.get(y);
        }
        long res = y-x;
        if (y%2==0) {
            res = Math.min(dfs(x, y/2)+1, res);
        } else {
            res = Math.min(dfs(x, (y-1)/2)+2, res);
            res = Math.min(dfs(x, (y+1)/2)+2, res);
        }
        dp.put(y, res);
        return res;
    }

    public static long[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
    }
}