import java.util.*;
import java.io.*;


class Main {


    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    // 64!
    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null && !"".equals(line)) {
            int[] as = toInts(line);
            line = br.readLine();
            int[] at = toInts(line);
            long s = 0L, t = 0L;
            for (int i = 0; i < 8; i += 2) {
                s = s | (1L << convert(as[i], as[i+1]));
                t = t | (1L << convert(at[i], at[i+1]));
            }
            out.println(bfs(s, t) ? "YES" : "NO");
        }
        out.flush();
    }


    public static boolean bfs(long s, long t) {
        Deque<Long> qz = new ArrayDeque<>(), qf = new ArrayDeque<>();
        HashMap<Long, Integer> dz = new HashMap<>(), df = new HashMap<>();
        qz.add(s); dz.put(s, 0);
        qf.add(t); df.put(t, 0);
        int ans = -1;
        while (!qz.isEmpty() && !qf.isEmpty()) {
            if (qz.size() <= qf.size()) {
                ans = expand(qz, dz, df);
            } else {
                ans = expand(qf, df, dz);
            }
            if (ans > 8) return false;
            if (ans >= 0) return true;
        }
        return false;
    }

    public static int expand(Deque<Long> que, HashMap<Long, Integer> dz, HashMap<Long, Integer> df) {
        int step = dz.get(que.peek());
        while (!que.isEmpty() && step == dz.get(que.peek())) {
            long cur = que.poll();
            long bak = cur;
            int cnt = 0;
            while (cur != 0) {
                if ((cur&1) == 1) {
                    int x = cnt / 8 + 1;
                    int y = cnt % 8 + 1;
                    for (int[] d : dir) {
                        int nx = x + d[0];
                        int ny = y + d[1];
                        if (nx < 1 || nx > 8 || ny < 1 || ny > 8) {
                            continue;
                        }
                        if (((bak>>>convert(nx, ny)) & 1) == 1) {
                            int nnx = nx + d[0];
                            int nny = ny + d[1];
                            if (nnx < 1 || nnx > 8 || nny < 1 || nny > 8) {
                                continue;
                            }
                            if (((bak>>>convert(nnx, nny))&1)==1) {
                                continue;
                            }
                            nx = nnx; ny = nny;
                        }
                        long val = bak ^ (1L << cnt) | (1L << (convert(nx, ny)));
                        if (df.containsKey(val)) {
                            return df.get(val) + step + 1;
                        }
                        if (!dz.containsKey(val)) {
                            dz.put(val, step + 1);
                            que.add(val);
                        }
                    }
                }
                cur >>>= 1;
                cnt++;
            }
        }   
        return -1;
    }

    public static int convert(int x, int y) {
        return (x-1)*8 + y - 1;
    }

    public static int[] toInts(String str) throws Exception {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}


public class HDOJ1401_Solitaire {
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./input.txt"));
        new Main().main();
    }
}

// public static boolean bfs(long s, long t) {
//     Deque<Long> que = new ArrayDeque<>();
//     HashMap<Long, Integer> dis = new HashMap<>();
//     que.add(s); dis.put(s, 0);
//     boolean ans = false;
//     while (!que.isEmpty()) {
//         long cur = que.poll();
//         long bak = cur;
//         int step = dis.get(cur);
//         if (step > 8) {
//             break;
//         }
//         if (cur == t) {
//             System.out.println(step);
//             ans = true;
//             break;
//         }
//         int cnt = 0;
//         while (cur != 0) {
//             if ((cur&1) == 1) {
//                 int x = cnt / 8 + 1;
//                 int y = cnt % 8 + 1;
//                 for (int[] d : dir) {
//                     int nx = x + d[0];
//                     int ny = y + d[1];
//                     if (nx < 1 || nx > 8 || ny < 1 || ny > 8) {
//                         continue;
//                     }
//                     if (((bak>>>convert(nx, ny)) & 1) == 1) {
//                         int nnx = nx + d[0];
//                         int nny = ny + d[1];
//                         if (nnx < 1 || nnx > 8 || nny < 1 || nny > 8) {
//                             continue;
//                         }
//                         if (((bak>>>convert(nnx, nny))&1)==1) {
//                             continue;
//                         }
//                         nx = nnx; ny = nny;
//                     }
//                     long val = bak ^ (1L << cnt) | (1L << (convert(nx, ny)));
//                     if (!dis.containsKey(val)) {
//                         dis.put(val, step + 1);
//                         que.add(val);
//                     }
//                 }
//             }
//             cur >>>= 1;
//             cnt++;
//         }
//     }
//     return ans;
// }