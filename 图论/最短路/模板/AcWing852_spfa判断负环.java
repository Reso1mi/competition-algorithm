/*
给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。

请你判断图中是否存在负权回路。

输入格式
第一行包含整数n和m。

接下来m行每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。

输出格式
如果图中存在负权回路，则输出“Yes”，否则输出“No”。

数据范围
1≤n≤2000,
1≤m≤10000,
图中涉及边长绝对值均不超过10000。
 */
import java.util.*;
import java.io.*;

class Main {

    static int INF = 0x3f3f3f3f;
    static int N, M;
    static int idx;
    static int[] e, h, ne, w;
    //a->b
    public static void add(int a, int b, int c){
        e[idx] = b; w[idx] = c;
        ne[idx] = h[a]; h[a] = idx++;
    }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        N = in[0]; M = in[1];
        e = new int[M+1]; ne = new int[M+1]; w = new int[M+1];
        h = new int[N+1]; Arrays.fill(h, -1);
        for (int i = 0; i < M; i++) {
            int[] t = read(br);
            add(t[0], t[1], t[2]);
        }
        out.println(spfa() ? "Yes" : "No");
        out.flush();
    }

    
    // 由于是判断图有没有负环，所以每个点都可能在负环上，不一定是1号点
    // 暴力的方法就是对每个点作为源点做一次spfa，但是这样显然复杂度过高
    // 考虑增加一个虚拟节点，和所有的点增加一条权值为0的单向边
    // 新增加的虚拟节点一定在负环上，所以我们对虚拟节点做一次spfa就能判断出原图是否有负环
    // 模拟第一次入队出队，虚拟节点出队后就会将所有的节点入队，且dis[i]=0，然后继续spfa的步骤
    // 然后就是关键的判环步骤，我们只需要记录下每个点到虚拟源点的边的数量就行了
    // 如果松弛后某个点到虚拟源点的边的数量大于等于N+1就说明存在负环
    public static boolean spfa() {
        Queue<Integer> queue = new LinkedList<>();
        int[] dis = new int[N+1];
        boolean[] vis = new boolean[N+1];
        int[] cnt = new int[N+1];
        for (int i = 1; i <= N; i++) {
            queue.add(i);
            vis[i] = true;
            cnt[i] = 1;
        }
        while (!queue.isEmpty()) {
            int top = queue.poll();
            vis[top] = false;
            for (int i = h[top]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dis[j] > dis[top] + w[i]) {
                    dis[j] = dis[top] + w[i];
                    cnt[j] = cnt[top] + 1;
                    if (cnt[j] >= N+1) return true;
                    if (!vis[j]) {
                        queue.add(j);
                        vis[j] = true;
                    }
                }
            }
        }
        return false;
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}