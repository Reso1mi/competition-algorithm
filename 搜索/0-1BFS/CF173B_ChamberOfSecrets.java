import java.util.*;
import java.io.*;

public class CF173B_ChamberOfSecrets {
    public static void main(String[] args) throws Exception {
        new Main().main();
    }
}

class Main {

    static int N, M;
    static char[][] grid;
    static boolean[] vis;
    static int[] dis;

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = read(br);
        N = nm[0]; M = nm[1];

        grid = new char[N+1][M+1];
        vis = new boolean[N+M+1];
        dis = new int[N+M+1];

        for (int i = 1; i <= N; i++) {
            grid[i] = ("0"+br.readLine()).toCharArray();
        }
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(N);
        vis[N] = true;
        dis[N] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == 1) break;
            if (cur <= N) { // 横向线条
                for (int j = 1; j <= M; j++) {
                    if (!vis[j+N] && grid[cur][j] == '#') {
                        queue.add(j+N);
                        dis[j+N] = dis[cur] + 1;
                        vis[j+N] = true;
                    }
                }
            } else {
                for (int i = 1; i <= N; i++) {
                    if (!vis[i] && grid[i][cur-N] == '#') {
                        queue.add(i);
                        dis[i] = dis[cur] + 1;
                        vis[i] = true;
                    }
                }
            }
        }
        out.println(dis[1]==0 ? -1 : dis[1]);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}


// import java.util.*;
// import java.io.*;
 
 
 
// public class CF173B_ChamberOfSecrets {
//     public static void main(String[] args) throws Exception {
//         new Main().main();
//     }
// }
 
// class Main {
 
//     static int N, M;
//     static char[][] grid;
//     static boolean[][][] vis;
//     static int[][][] dis;
//     static int INF = 0x3f3f3f3f;
//     static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
 
//     public static void main(String... args) throws Exception {
//         PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         int[] nm = read(br);
//         N = nm[0]; M = nm[1];
 
//         grid = new char[N+1][M+1];
//         vis = new boolean[N+1][M+1][4];
//         dis = new int[N+1][M+1][4];
 
//         for (int i = 1; i <= N; i++) {
//             for (int j = 1; j <= M; j++) {
//                 Arrays.fill(dis[i][j], INF);
//             }
//         }
//         for (int i = 1; i <= N; i++) {
//             grid[i] = ("0"+br.readLine()).toCharArray();
//         }
//         Deque<int[]> queue = new ArrayDeque<>();
//         queue.add(new int[]{N, M, 0});
//         dis[N][M][0] = 0;
//         while (!queue.isEmpty()) {
//             int[] cur = queue.poll();
//             int x = cur[0], y = cur[1], p = cur[2];
//             if (x == 1 && y == 1 && p == 0) break;
//             if (vis[x][y][p]) continue;
//             vis[x][y][p] = true;
//             for (int i = 0; i < 4; i++) {
//                 int nx = x + dir[i][0], ny = y + dir[i][1];
//                 if (nx <= 0 || nx > N || ny <= 0 || ny > M) continue;
//                 if (i == p) {
//                     queue.addFirst(new int[]{nx, ny, p});
//                     dis[nx][ny][i] = dis[x][y][p];
//                 } else if (grid[x][y] == '#') {
//                     if (dis[x][y][p] + 1 < dis[nx][ny][i]) {
//                         dis[nx][ny][i] = dis[x][y][p] + 1;
//                         queue.add(new int[]{nx, ny, i});
//                     }
//                 }
//             }
//         }
//         out.println(dis[1][1][0]==INF ? -1 : dis[1][1][0]);
//         out.flush();
//     }
 
//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }