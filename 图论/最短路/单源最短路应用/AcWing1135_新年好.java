import java.io.*;
import java.util.*;

class Main {
    
    static int idx;
    static int[] e, ne, h, w;
    //a->b 
    public static void add(int a, int b, int c){ 
        e[idx] = b; ne[idx] = h[a];
        w[idx] = c; h[a] = idx++;
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] in = read(br);
        int N = in[0], M = in[1];
        int[] tar = read(br)
        for (int i = 0; i < M; i++) {
            int[] _in = read(br);
            add(_in[0], _in[1], _in[2]);
            add(_in[1], _in[0], _in[2]);
        }
        //a,b,c,d,e 4*3*2×1=24
        PriorityQueue<Integer> pq = new PriorityQueue<>();


    }

    

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}


public class AcWing1135_新年好 {
    public static void main(String[] args) throws Exception{
        new Main().main();
    }
}