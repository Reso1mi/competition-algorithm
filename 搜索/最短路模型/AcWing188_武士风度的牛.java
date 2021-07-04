import java.util.*;
import java.io.*;

class Main {

    static int C, R;
    static int[][] w;
    static int[][] dis;
    static int[][] dir = {{-2, 1}, {1, -2}, {2, -1}, {-1, 2}, {2, 1}, {1, 2}, {-1, -2}, {-2, -1}};

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        C = in[0]; R = in[1]; w = new int[R][C];
        Queue<int[]> queue = new LinkedList<>();
        dis = new int[R][C];
        for (int i = 0; i < R; i++) Arrays.fill(dis[i], -1);
        for (int i = 0; i < R; i++) {
            char[] cs = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (cs[j] == 'K') {
                    queue.add(new int[]{i, j});
                    dis[i][j] = 0;
                }
                w[i][j] = cs[j];
            }
        }
        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            int x = top[0], y = top[1];
            if (w[x][y] == 'H') out.println(dis[x][y]);
            for (int i = 0; i < dir.length; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C 
                    || dis[nx][ny] != -1 || w[nx][ny] == '*') continue;
                queue.add(new int[]{nx, ny});
                dis[nx][ny] = dis[x][y]+1;
            }
        }
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}