// 1.first attempt - backtracking, but it can only find one solution
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        int[][] chessboard = new int[n][n];
        backtracking(new ArrayList<String>(), ans, chessboard, n, 0);
        return ans;
    }
    
    private boolean backtracking(List<String> res, List<List<String>> ans, int[][] chessboard, int n, int row){
        if(row == n){
            ans.add(res);
            return true;
        } 
        for(int i=0;i<n;i++){
            if(isValid(chessboard, row, i)){
                //set String res 
                StringBuilder s = new StringBuilder("");
                int tmp = i, remain = n - i - 1;
                while(tmp-->0) s.append('.');
                s.append('Q');
                while(remain-->0) s.append('.');
                res.add(row, s.toString());
                //update array
                chessboard[row][i] = 1;
                if(backtracking(res, ans, chessboard, n, row+1)){
                    return true;
                } 
                //backrecking
                res.remove(row);
                chessboard[row][i] = 0;
            }
        }
        return false;
    }
    
    private boolean isValid(int[][] chessboard, int row, int column){
        for(int i=0;i<chessboard[0].length;i++){
            if(chessboard[row][i] == 1 || chessboard[i][column] == 1) return false;
            else if(row-i>=0 && column-i>=0 && chessboard[row-i][column-i]==1) return false;
            else if(row-i>=0 && column+i<=chessboard[0].length-1 && chessboard[row-i][column+i]==1) return false;
            else if(row+i<=chessboard[0].length-1 && column-i>=0 && chessboard[row+i][column-i]==1) return false;
            else if(row+i<=chessboard[0].length-1 && column+i<=chessboard[0].length-1 && chessboard[row+i][column+i]==1) return false;
        }
        return true;
    }
}

// -修改后:
// 看了一下别人写的，感觉自己很蠢，backtracking/dfs函数无需返回值，这样就可以遍历到所有解。

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        int[][] chessboard = new int[n][n];
        backtracking(new ArrayList<String>(), ans, chessboard, n, 0);
        return ans;
    }
    
    private void backtracking(List<String> res, List<List<String>> ans, int[][] chessboard, int n, int row){
        if(row == n){
            ans.add(new ArrayList(res));
            return;
        } 
        for(int i=0;i<n;i++){
            if(isValid(chessboard, row, i)){
                //set String res 
                StringBuilder s = new StringBuilder("");
                int tmp = i, remain = n - i - 1;
                while(tmp-->0) s.append('.');
                s.append('Q');
                while(remain-->0) s.append('.');
                res.add(row, s.toString());
                //update array
                chessboard[row][i] = 1;
                backtracking(res, ans, chessboard, n, row+1);
                //backrecking
                res.remove(row);
                chessboard[row][i] = 0;
            }
        }
    }
    
    private boolean isValid(int[][] chessboard, int row, int column){
        for(int i=0;i<chessboard[0].length;i++){
            if(chessboard[row][i] == 1 || chessboard[i][column] == 1) return false;
            else if(row-i>=0 && column-i>=0 && chessboard[row-i][column-i]==1) return false;
            else if(row-i>=0 && column+i<=chessboard[0].length-1 && chessboard[row-i][column+i]==1) return false;
            else if(row+i<=chessboard[0].length-1 && column-i>=0 && chessboard[row+i][column-i]==1) return false;
            else if(row+i<=chessboard[0].length-1 && column+i<=chessboard[0].length-1 && chessboard[row+i][column+i]==1) return false;
        }
        return true;
    }
}

//->用char数组无需每次new StringBuilder -> final version

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] board = new char[n][n];
        System.out.println(board.length);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                board[i][j] = '.';
        }
        backtracking(ans, board, 0);
        return ans;
    }
    
    private void backtracking(List<List<String>> ans, char[][] chessboard, int row){
        if(row == chessboard.length){
            ans.add(construct(chessboard));
            return;
        } 
        for(int i=0;i<chessboard.length;i++){
            if(isValid(chessboard, row, i)){
                chessboard[row][i] = 'Q';
                backtracking(ans, chessboard, row+1);
                chessboard[row][i] = '.';
            }
        }
    }
    
    private boolean isValid(char[][] chessboard, int row, int column){
        for(int i=0;i<chessboard.length;i++){
            if(chessboard[row][i] == 'Q' || chessboard[i][column] == 'Q') return false;
            else if(row-i>=0 && column-i>=0 && chessboard[row-i][column-i]=='Q') return false;
            else if(row-i>=0 && column+i<=chessboard.length-1 && chessboard[row-i][column+i]=='Q') return false;
            else if(row+i<=chessboard.length-1 && column-i>=0 && chessboard[row+i][column-i]=='Q') return false;
            else if(row+i<=chessboard.length-1 && column+i<=chessboard.length-1 && chessboard[row+i][column+i]=='Q') return false;
        }
        return true;
    }
    
    private List<String> construct(char[][] chessboard) {
        List<String> res = new LinkedList<String>();
        for(int i = 0; i < chessboard.length; i++) {
            String s = new String(chessboard[i]);
            res.add(s);
        }
        return res;
    }
}

// 2.use boolean array
// For all "hill" diagonals(/) row + column = const, and for all "dale" diagonals(\) row - column = const.
class Solution {
    int count = 0;

    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];     // columns   |
        boolean[] d1 = new boolean[2 * n];   // diagonals \  -(n-1) <= column - row <= n-1
        boolean[] d2 = new boolean[2 * n];   // diagonals /  0 <= column + row <= 2(n-1)
        backtracking(0, cols, d1, d2, n);
        return count;
    }
    
    public void backtracking(int row, boolean[] cols, boolean[] d1, boolean []d2, int n) {
        if(row == n){
            count++;
            return;
        } 
        for(int col = 0; col < n; col++) {
            int id1 = col - row + n;
            int id2 = col + row;
            if(cols[col] || d1[id1] || d2[id2]) continue; //attack!
            cols[col] = true; d1[id1] = true; d2[id2] = true;
            backtracking(row + 1, cols, d1, d2, n);
            cols[col] = false; d1[id1] = false; d2[id2] = false;
        }
    }
}
