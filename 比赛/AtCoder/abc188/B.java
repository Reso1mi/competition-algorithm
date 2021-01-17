import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int N = Integer.valueOf(br.readLine());        
        int[] A = read(br);
        int[] B = read(br);
        long res = 0;
        for (int i = 0; i < N; i++) {
            res += A[i]*B[i];
        }
        System.out.println(res==0?"Yes":"No");
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}