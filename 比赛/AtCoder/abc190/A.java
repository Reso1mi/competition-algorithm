import java.util.*;
import java.io.*;

class Main {

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int[] in = read(br);
        int A = in[0], B = in[1], C = in[2];
        if (C == 0) {
            if (A-1 >= B) out.println("Takahashi");
            else out.println("Aoki");
        } else {
            if (B-1 >= A) out.println("Aoki");
            else out.println("Takahashi");
        }
        out.flush();
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class A {
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 3; i++) {
            new Main().main();
        }
    }
}