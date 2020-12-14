/*
宠物小精灵是一部讲述小智和他的搭档皮卡丘一起冒险的故事。

一天，小智和皮卡丘来到了小精灵狩猎场，里面有很多珍贵的野生宠物小精灵。

小智也想收服其中的一些小精灵。

然而，野生的小精灵并不那么容易被收服。

对于每一个野生小精灵而言，小智可能需要使用很多个精灵球才能收服它，而在收服过程中，野生小精灵也会对皮卡丘造成一定的伤害（从而减少皮卡丘的体力）。

当皮卡丘的体力小于等于0时，小智就必须结束狩猎（因为他需要给皮卡丘疗伤），而使得皮卡丘体力小于等于0的野生小精灵也不会被小智收服。

当小智的精灵球用完时，狩猎也宣告结束。

我们假设小智遇到野生小精灵时有两个选择：收服它，或者离开它。

如果小智选择了收服，那么一定会扔出能够收服该小精灵的精灵球，而皮卡丘也一定会受到相应的伤害；如果选择离开它，那么小智不会损失精灵球，皮卡丘也不会损失体力。

小智的目标有两个：主要目标是收服尽可能多的野生小精灵；如果可以收服的小精灵数量一样，小智希望皮卡丘受到的伤害越小（剩余体力越大），因为他们还要继续冒险。

现在已知小智的精灵球数量和皮卡丘的初始体力，已知每一个小精灵需要的用于收服的精灵球数目和它在被收服过程中会对皮卡丘造成的伤害数目。

请问，小智该如何选择收服哪些小精灵以达到他的目标呢？

输入格式
输入数据的第一行包含三个整数：N，M，K，分别代表小智的精灵球数量、皮卡丘初始的体力值、野生小精灵的数量。

之后的K行，每一行代表一个野生小精灵，包括两个整数：收服该小精灵需要的精灵球的数量，以及收服过程中对皮卡丘造成的伤害。

输出格式
输出为一行，包含两个整数：C，R，分别表示最多收服C个小精灵，以及收服C个小精灵时皮卡丘的剩余体力值最多为R。

数据范围
0<N≤1000,
0<M≤500,
0<K≤100
输入样例1：
10 100 5
7 10
2 40
2 50
1 20
4 20
输出样例1：
3 30
输入样例2：
10 100 5
8 110
12 10
20 10
5 200
1 110
输出样例2：
0 100
 */
import java.io.*;// petr的输入模板
import java.util.*; 
import java.math.*; // 不是大数题可以不要这个
//本地测试
public class AcWing1022_宠物小精灵之收服 {
    public static void main(String... args) throws Exception{
        new Main().main();
    }
}

class Main {

    public static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String... args) throws Exception {
        //InputReader in = new InputReader(System.in);
        InputReader in = new InputReader(new FileInputStream("./input.txt"));
        int N = in.nextInt(); //精灵球个数
        int M = in.nextInt()-1; //皮卡丘体力值
        int K = in.nextInt();
        int[] costN = new int[K];
        int[] costM = new int[K];
        for (int i = 0; i < K; i++) {
            costN[i] = in.nextInt();
            costM[i] = in.nextInt();
        }
        int[] res = solveOpt(N, M, K, costN, costM);
        out.println(res[0]+" "+res[1]);
        out.flush();
        out.close();
    }

    //多维01背包 O(N*M*K) 5 x 10^7
    public static int[] solve(int N, int M, int K, int[] costN, int[] costM) {
        //dp[i][j]: 消耗最多i个精灵球和最多j点生命值，能捕捉的最多精灵数量
        int[][] dp = new int[N+1][M+1];
        for (int i = 0; i < K; i++) {
            for (int j = N; j >= costN[i]; j--) {
                for (int k = M; k >= costM[i]; k--) {
                    //注意这里N和M的cost都满足才能计算
                    dp[j][k] = Math.max(dp[j][k], dp[j-costN[i]][k-costM[i]] + 1);
                }
            }
        }
        int maxCnt = dp[N][M], minCost = 0;
        //枚举出捕获maxCnt个精灵球，消耗的最小生命值
        for (int i = 0; i <= M; i++) {
            if (dp[N][i] == maxCnt) {
                minCost = i;
                break;
            }
        }
        //最后+1，把之前的加回来
        return new int[]{maxCnt, M+1-minCost};
    }

    //交换维度，降低复杂度，O(K^2*N) = 10^7 还可以优化成 k^2*m
    public static int[] solveOpt(int N, int M, int K, int[] costN, int[] costM) {
        //dp[i][j]: 捕捉i个精灵，消耗j个精灵球，消耗的最少的体力值
        int[][] dp = new int[K+1][N+1];
        for (int i = 1; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
               dp[i][j] = 0x3f3f3f3f; 
            }
        }
        for (int i = 0; i < K; i++) {
            for (int j = K; j >= 1; j--) {
                for (int k = N; k >= costN[i]; k--) {
                    if (dp[j-1][k-costN[i]] + costM[i] <= M) {
                        dp[j][k] = Math.min(dp[j][k], dp[j-1][k-costN[i]]+costM[i]);
                    }
                }
            }
        }
        int maxCnt = 0;
        for (int i = K; i >= 0; i--) {
            if (dp[i][N] <= M) {
                maxCnt = i;
                break;
            }
        }
        //最后+1，把之前的加回来
        return new int[]{maxCnt, M+1-dp[maxCnt][N]};
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