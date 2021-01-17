import java.util.*;
import java.io.*;

/*
差分模板题
 */
class Solution {
    public int[] corpFlightBookings(int[][] b, int n) {
        int[] diff = new int[n+1];
        //diff[0]+..+diff[i] = res[i]
        //[left,right]+C ==> diff[left]+C diff[right+1]-C
        for (int i = 0; i < b.length; i++) {
            diff[b[i][0]-1] += b[i][2];
            diff[b[i][1]] -= b[i][2];
        }
        int[] res = new int[n];
        res[0] = diff[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i-1] + diff[i];
        }
        return res;
    }
}