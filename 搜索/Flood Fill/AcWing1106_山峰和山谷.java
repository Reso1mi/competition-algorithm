/*
FGD小朋友特别喜欢爬山，在爬山的时候他就在研究山峰和山谷。

为了能够对旅程有一个安排，他想知道山峰和山谷的数量。

给定一个地图，为FGD想要旅行的区域，地图被分为 n×n 的网格，每个格子 (i,j) 的高度 w(i,j) 是给定的。

若两个格子有公共顶点，那么它们就是相邻的格子，如与 (i,j) 相邻的格子有(i−1,j−1),(i−1,j),(i−1,j+1),(i,j−1),(i,j+1),(i+1,j−1),(i+1,j),(i+1,j+1)。

我们定义一个格子的集合 S 为山峰（山谷）当且仅当：

S 的所有格子都有相同的高度。
S 的所有格子都连通。
对于 s 属于 S，与 s 相邻的 s′ 不属于 S，都有 ws>ws′（山峰），或者 ws<ws′（山谷）。
如果周围不存在相邻区域，则同时将其视为山峰和山谷。
你的任务是，对于给定的地图，求出山峰和山谷的数量，如果所有格子都有相同的高度，那么整个地图即是山峰，又是山谷。

输入格式
第一行包含一个正整数 n，表示地图的大小。

接下来一个 n×n 的矩阵，表示地图上每个格子的高度 w。

输出格式
共一行，包含两个整数，表示山峰和山谷的数量。
 */
import java.util.*;
import java.io.*;
import java.math.*; // 不是大数题可以不要这个

class Main {

    static int n;
    static boolean[][] vis;    
    static int[][] w;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, -1}, {-1, 1}, {-1, -1}, {1, 1}};
    static Queue<Pair> queue = new LinkedList<>();
    static class Pair {
        int x, y;
        public Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    /*
     */
    public static void main(String... args) throws Exception{
        InputReader in = new InputReader(System.in);
        // InputReader in = new InputReader(new FileInputStream("./input.txt"));
        n = in.nextInt();
        w = new int[n][n];
        vis = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                w[i][j] = in.nextInt();
            }
        }
        int up = 0, down = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!vis[i][j]) {
                    int temp = bfs(i, j);
                    if (temp == 1 || temp == 2) up++;
                    if (temp == -1 || temp == 2) down++;
                }
            }
        }
        System.out.println(up + " " + down);
    }

    //对这个点进行广搜，并且进行标记
    //返回值: -1山谷, 0啥也不是, 1山峰, 2既是山峰也是山谷
    public static int bfs(int x, int y) {
        queue.clear();
        queue.add(new Pair(x, y));
        vis[x][y] = true;
        boolean up = false, down = false;
        while (!queue.isEmpty()){
            Pair p = queue.poll();
            for (int i = 0; i < dir.length; i++) {
                int nx = p.x + dir[i][0];
                int ny = p.y + dir[i][1];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }
                if (w[p.x][p.y] < w[nx][ny]) {
                    down = true;
                } else if (w[p.x][p.y] > w[nx][ny]) {
                    up = true;
                } else if (!vis[nx][ny]) {
                    vis[nx][ny] = true;
                    queue.add(new Pair(nx, ny));
                }
            }
        }
        //up和down同时满足，说明周围既有比当前大的，也有比当前小的
        if (up && down) return 0;
        if (up) return 1;
        if (down) return -1;
        //up和down都不满足，所有元素一样，既是山峰也是山谷
        return 2;
    }

    //过了17/20 不知道咋改了
    //-1:下降 0:不合法 1:上升
    // public static int dfs(int x, int y) {
    //     vis[x][y] = true;
    //     boolean up = false, down = false;
    //     // System.out.println(x + " " + y);
    //     for (int i = 0; i < dir.length; i++) {
    //         int nx = x + dir[i][0];
    //         int ny = y + dir[i][1];
    //         if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
    //             continue;
    //         }
    //         if (w[x][y] < w[nx][ny]) {
    //             down = true;
    //         } else if (w[x][y] > w[nx][ny]) {
    //             up = true;
    //         } else if (!vis[nx][ny]) {
    //             int temp = dfs(nx, ny);
    //             if (temp == 1) {
    //                 up = true;
    //             } else if (temp == -1) {
    //                 down = true;
    //             } else if (temp == 0) {
    //                 return 0;
    //             }
    //         }
    //     }
    //     System.out.println(x + " " + y + " " + up + " " + down);
    //     if (up && down) return 0;
    //     if (up) return 1;
    //     if (down) return -1;
    //     return 2;
    // }
}

class InputReader {

    public BufferedReader reader;
    
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        //char[32768]
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    //默认以" "作为分隔符，读一个
    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    //有的题目不给有多少组测试用例，只能一直读，读到结尾，需要自己判断结束
    //该函数也会读取一行，并初始化tokenizer，后序直接nextInt..等就可以读到该行
    public boolean EOF() {
        String str = null;
        try {
            str = reader.readLine();
            if (str == null) {
                return true;
            }
            //创建tokenizer
            tokenizer = new StringTokenizer(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    int nextInt(){
        return Integer.parseInt(next());
    }
    
    long nextLong(){
        return Long.parseLong(next());
    }
    
    double nextDouble(){
        return Double.parseDouble(next());
    }
    
    BigInteger nextBigInteger(){
        return new BigInteger(next());
    }

    BigDecimal nextBigDecimal(){
        return new BigDecimal(next());
    }
}


public class AcWing1106_山峰和山谷 {
    public static void main(String[] args) throws Exception {
        new Main().main();
    }
}