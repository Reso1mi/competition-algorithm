import java.util.*;
import java.io.*;// petr的输入模板
import java.math.*; // 不是大数题可以不要这个

//http://acm.hdu.edu.cn/showproblem.php?pid=4501
class Main {

    public static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception{
        InputReader in = new InputReader(System.in);
        //InputReader in = new InputReader(new FileInputStream("./input.txt"));
        while(!in.EOF()) {
            int n = in.nextInt();
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            int k = in.nextInt();
            int[][] cost = new int[n][3];
            for (int i = 0; i < n; i++) {
                cost[i][0] = in.nextInt();
                cost[i][1] = in.nextInt();
                cost[i][2] = in.nextInt();
            }
            solve(n, v1, v2, k, cost);
        }
        //别忘了flush
        out.flush();
        out.close();
    }

    //因为数据量不大，就直接Scanner了
    public static void solve(int n, int v1, int v2, int k, int[][] cost) {
        int[][][] dp = new int[k+1][v1+1][v2+1];
        for (int i = 0; i < n; i++) {
            for (int j = k; j >= 0; j--) {
                for (int u = v1; u >= 0; u--) {
                    for (int w = v2; w >= 0; w--) {
                        //这里不能直接u>=cost[i][0] w >= cost[i][1]，因为积分和钱和免费拿是分开的，没有关联的
                        //即使我不能免费拿，但是我能用积分拿，即使不能用积分拿，我可以用钱买
                        //dp[j][u][w] = Math.max(dp[j][u][w], dp[j-1][u-cost[i][0]][w-cost[i][1]] + cost[i][2]);
                        int ans = 0;
                        if (j >= 1) { //免费拿
                            ans = Math.max(ans, dp[j-1][u][w] + cost[i][2]);
                        }
                        if (u >= cost[i][0]) { //钱
                            ans = Math.max(ans, dp[j][u-cost[i][0]][w] + cost[i][2]);
                        }
                        if (w >= cost[i][1]) { //积分
                            ans = Math.max(ans, dp[j][u][w-cost[i][1]] + cost[i][2]);
                        }
                        dp[j][u][w] = Math.max(ans, dp[j][u][w]);
                    }
                }
            }
        }
        out.println(dp[k][v1][v2]);
    }
}


class InputReader {

    public BufferedReader reader;
    
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        //char[32768]
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    //默认以" "作为分隔符，读一个
    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    //有的题目不给有多少组测试用例，只能一直读，读到结尾，需要自己判断结束
    //该函数也会读取一行，并初始化tokenizer，后序直接nextInt..等就可以读到该行
    public boolean EOF() {
        String str = null;
        try {
            str = reader.readLine();
            if (str == null) {
                return true;
            }
            //创建tokenizer
            tokenizer = new StringTokenizer(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    int nextInt(){
        return Integer.parseInt(next());
    }
    
    long nextLong(){
        return Long.parseLong(next());
    }
    
    double nextDouble(){
        return Double.parseDouble(next());
    }
    
    BigInteger nextBigInteger(){
        return new BigInteger(next());
    }

    BigDecimal nextBigDecimal(){
        return new BigDecimal(next());
    }
}