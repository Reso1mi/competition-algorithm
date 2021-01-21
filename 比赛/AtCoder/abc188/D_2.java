/*
    扫描线的做法，整体效率会比差分的做法快一点，因为没有用到Hash表，但是实际上整体思路是一样的
 */
import java.util.*;
import java.io.*;

class Event {
    int t, c;
    public Event(int t, int c) {
        this.t = t;
        this.c = c;
    }
}

class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int N = in[0];
        int C = in[1];
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int[] abc = read(br);
            int a = abc[0], b = abc[1], c = abc[2];
            events.add(new Event(a, c));
            events.add(new Event(b+1, -c));
        }
        long res = 0;
        int last = 0; // 上一个时间点
        long sum = 0;
        // 按照时间排序
        Collections.sort(events, (e1, e2)->e1.t-e2.t);
        for (Event event : events) {
            long min = Math.min(sum, C);
            res += min * (event.t-last);
            last = event.t;
            sum += event.c;
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