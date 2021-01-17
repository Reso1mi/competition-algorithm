import java.util.*;
import java.io.*;

class Solution {


    int[] w;
    //小根堆和大根堆的和
    long minSum = 0l, maxSum = 0l;
    //存后1/2小的元素
    PriorityQueue<Integer> max = new PriorityQueue<>((a, b)->w[b]-w[a]);
    //存前1/2大的元素
    PriorityQueue<Integer> min = new PriorityQueue<>((a, b)->w[a]-w[b]);

    //n = 10^5
    public int[] numsGame(int[] nums) {
        int MOD = (int)1e9+7;
        int n = nums.length;
        w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = nums[i]-i;
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            add(i);
            int mid = find();
            //这里取模WA了两发...int后面没打括号，给强转了
            //小根堆的个数以及sum代表的实际是大于mid的元素部分，大根堆同理
            res[i] = (int)(((long)w[mid]*(max.size()-min.size()) - maxSum + minSum) % MOD);
        }
        return res;
    }

    //维护大根堆以及小根堆
    public void add(int x) {
        min.add(x);
        minSum += w[x];
        minSum -= w[min.peek()]; maxSum += w[min.peek()];
        max.add(min.poll());
        if (min.size() < max.size()) {
            minSum += w[max.peek()]; maxSum -= w[max.peek()];
            min.add(max.poll());
        }
    }

    public int find() {
        return min.peek();
    }

}

public class LeetCode_LCP24数字游戏 {
    public static void main(String[] args) {
        Arrays.stream(new Solution().numsGame(new int[]{3,4,5,1,6,7})).forEach(System.out::println);
    }
}