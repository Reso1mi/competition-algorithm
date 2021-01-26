import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        //2 4 4 9 4 9
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int N = read(br)[0];
        int[] w = new int[N+1];
        System.arraycopy(read(br), 0, w, 0, N);
        w[N] = -1;
        //单调递增栈
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i <= N; i++) {
            while (!stack.isEmpty() && w[stack.peek()] > w[i]) {
                int cur = stack.pop();
                //向左最多扩展到left+1，向右最多扩展到i-1 (i-1-left-1+1)
                int left = stack.isEmpty() ? -1 : stack.peek();
                res = Math.max(res, (i-1-left)*w[cur]);
            }
            stack.push(i);
        }
        System.out.println(res);
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

// class Main {

//     public static void main(String... args) throws Exception {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int N = read(br)[0];
//         int[] w = read(br);
//         int res = 0;
//         for (int i = 0; i < N; i++) {
//             if (i > 0 && w[i] == w[i-1]) continue;
//             int min = Integer.MAX_VALUE;
//             for (int j = i; j < N; j++) {
//                 min = Math.min(w[j], min);
//                 res = Math.max(res, min*(j-i+1));
//             }
//         }
//         System.out.println(res);
//     }

//     public static int[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//     }
// }

public class C {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}