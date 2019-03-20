// 1.Top-down Recursive DP - TLE - 34 / 43 test cases passed.
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] board = new int[m][n];
        return dp(board, obstacleGrid, m, n, 0, 0);
    }
    
    private int dp(int[][] board, int[][] obstacleGrid, int m, int n, int r, int c){
        if(r == m || c == n || obstacleGrid[r][c] == 1) return 0;
        if(board[r][c]!=0) return board[r][c];
        if(r == m-1 && c == n-1){
            board[r][c] = 1;
            return 1;
        }
        int n1 = dp(board, obstacleGrid, m, n, r+1, c);
        int n2 = dp(board, obstacleGrid, m, n, r, c+1);
        board[r][c] = n1 + n2;
        return board[r][c];
    }
}

// 2.Bottom-up Dp (Non-Recursive) 
// Time:O(M*N) Space:O(M*N)
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] board = new int[m][n];
        if(obstacleGrid[m-1][n-1] == 1) return 0;
        board[m-1][n-1] = 1;
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i==m-1 && j==n-1) continue;
                board[i][j] = (obstacleGrid[i][j] == 1) ? 0 : ((i+1>=m ? 0 : board[i+1][j]) + (j+1>=n ? 0 : board[i][j+1]));
            }
        }
        return board[0][0];
    }
}

// 3.based on the solution2, optimize its space complexity to O(1)
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(obstacleGrid[0][0]==1) return 0;
        obstacleGrid[0][0] = 1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0) continue;
                if(obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = 0;
                    continue;
                } 
                obstacleGrid[i][j] = (j-1<0 ? 0 : obstacleGrid[i][j-1]) + (i-1<0 ? 0 : obstacleGrid[i-1][j]);
            }
        }
        return obstacleGrid[m-1][n-1];
    }
}