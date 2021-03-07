/*
给定一个n个点m条边的有向图，点的编号是1到n，图中可能存在重边和自环。

请输出任意一个该有向图的拓扑序列，如果拓扑序列不存在，则输出-1。

若一个由图中所有点构成的序列A满足：对于图中的每条边(x, y)，x在A中都出现在y之前，则称A是该图的一个拓扑序列。

输入格式
第一行包含两个整数n和m

接下来m行，每行包含两个整数x和y，表示存在一条从点x到点y的有向边(x, y)。
 */

import java.util.*;
import java.io.*;

class Main {

    static int N, M;
    static int idx;
    //e[i]:第i条边的终边 ne[i]:第i条边的下一条边起点 h[j]: j号点的第一条边
    static int[] e, ne, h;
    //a->b
    public static void add(int a, int b) {
        e[idx] = b; ne[idx] = h[a]; h[a] = idx++; 
    }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        N = in[0]; M = in[1];
        e = new int[M+1]; h = new int[N+1]; ne = new int[M+1];
        int[] indeg = new int[N+1];
        Arrays.fill(h, -1);
        for (int i = 0; i < M; i++) {
            int[] t = read(br); //t[0]->t[1]
            add(t[0], t[1]);
            indeg[t[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indeg[i] == 0) {
                queue.add(i);
            }
        }
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur+" ");
            cnt++;
            for (int i = h[cur]; i != -1; i = ne[i]) {
                if (--indeg[e[i]] == 0) {
                    queue.add(e[i]);
                }
            }
        }
        //如果有环，那么这个环所有的节点都无法入队
        //因为环上任何一个节点的入度都不可能为0，所以最终的序列长度必然是小于N的
        out.println(cnt < N ? -1 : sb.toString());
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}