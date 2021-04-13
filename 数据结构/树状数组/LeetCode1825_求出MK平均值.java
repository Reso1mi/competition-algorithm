class MKAverage {

    int MAX = (int)1e5+10;
    int idx, m, k;
    int[] w;
    //小于等于w[i]的个数
    int[] cnt;
    //小于等于w[i]的元素和
    long[] sum;

    public int lowbit(int x) {
        return x & -x;
    }

    // 查询小于等于i的元素个数
    public int queryCnt(int i) {
        int res = 0;
        while (i > 0) {
            res += cnt[i];
            i -= lowbit(i);
        }
        return res;
    }

    // 查询小于等于i的元素和
    public long querySum(int i) {
        long res = 0;
        while (i > 0) {
            res += sum[i];
            i -= lowbit(i);
        }
        return res;
    }

    public void add(int i, int v) {
        int x = i; //保存元素值
        while (i < MAX) {
            cnt[i] += v; sum[i] += v*(long)x;
            i += lowbit(i);
        } 
    }

    //找到第k大的元素
    public int findK(int k) {
        int res = 0;
        int left = 1, right = MAX-1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            // 向大于等于k的最小值逼近
            if (queryCnt(mid) >= k) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public MKAverage(int m, int k) {
        idx = 0;
        this.m = m;
        this.k = k;
        w = new int[MAX];
        cnt = new int[MAX]; sum = new long[MAX];
    }
    
    public void addElement(int num) {
        w[++idx] = num;
        add(w[idx], 1);
        if (idx > m) { // 将前面的减掉，只保留后面的m个
            add(w[idx-m], -1);
        }
    }
    
    //m=4 k=1
    public int calculateMKAverage() {
        if (idx < m) return -1;
        int left = findK(k), right = findK(m-k);
        long sumL = querySum(left), sumR = querySum(right);
        // 小于等于left和right的到底有多少个，将重复的减出来
        long cntL = queryCnt(left), cntR = queryCnt(right);
        // 5 5 5, k=1, left=5, cntL = 3
        if (cntL > k) { // 说明left有重复的，多了cntL-k个
            sumL -= (cntL-k) * left;
        }
        if (cntR > m-k) {
            sumR -= (cntR-m+k) * right; 
        }
        return (int)((sumR-sumL)/(m-2*k));
    }
}

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */