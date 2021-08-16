import java.util.*;
import java.io.*;

public class CF1063B_Labyrinth {
    public static void main(String[] args) throws Exception {
        new Main().main();
    }
}

class Main {

    static int N, M;
    static int R, C;
    static int X, Y;
    static int[][] grid;
    static boolean[][] vis;
    static int[][] remainX, remainY;
    // 左右下上
    static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] nm = read(br);
        N = nm[0]; M = nm[1];
        int[] rc = read(br);
        R = rc[0]; C = rc[1];
        int[] xy = read(br);
        X = xy[0]; Y = xy[1];

        grid = new int[N][M];
        vis = new boolean[N+1][M+1];
        remainX = new int[N+1][M+1];
        remainY = new int[N+1][M+1];
        remainX[R][C] = X;
        remainY[R][C] = Y;

        for (int i = 0; i < N; i++) {
            grid[i] = read0(br);
        }

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{R, C});
        vis[R][C] = true;
        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if ((i==0 && remainX[x][y]==0) || (i==1 && remainY[x][y]==0)) {
                    continue;
                }
                if (nx <= 0 || ny <= 0 || nx > N || ny > M || vis[nx][ny] || grid[nx-1][ny-1] == '*') {
                    continue;
                }
                if (i == 2 || i == 3) {
                    queue.addFirst(new int[]{nx, ny});
                } else {
                    queue.add(new int[]{nx, ny});
                }
                vis[nx][ny] = true;
                remainX[nx][ny] = remainX[x][y] - (i==0 ? 1 : 0);
                remainY[nx][ny] = remainY[x][y] - (i==1 ? 1 : 0);
            }
        }
        out.println(cnt);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static int[] read0(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split("")).mapToInt(c->(int)c.charAt(0)).toArray();
    }
}

/*
import java.util.*;
import java.io.*;

public class CF1063B_Labyrinth {
    public static void main(String[] args) throws Exception {
        new Main().main();
    }
}

class Main {

    static int N, M;
    static int R, C;
    static int X, Y;
    static int[][] grid;
    static boolean[][] vis;
    static int[][] remainX, remainY;
    // 左右下上
    static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] nm = read(br);
        N = nm[0]; M = nm[1];
        int[] rc = read(br);
        R = rc[0]; C = rc[1];
        int[] xy = read(br);
        X = xy[0]; Y = xy[1];

        grid = new int[N][M];
        vis = new boolean[N+1][M+1];
        remainX = new int[N+1][M+1];
        remainY = new int[N+1][M+1];
        remainX[R][C] = X;
        remainY[R][C] = Y;

        for (int i = 0; i < N; i++) {
            grid[i] = read0(br);
        }

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{R, C});
        vis[R][C] = true;
        int cnt = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if ((i==0 && remainX[x][y]==0) || (i==1 && remainY[x][y]==0)) {
                    continue;
                }
                if (nx <= 0 || ny <= 0 || nx > N || ny > M || grid[nx-1][ny-1] == '*') {
                    continue;
                }
                if (vis[nx][ny]) {
                    if (remainX[x][y] - (i==0 ? 1 : 0) > remainX[nx][ny] && remainY[x][y] - (i==1 ? 1 : 0) > remainY[nx][ny]) {
                        remainX[nx][ny] = remainX[x][y] - (i==0 ? 1 : 0);
                        remainY[nx][ny] = remainY[x][y] - (i==1 ? 1 : 0);
                        queue.add(new int[]{nx, ny});
                    }
                } else {
                    vis[nx][ny] = true;
                    cnt++;
                    remainX[nx][ny] = remainX[x][y] - (i==0 ? 1 : 0);
                    remainY[nx][ny] = remainY[x][y] - (i==1 ? 1 : 0);
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        out.println(cnt);
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static int[] read0(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split("")).mapToInt(c->(int)c.charAt(0)).toArray();
    }
}
*/