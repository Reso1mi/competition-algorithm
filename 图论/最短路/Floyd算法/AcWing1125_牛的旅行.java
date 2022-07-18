import java.util.*;
import java.io.*;


class Main {

    public static void main(String... args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int INF = 0x3f3f3f3f;

        int N = read(br)[0];
        int[][] point = new int[N][2];
        for (int i = 0; i < N; i++) {
            point[i] = read(br);
        }

        double[][] w = new double[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(w[i], INF);
            w[i][i] = 0;
        }
        for (int i = 0; i < N; i++) {
            int[] t = read0(br);
            for (int j = 0; j < N; j++) {
                if (t[j] == 1){
                    w[i][j] = calDis(point, i, j);
                }
            }
        }

        // Floyd
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (w[i][k] == INF || w[k][j] == INF) continue;
                    w[i][j] = Math.min(w[i][j], w[i][k] + w[k][j]);
                }
            }
        }

        // 求未连通前最大直径
        double[] maxDis = new double[N];
        // 最大直径在一个牧场中
        double max = 0;
        for (int i = 0; i < N; i++) {
            double temp = 0;
            for (int j = 0; j < N; j++) {
                if (w[i][j] == INF) continue;
                temp = Math.max(temp, w[i][j]);
            }
            maxDis[i] = temp;
            max = Math.max(max, temp);
        }

        // Arrays.stream(maxDis).forEach(System.out::println);

        // 尝试连接两部分
        double mergeMin = INF;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (w[i][j] != INF) continue;
                mergeMin = Math.min(mergeMin, calDis(point, i, j) + maxDis[i] + maxDis[j]);
            }
        }

        System.out.printf("%.6f\n", Math.max(mergeMin, max));
    }

    public static double calDis(int[][] point, int i, int j) {
        return Math.sqrt((point[i][0]-point[j][0]) * (point[i][0]-point[j][0]) + 
                         (point[i][1]-point[j][1]) * (point[i][1]-point[j][1]));
    }


    public static int[] read(BufferedReader br) throws Exception{
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static int[] read0(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
    }
}

public class AcWing1125_牛的旅行 {
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./input.txt"));
        new Main().main();
    }
}