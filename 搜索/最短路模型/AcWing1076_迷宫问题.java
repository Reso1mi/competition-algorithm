import java.util.*;
import java.io.*;
class Main {

    static int N;
    static int[][] w;
    static int[][] pre;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String []args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N =  read(br)[0];
        pre = new int[N][N]; 
        w = new int[N][N];
        for (int i = 0; i < N; i++) {
            w[i] = read(br);
        }
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] vis = new boolean[N][N];
        //要输出路径，一般逆向搜索更加方便
        queue.add(new int[]{N-1, N-1});
        vis[N-1][N-1] = true;
        pre[N-1][N-1] = -1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == 0 && cur[1] == 0) break;
            for (int i = 0; i < dir.length; i++) {
                int x = cur[0] + dir[i][0];
                int y = cur[1] + dir[i][1];
                if (x < 0 || y < 0 || x >= N || y >= N || 
                    vis[x][y] || w[x][y] == 1) continue;
                queue.add(new int[]{x, y});
                vis[x][y] = true;
                pre[x][y] = conv(cur[0], cur[1]);
            }
        }
        int tx = 0, ty = 0;
        out.println(tx + " " + ty);
        while (pre[tx][ty] != -1) {
            int[] temp = conv(pre[tx][ty]);
            out.println(temp[0] + " " + temp[1]);
            tx = temp[0]; ty = temp[1];
        }
        out.flush();
    }

    //(x,y) -> [0,N*N)
    public static int conv(int x, int y) {
        return x*N + y;
    }

    //5->(1,0)
    public static int[] conv(int i) {
        return new int[]{(i+N)/N-1, i%N};
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}