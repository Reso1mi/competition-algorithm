/*
    E题通解，在xy大小关系不确定的时候可以使用，也就是说该图不是DAG也适用，时间复杂度O(NlogN+M)
 */
import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int INF = 0x3f3f3f3f;
        int[] nm = read(br);
        int N = nm[0], M = nm[1];
        int[] w = read(br);
        Integer[] tid = new Integer[N];
        for (int i = 0; i < N; i++) tid[i] = i;
        //将id按照cost从小到大排序
        Arrays.sort(tid, (t1,t2)->w[t1]-w[t2]);
        List<Integer>[] adj = new ArrayList[N];
        for (int i = 0; i < M; i++) {
            int[] xy = read(br);
            int x = xy[0]-1, y = xy[1]-1;
            if (adj[x] == null) {
                adj[x] = new ArrayList<>();
            }
            adj[x].add(y);
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[N];
        int res = -INF;
        for (int i = 0; i < N; i++) {
            if (visit[tid[i]]) continue;
            queue.add(tid[i]);
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (adj[cur]==null) continue;
                for (Integer j : adj[cur]) {
                    if (visit[j]) continue;
                    visit[j] = true;
                    res = Math.max(res, w[j]-w[tid[i]]);
                    queue.add(j);
                }
            }   
        }
        System.out.println(res);
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class E_2 {
    public static void main(String[] args) throws Exception {
        new Main().main();
    }
}