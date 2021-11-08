import java.util.*;
import java.io.*;

class Main {

    static int idx;
    static int[] heap;
    // 堆中的索引映射到插入的顺序
    static int[] o2h;
    // 插入的顺序映射到堆中的索引
    static int[] h2o;
    static int N;
    static int size = 0;
    // 插入顺序
    static int j = 0;

    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    
    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        heap = new int[N];
        Arrays.fill(heap, 0x3f3f3f3f);
        o2h = new int[N];
        h2o = new int[N];
        while (N-- > 0) {
            String[] in = read(br);
            switch (in[0]) {
                case "I": insert(Integer.valueOf(in[1])); break;
                case "PM": printMin(); break;
                case "DM": delMin(); break;
                case "D": delK(Integer.valueOf(in[1])); break;
                case "C": change(Integer.valueOf(in[1]), Integer.valueOf(in[2])); break;
            }
        }
        out.flush();
    }

    public static void printMin() {
        out.println(heap[0]);
    }

    public static void delMin() {
        headSwap(0, --size);
        down(0);
    }

    public static void insert(int x) {
        idx++;
        heap[size] = x;
        o2h[idx] = size; 
        h2o[size] = idx;
        up(size);
        size++;
    }

    public static void delK(int k) {
        int hi = o2h[k];
        headSwap(hi, --size);
        down(hi);
        up(hi);
    }

    public static void change(int k, int x) {
        int hi = o2h[k];
        heap[hi] = x;
        up(hi);
        down(hi);
    }

    public static void up(int i) {
        // 无论左孩子，右孩子，父节点都是(i-1)/2
        int p = (i-1)/2;
        if (p < 0 || heap[p] <= heap[i]) return;
        headSwap(p, i);
        up(p);
    }

    //下移
    public static void down(int i) {
        int left = i*2+1;
        int right = left + 1;
        if (left >= size) return;
        int min = right < size && heap[right] <= heap[left] ? right : left;
        if (heap[min] >= heap[i]) return;
        headSwap(i, min);
        down(min);
    }


    public static void headSwap(int i, int j) {
        swap(heap, i, j);
        // 交换heap[i], heap[j]堆中的idx
        swap(o2h, h2o[i], h2o[j]);
        // 交换heap[i], heap[j]插入顺序
        swap(h2o, i, j);
    }

    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static String[] read(BufferedReader br) throws Exception {
        // return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        return br.readLine().split(" ");
    }
}

// public class AcWing839_模拟堆 {
//     public static void main(String[] args) throws Exception{
//         // 输入重定向
//         System.setIn(new FileInputStream("./input.txt"));
//         new Main().main();
//     }
// }