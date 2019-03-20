// 1.backtracking - TLE - 37 / 62 test cases passed.
class Solution {
    public int uniquePaths(int m, int n) {
        return backtracking(m, n, 0, 0);
    }
    
    private int backtracking(int m, int n, int r, int c){
        if(r == m || c == n) return 0;
        if(r == m-1 && c == n-1) return 1;
        int n1 = backtracking(m, n, r+1, c);
        int n2 = backtracking(m, n, r, c+1);
        return n1 + n2;
    }
}

// 2.Top-down Recursive DP
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] board = new int[m][n];
        board[m-1][n-1] = 1;
        return backtracking(board, m, n, 0, 0);
    }
    
    private int backtracking(int[][] board, int m, int n, int r, int c){
        if(r == m || c == n) return 0;
        if(board[r][c] != 0) return board[r][c];
        int n1 = backtracking(board, m, n, r+1, c);
        int n2 = backtracking(board, m, n, r, c+1);
        board[r][c] = n1 + n2;
        return board[r][c];
    }
}

// 3.Bottom-up Dp (Non-Recursive)
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] board = new int[m][n];
        board[m-1][n-1] = 1;
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i==m-1&&j==n-1) continue;
                board[i][j] = ((i+1>=m) ? 0: board[i+1][j]) + ((j+1>=n) ? 0 : board[i][j+1]);
            }
        }
        return board[0][0];
    }
}