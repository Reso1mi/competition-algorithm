import java.util.*;
import java.io.*;

class Main {

    //2^16 = 65536
    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./input.txt")));
        int N = Integer.valueOf(br.readLine());
        int[] w = read(br);
        LinkedList<Player> queue = new LinkedList<>();
        for (int i = 0; i < (1<<N); i++) {
            queue.addLast(new Player(i+1, w[i]));
        }
        while (queue.size() > 2) {
            Player p1 = queue.removeFirst();
            Player p2 = queue.removeFirst();
            queue.addLast(p1.rank > p2.rank ? p1 : p2);
        }
        Player p1 = queue.get(0);
        Player p2 = queue.get(1);
        System.out.println(p1.rank > p2.rank ? p2.i : p1.i);
    }

    static class Player{
        int i;
        int rank;
        public Player (int i, int rank) {
            this.rank = rank;
            this.i = i;
        }
    }

    public static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}

public class C {
    public static void main(String[] args) throws Exception {
        new Main().main();
    }
}