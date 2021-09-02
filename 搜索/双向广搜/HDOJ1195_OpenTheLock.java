import java.util.*;
import java.io.*;

class Main {

    static int[] d = {1, -1};

    static HashMap<String, Integer> dis;

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.valueOf(br.readLine());
        while (T-- > 0) {
            dis = new HashMap<>();
            String lock = br.readLine();
            String pwd  = br.readLine();
            out.println(bfs(lock, pwd));
            br.readLine(); // 空行
        }
        out.flush();
    }

    //11^5
    public static int bfs(String lock, String pwd) {
        Deque<String> que = new ArrayDeque<>();
        que.add(lock);
        dis.put(lock, 0);
        while (!que.isEmpty()) {
            String cur = que.poll();
            if (cur.equals(pwd)) {
                return dis.get(cur);
            }
            char[] cs = cur.toCharArray();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    change(cs, i, d[j]);
                    String next = new String(cs);
                    if (!dis.containsKey(next)) {
                        dis.put(next, dis.get(cur) + 1);
                        que.add(next);
                    }
                    change(cs, i, -d[j]);
                }
            }
            for (int i = 0, j = i+1; j < 4; i++, j++) {
                swap(cs, i, j);
                String next = new String(cs);
                if (!dis.containsKey(next)) {
                    dis.put(next, dis.get(cur) + 1);
                    que.add(next);
                }
                swap(cs, j, i);
            }
        }
        return -1;
    }

    public static void change(char[] cs, int i, int d) {
        if (cs[i] == '1' && d == -1) {
            cs[i] = '9';
            return;
        }
        if (cs[i] == '9' && d == 1) {
            cs[i] = '1';
            return;
        }
        cs[i] += d;
    }


    public static void swap(char[] cs, int a, int b) {
        char t = cs[a];
        cs[a] = cs[b];
        cs[b] = t;
    }
}

public class HDOJ1195_OpenTheLock {
    public static void main(String[] args) throws Exception{
        // 输入重定向
        FileInputStream fis = new FileInputStream("./input.txt");
        System.setIn(fis);
        new Main().main();
    }
}