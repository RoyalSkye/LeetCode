// 1.Bottom-up Dp (Non-Recursive) 
// Time:O(M*N) Space:O(M*N)
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid.length==0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] path = new int[m][n];
        path[m-1][n-1] = grid[m-1][n-1];
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i==m-1 && j==n-1) continue;
                int down = (i+1>=m) ? Integer.MAX_VALUE : path[i+1][j];
                int left = (j+1>=n) ? Integer.MAX_VALUE : path[i][j+1];
                path[i][j] = grid[i][j] + Math.min(down, left);
            }
        }
        return path[0][0];
    }
}

// 2.based on the solution2, optimize its space complexity to O(1)
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid.length==0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i==m-1 && j==n-1) continue;
                int down = (i+1>=m) ? Integer.MAX_VALUE : grid[i+1][j];
                int left = (j+1>=n) ? Integer.MAX_VALUE : grid[i][j+1];
                grid[i][j] = grid[i][j] + Math.min(down, left);
            }
        }
        return grid[0][0];
    }
}