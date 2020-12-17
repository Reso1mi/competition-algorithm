import java.io.*;
import java.util.*;

public class AcWing1098_城堡问题 {
    public static void main(String[] args) throws Exception {
        new Main().main();
    }
}

class Main {
    //        2(0010)
    //1(0001)         4(0100)
    //        8(1000)
    //左，上，右，下
    static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};    
    static int n, m;
    static boolean[][] vis;
    static int[][] board;
    
    public static void main(String... args) throws Exception {
        Scanner sc = new Scanner(new File("./input.txt"));
        // Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        vis = new boolean[n][m];
        board = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        int count = 0, max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j]) {
                    count++;
                    max = Math.max(max, dfs(i, j));
                }
            }
        }
        System.out.println(count);
        System.out.println(max);
    }

    public static int dfs (int x, int y) {
        vis[x][y] = true;
        int area = 1;
        for (int i = 0; i < dir.length; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }
            if (!vis[nx][ny] && ((board[x][y]>>i)&1) == 0) {
                area += dfs(nx, ny);
            }
        }
        return area;
    }
}

//傻傻的做法
class Main2 {
    //     2(3)
    //1(2)      4(1)
    //     8(0)
    
    //下，右，左，上
    static int[][] dir = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};    
    static int n, m;
    static boolean[][] wall;
    static boolean[][] vis;
    static int[][] board;
    
    public static void main(String... args) throws Exception {
        Scanner sc = new Scanner(new File("./input.txt"));
        // Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        vis = new boolean[n][m];
        wall = new boolean[16][4];
        wall[2][3] = wall[1][2] = wall[4][1] = wall[8][0] = true;
        wall[3][2] = wall[3][3] = true;
        wall[5][1] = wall[5][2] = true;
        wall[9][0] = wall[9][2] = true;
        wall[6][1] = wall[6][3] = true;
        wall[10][0] = wall[10][3] = true;
        wall[12][0] = wall[12][1] = true;
        wall[7][1] = wall[7][2] = wall[7][3] = true; 
        wall[11][0] = wall[11][2] = wall[11][3] = true;
        wall[14][0] = wall[14][1] = wall[14][3] = true;
        wall[13][0] = wall[13][1] = wall[13][2] = true;
        wall[15][0] = wall[15][1] = wall[15][2] = wall[15][3] = true;
        board = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        int count = 0, max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j]) {
                    count++;
                    max = Math.max(max, dfs(i, j));
                }
            }
        }
        System.out.println(count);
        System.out.println(max);
    }

    public static int dfs (int x, int y) {
        vis[x][y] = true;
        int area = 1;
        for (int i = 0; i < dir.length; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (nx < 0 || ny < 0 || nx >= n || ny >= m || wall[board[x][y]][i]) {
                continue;
            }
            if (!vis[nx][ny]) {
                area += dfs(nx, ny);
            }
        }
        return area;
    }
}