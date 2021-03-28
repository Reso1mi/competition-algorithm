class Solution {
    
    int idx;
    int[][] son;
    int[] cnt;
    public void insert(int a) {
        int cur = 0;
        for (int i = 15; i >= 0; i--) {
            int c = (a>>>i)&1;
            if (son[cur][c] == 0) {
                son[cur][c] = ++idx;
            }
            cur = son[cur][c];
            // 当前节点下面有多少个数字
            cnt[cur]++;
        }
    }

    public int query(int a, int limit) {
        int cur = 0, res = 0;
        for (int i = 15; i >= 0; i--) {
            int c = (a>>>i)&1, h = (limit>>>i)&1;
            if (c == 0 && h == 0) { //cur只能向0走，否则就超过limit了
                cur = son[cur][0];
            } else if (c == 0 && h == 1) { //cur向0走的都是小于limit的，统计下，然后向1走
                res += cnt[son[cur][0]];
                cur = son[cur][1];
            } else if (c == 1 && h == 0) { //cur只能向1走
                cur = son[cur][1];
            } else if (c == 1 && h == 1) { //同上
                res += cnt[son[cur][1]];
                cur = son[cur][0];
            }
            if (cur == 0) return res;
        }
        return res;
    }

    public int countPairs(int[] nums, int low, int high) {
        int N = nums.length;
        son = new int[17*N][2];
        cnt = new int[17*N];
        int res = 0;
        for (int i = 0; i < N; i++) {
            res += (query(nums[i], high+1)-query(nums[i], low));
            insert(nums[i]);
        }
        return res;
    }
}