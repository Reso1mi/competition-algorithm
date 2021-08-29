import java.util.*;
import java.io.*;

class Main {

    static int n;

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int ans = bfs(br);
        out.println(ans == -1 ? "NO ANSWER!" : ans);
        out.flush();
    }

    public static int bfs(BufferedReader br) throws Exception {
        String[] in = br.readLine().split(" ");
        String A = in[0], B = in[1];
        if (A.equals(B)) {
            return 0; 
        }
        // 正反搜索队列
        Deque<String> qp = new ArrayDeque<>();
        Deque<String> qn = new ArrayDeque<>();
        // 正反搜索步数
        HashMap<String, Integer> dp = new HashMap<>();
        HashMap<String, Integer> dn = new HashMap<>();
        
        // 转换规则
        String[] sa = new String[7];
        String[] sb = new String[7];

        String line = null;
        n = 0;
        while ((line = br.readLine()) != null) {
            String[] t = line.split(" ");
            sa[n] = t[0]; sb[n++] = t[1];
        }

        qp.add(A); qn.add(B);
        dp.put(A, 0); dn.put(B, 0);
        int ans = -1, cnt = 0;
        while (!qp.isEmpty() && !qn.isEmpty()) {
            if (qp.size() < qn.size()) {
                ans = expand(qp, dp, dn, sa, sb);
            } else {
                ans = expand(qn, dn, dp, sb, sa);
            }
            ans = expand(qp, dp, dn, sa, sb);
            if (ans <= 10) return ans;
            if (++cnt >= 10) return -1;
        }
        return -1;
    }

    public static int expand(Deque<String> que, Map<String, Integer> dp, Map<String, Integer> dn, String[] sa, String[] sb) {
        int limit = dp.get(que.peek());
        // 一次扩展一层
        while (!que.isEmpty() && limit == dp.get(que.peek())) {
            String cur = que.poll();
            for (int i = 0; i < n; i++) { // 枚举规则
                for (int j = 0, k = sa[i].length(); k <= cur.length(); j++, k++) { // 枚举字符
                    if (sa[i].equals(cur.substring(j, k))) {
                        String next = cur.substring(0, j) + sb[i] + cur.substring(k);
                        if (dn.containsKey(next)) {
                            return dp.get(cur) + 1 + dn.get(next);
                        }
                        // 去重
                        if (dp.containsKey(next)) continue;
                        dp.put(next, dp.get(cur) + 1);
                        que.add(next);
                    }
                }
            }
        }
        return 123456;
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}