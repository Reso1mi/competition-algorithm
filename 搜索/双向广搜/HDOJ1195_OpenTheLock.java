import java.util.*;
import java.io.*;

class Main {

    static int[] d = {1, -1};

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.valueOf(br.readLine());
        while (T-- > 0) {
            String lock = br.readLine();
            String pwd  = br.readLine();
            out.println(bfs(lock, pwd));
            br.readLine(); // 空行
        }
        out.flush();
    }

    //11^4
    public static int bfs(String lock, String pwd) {
        HashMap<String, Integer> dz = new HashMap<>();
        HashMap<String, Integer> df = new HashMap<>();
        Deque<String> qz = new ArrayDeque<>();
        Deque<String> qf = new ArrayDeque<>();

        qz.add(lock); qf.add(pwd);
        dz.put(lock, 0); df.put(pwd, 0);
        int ans = -1;
        while (!qz.isEmpty() && !qf.isEmpty()) {
            if (qz.size() < qf.size()) {
                ans = expand(qz, dz, df);
            } else {
                ans = expand(qf, df, dz);
            }
            if (ans != -1) break;
        }
        return ans;
    }

    public static int expand(Deque<String> que, HashMap<String, Integer> dz, HashMap<String, Integer> df) {
        int step = dz.get(que.peek());
        while (!que.isEmpty() && step == dz.get(que.peek())) {
            char[] cs = que.poll().toCharArray();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    change(cs, i, d[j]);
                    String next = new String(cs);
                    if (df.containsKey(next)) {
                        return df.get(next) + step + 1;
                    }
                    if (!dz.containsKey(next)) {
                        dz.put(next, step + 1);
                        que.add(next);
                    }
                    change(cs, i, -d[j]);
                }
            }
            for (int i = 0, j = i+1; j < 4; i++, j++) {
                swap(cs, i, j);
                String next = new String(cs);
                if (df.containsKey(next)) {
                    return df.get(next) + step + 1;
                }
                if (!dz.containsKey(next)) {
                    dz.put(next, step + 1);
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
        System.setIn(new FileInputStream("./input.txt"));
        new Main().main();
    }
}

// class Main {

//     static int[] d = {1, -1};

//     static HashMap<String, Integer> dis;

//     public static void main(String... args) throws Exception {
//         PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//         int T = Integer.valueOf(br.readLine());
//         while (T-- > 0) {
//             dis = new HashMap<>();
//             String lock = br.readLine();
//             String pwd  = br.readLine();
//             out.println(bfs(lock, pwd));
//             br.readLine(); // 空行
//         }
//         out.flush();
//     }

//     //11^5
//     public static int bfs(String lock, String pwd) {
//         Deque<String> que = new ArrayDeque<>();
//         que.add(lock);
//         dis.put(lock, 0);
//         while (!que.isEmpty()) {
//             String cur = que.poll();
//             if (cur.equals(pwd)) {
//                 return dis.get(cur);
//             }
//             char[] cs = cur.toCharArray();
//             for (int i = 0; i < 4; i++) {
//                 for (int j = 0; j < 2; j++) {
//                     change(cs, i, d[j]);
//                     String next = new String(cs);
//                     if (!dis.containsKey(next)) {
//                         dis.put(next, dis.get(cur) + 1);
//                         que.add(next);
//                     }
//                     change(cs, i, -d[j]);
//                 }
//             }
//             for (int i = 0, j = i+1; j < 4; i++, j++) {
//                 swap(cs, i, j);
//                 String next = new String(cs);
//                 if (!dis.containsKey(next)) {
//                     dis.put(next, dis.get(cur) + 1);
//                     que.add(next);
//                 }
//                 swap(cs, j, i);
//             }
//         }
//         return -1;
//     }

//     public static void change(char[] cs, int i, int d) {
//         if (cs[i] == '1' && d == -1) {
//             cs[i] = '9';
//             return;
//         }
//         if (cs[i] == '9' && d == 1) {
//             cs[i] = '1';
//             return;
//         }
//         cs[i] += d;
//     }


//     public static void swap(char[] cs, int a, int b) {
//         char t = cs[a];
//         cs[a] = cs[b];
//         cs[b] = t;
//     }
// }