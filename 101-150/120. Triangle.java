// 1.backtracking - TLE - 42 / 43 test cases passed.
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        return backtracking(triangle, 0, 0);
    }
    
    private int backtracking(List<List<Integer>> triangle, int row, int curpos){
        if(row == triangle.size()) return 0;
        int left = backtracking(triangle, row+1, curpos);
        int right = backtracking(triangle, row+1, curpos+1);
        return triangle.get(row).get(curpos) + Math.min(left, right);
    }
}

// 2.bottom - up dp
// https://leetcode.com/problems/triangle/discuss/38730/DP-Solution-for-Triangle
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int dp[] = new int[size+1];
        for(int i=size-1;i>=0;i--){
            for(int j=0;j<=i;j++){
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
