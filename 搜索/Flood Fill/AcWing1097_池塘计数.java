/*
农夫约翰有一片 N∗M 的矩形土地。

最近，由于降雨的原因，部分土地被水淹没了。

现在用一个字符矩阵来表示他的土地。

每个单元格内，如果包含雨水，则用”W”表示，如果不含雨水，则用”.”表示。

现在，约翰想知道他的土地中形成了多少片池塘。

每组相连的积水单元格集合可以看作是一片池塘。

每个单元格视为与其上、下、左、右、左上、右上、左下、右下八个邻近单元格相连。

请你输出共有多少片池塘，即矩阵中共有多少片相连的”W”块。

输入格式
第一行包含两个整数 N 和 M。

接下来 N 行，每行包含 M 个字符，字符为”W”或”.”，用以表示矩形土地的积水状况，字符之间没有空格。

输出格式
输出一个整数，表示池塘数目。

数据范围
1≤N,M≤1000
输入样例：
10 12
W........WW.
.WWW.....WWW
....WW...WW.
.........WW.
.........W..
..W......W..
.W.W.....WW.
W.W.W.....W.
.W.W......W.
..W.......W.
输出样例：
3
 */
import java.io.*;
import java.util.*;
class Main {
    
    static int N;
    static int M;
    static boolean[][] vis;    
    static char[][] board;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, -1}, {-1, 1}, {-1, -1}, {1, 1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        board = new char[N][M];
        vis = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = sc.next().toCharArray();
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!vis[i][j] && board[i][j] == 'W') {
                    res++;
                    dfs(i, j);
                }
            }
        }
        System.out.println(res);
    }

    public static void dfs (int x, int y) {
        vis[x][y] = true;
        for (int i = 0; i < dir.length; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                continue;
            }
            if (!vis[nx][ny] && board[nx][ny] == 'W') {
                dfs(nx, ny);
            }
        }
    }
}