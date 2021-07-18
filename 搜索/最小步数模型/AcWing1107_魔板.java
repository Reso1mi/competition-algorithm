import java.util.*;
import java.io.*;

class Main {

    static class Node {
        String val;
        int step;
        char op;
        public Node(String v, int s, char o) {
            val = v; step = s; op = o;
        }
    }
    //2 6 8 4 5 7 3 1
    //1 2 3 4 5 6 7 8
    public static void main(String... args) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = read(br);
        String base = "12345678";
        HashMap<String, Node> pre = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(base, 0, 'N'));
        int min = 0;
        char last = '-';
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            String val = cur.val;
            int step = cur.step;
            if (target.equals(val)) {
                last = cur.op;
                min = step;
                break;
            }
            String oa = A(val), ob = B(val), oc = C(val);
            //想保证字典序最小只需要在搜索时候按顺序依次从A,B,C方案开始搜索就行了
            if (!pre.containsKey(oa)) {
                queue.add(new Node(oa, step+1, 'A'));
                pre.put(oa, cur);
            }
            if (!pre.containsKey(ob)) {
                queue.add(new Node(ob, step+1, 'B'));
                pre.put(ob, cur);
            }
            if (!pre.containsKey(oc)) {
                queue.add(new Node(oc, step+1, 'C'));
                pre.put(oc, cur);
            }
        }
        out.println(min);
        //目标倒推路径
        String temp = target;
        Node node = pre.get(temp);
        StringBuilder sb = new StringBuilder();
        sb.append(last); //不要直接传last进去，会被解析成数字
        while (node != null && node.op != 'N') {
            sb.append(node.op);
            temp = node.val;
            node = pre.get(temp);
        }
        out.print(min != 0 ? sb.reverse().toString() : "");
        out.flush();
    }

    public static String A(String s) {
        char[] t = s.toCharArray();
        swap(t, 0, 7); swap(t, 1, 6); 
        swap(t, 2, 5); swap(t, 3, 4);
        return new String(t);
    }

    public static String B(String s) {
        char[] t = s.toCharArray();
        swap(t, 3, 2); swap(t, 2, 1); swap(t, 1, 0);
        swap(t, 4, 5); swap(t, 5, 6); swap(t, 6, 7);
        return new String(t);
    }

    public static String C(String s) {
        char[] t = s.toCharArray();
        char temp = t[1];
        t[1] = t[6];
        t[6] = t[5];
        t[5] = t[2];
        t[2] = temp;
        return new String(t);
    }

    public static void swap(char[] cs, int a, int b) {
        char t = cs[a];
        cs[a] = cs[b];
        cs[b] = t;
    }

    public static String read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).reduce("", (a, b)->(a+b));
    }
}