import java.util.*;
import java.io.*;

class Main {

    static int idx;
    static int[][] son;
    static int[] cnt;
    
    public static void insert(String str) {
        int cur = 0;
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i)-'a';
            if (son[cur][c] == 0) {
                son[cur][c] = ++idx;
            }
            cur = son[cur][c];
        }
        cnt[cur]++;
    }

    public static int query(String str) {
        int cur = 0;
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i)-'a';
            if (son[cur][c]==0) return 0;
            cur = son[cur][c];
        }
        return cnt[cur];
    }

    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int MAX = (int)1e5+10;
        son = new int[MAX][26]; cnt = new int[MAX];
        int N = Integer.valueOf(br.readLine());
        while (N-- > 0) {
            String[] op = read(br);
            if ("I".equals(op[0])) {
                insert(op[1]);
            } else {
                out.println(query(op[1]));
            }
        }
        out.flush();
    }

    public static String[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).toArray(String[]::new);
    }
}

// import java.util.*;
// import java.io.*;

// class Main {

//     static Node root;

//     static class Node {
//         Node[] next;
//         int cnt;
//         public Node() {
//             next = new Node[26];
//         }
//     }
    
//     public static void insert(String str) {
//         Node cur = root;
//         int i = 0;
//         while (i < str.length()) {
//             int c = str.charAt(i++)-'a';
//             if (cur.next[c] == null) {
//                 cur.next[c] = new Node();
//             }
//             cur = cur.next[c];
//         }
//         cur.cnt++;
//     }

//     public static int query(String str) {
//         Node cur = root;
//         int i = 0;
//         while (i < str.length()) {
//             int c = str.charAt(i)-'a';
//             if (cur.next[c]==null) return 0;
//             cur = cur.next[c];
//             i++;
//         }
//         return cur.cnt;
//     }

//     public static void main(String... args) throws Exception {
//         PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
//         int N = Integer.valueOf(br.readLine());
//         root = new Node();
//         while (N-- > 0) {
//             String[] op = read(br);
//             if ("I".equals(op[0])) {
//                 insert(op[1]);
//             } else {
//                 out.println(query(op[1]));
//             }
//         }
//         out.flush();
//     }

//     public static String[] read(BufferedReader br) throws Exception {
//         return Arrays.stream(br.readLine().split(" ")).toArray(String[]::new);
//     }
// }