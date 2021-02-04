import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        long N = Long.valueOf(br.readLine());
        // (a+a+n-1)*n/2 = N ==> 2a = 2N/n + 1 - n
        // 枚举2N的所有因子
        long x = 2*N;
        HashSet<Long> set = new HashSet<>();
        //i*i大于x那么i之前肯定已经被加入了
        for (long i = 1; i*i <= x; i++) {
            if ((x%i) == 0) {
                set.add(i);
                set.add(x/i);
            }
        }
        long res = 0;
        for (Long f : set) {
            if ((x/f+1-f)%2==0) res++;
        }
        out.println(res);
        out.flush();
    }
}

public class D {
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}