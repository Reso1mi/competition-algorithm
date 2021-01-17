import java.util.*;
import java.io.*;

class Solution {
    /**
     * @param grid: a 2D grid
     * @return: the minimize travel distance
     */
    public int minTotalDistance(int[][] grid) {
        // Write your code here
        int n = grid.length;
        int m = grid[0].length;
        List<Integer> xlis = new ArrayList<>();
        List<Integer> ylis = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    xlis.add(i);
                    ylis.add(j);
                }
            }
        }
        Collections.sort(xlis);
        Collections.sort(ylis);
        int xmid = xlis.get(xlis.size()>>1);
        int ymid = ylis.get(ylis.size()>>1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    res += Math.abs(i-xmid) + Math.abs(j-ymid);      
                }
            }
        }
        return res;
    }
}