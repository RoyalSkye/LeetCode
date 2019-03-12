// 1.backtracking
public class Solution {
    public void solveSudoku(char[][] board) {  
        solve(board);
    }
    
    private boolean solve(char[][] board){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j] == '.'){
                    for(char c='1';c<='9';c++){
                        if(isValid(board,c,i,j)){  //may succeed
                            board[i][j] = c;
                            if(solve(board)) return true;
                            else{  //backtracking
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, char c, int i, int j){
        for(int k=0;k<9;k++){
            if(board[i][k] == c || board[k][j] == c || board[(i/3)*3+k/3][(j/3)*3+k%3] == c) return false;
        }
        return true;
    }
    
 or

// this solution is better than the first one - in the first solution, we need to search '.' pos from the beginning of the sudoko every time we call solve()
public class Solution {  
    public void solveSudoku(char[][] board) {
        dfs(board,0);
    }
    private boolean dfs(char[][] board, int d) {
        if (d==81) return true; //found solution
        int i=d/9, j=d%9;
        if (board[i][j]!='.') return dfs(board,d+1);//prefill number skip
        
        boolean[] flag=new boolean[10];
        validate(board,i,j,flag);
        for (int k=1; k<=9; k++) {
            if (flag[k]) {
                board[i][j]=(char)('0'+k);
                if (dfs(board,d+1)) return true;
            }
        }
        board[i][j]='.'; //if can not solve, in the wrong path, change back to '.' and out
        return false;
    }
    private void validate(char[][] board, int i, int j, boolean[] flag) {
        Arrays.fill(flag,true);
        for (int k=0; k<9; k++) {
            if (board[i][k]!='.') flag[board[i][k]-'0']=false;
            if (board[k][j]!='.') flag[board[k][j]-'0']=false;
            int r=i/3*3+k/3;
            int c=j/3*3+k%3;
            if (board[r][c]!='.') flag[board[r][c]-'0']=false;
        }
    }
}