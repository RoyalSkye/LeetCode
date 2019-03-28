// 1.backtracking - 22ms
class Solution {
    public boolean exist(char[][] board, String word) { 
        if(word.length() == 0) return true; 
        int row = board.length, column = board[0].length;
        if(row == 0 || column == 0) return false;
        char c = word.charAt(0);
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(board[i][j] == c){
                    int[][] checked = new int[row][column];
                    if(backtracking(board, checked, word, i, j)) return true;
                }
            }
        }
        return false;
    }
    
    private boolean backtracking(char[][] board, int[][] checked, String word, int i, int j){
        if(word.length() == 0) return true;
        char c = word.charAt(0);
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if(checked[i][j] == 1 || board[i][j] != c) return false;
        checked[i][j] = 1;
        if(backtracking(board, checked, word.substring(1), i+1, j)) return true;
        if(backtracking(board, checked, word.substring(1), i-1, j)) return true;
        if(backtracking(board, checked, word.substring(1), i, j+1)) return true;
        if(backtracking(board, checked, word.substring(1), i, j-1)) return true;
        checked[i][j] = 0;
        return false;
    }
}

//or do not use extra space - 4ms

class Solution {
    public boolean exist(char[][] board, String word) { 
        if(word.length() == 0) return true; 
        int row = board.length, column = board[0].length;
        if(row == 0 || column == 0) return false;
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(backtracking(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }
    
    private boolean backtracking(char[][] board, String word, int i, int j, int cur){
        if(cur == word.length()) return true;
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if(board[i][j] == word.charAt(cur)){
            char c = board[i][j];
            board[i][j] = '#';  //set visited, if return to this pos, must be false
            boolean ans = backtracking(board, word, i+1, j, cur+1) || backtracking(board, word, i-1, j, cur+1) || backtracking(board, word, i, j+1, cur+1) || backtracking(board, word, i, j-1, cur+1);
            board[i][j] = c;
            return ans;
        }
        return false;
    }
}