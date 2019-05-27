// 1.my wrong solution
    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0) return;
        int rows = board.length;
        int columns = board[0].length;
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(board[i][j] == 'O' && i!=0 && i!=rows-1 && j!=0 && j!=columns-1){
                    boolean[][] visited = new boolean[rows][columns];
                    recursive(rows, columns, i, j, board, visited);
                }
            }
        }
    }
    
    private boolean recursive(int rows, int columns, int r, int c, char[][] board, boolean[][] visited){
        // System.out.println(r+" "+c);
        if(r>=rows || c>=columns || visited[r][c] || board[r][c] == 'X') return false;
        visited[r][c] = true;
        if(r==0 || r==rows-1 || c==0 || c==columns-1){
            if(board[r][c] == 'O' ){
                return true;
            } 
            else return false;
        }
        if(recursive(rows, columns, r+1, c, board, visited) || recursive(rows, columns, r-1, c, board, visited) || recursive(rows, columns, r, c+1, board, visited) || recursive(rows, columns, r, c-1, board, visited)){
            return true;
        }else{
            board[r][c] = 'X';
            return false;
        }
    }
}

// 2.union-find - difficult to understand
class Solution {
    int[] unionSet;
    boolean[] hasEdgeO;
    
    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0) return;
        int rows = board.length, columns = board[0].length;
        unionSet = new int[rows*columns];
        hasEdgeO = new boolean[rows*columns];
        for(int i=0;i<unionSet.length;i++){
            unionSet[i] = i;
            int r = i / columns, c = i % columns;
            if(board[r][c] == 'O' && (r == 0 || r == rows-1 || c == 0 || c == columns-1)) hasEdgeO[i] = true;
        }
        for(int i=0;i<unionSet.length;i++){
            int r = i / columns, c = i % columns, upperx = r - 1, righty = c + 1; 
            if(board[r][c] == 'O'){
                if(upperx >=0 && board[r][c] == board[upperx][c]) union(i, i-columns);
                if(righty < columns && board[r][c] == board[r][righty]) union(i, i+1);
            }
        }
        for(int i=0;i<unionSet.length;i++){
             int r = i / columns, c = i % columns;
            if(board[r][c] == 'O' && !hasEdgeO[findroot(i)]) board[r][c] = 'X';
        }
    }
    
    private void union(int node1, int node2){
        int root1 = findroot(node1);
        int root2 = findroot(node2);
        boolean flag = hasEdgeO[root1] || hasEdgeO[root2];
        unionSet[root1] = root2;
        hasEdgeO[root2] = flag;
    }
    
    private int findroot(int node){
        if(unionSet[node] == node) return node;
        unionSet[node] = findroot(unionSet[node]);
        return unionSet[node];
    }
}

// 3.dfs - cooooooooooool!
class Solution {
    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0) return;
        int rows = board.length, columns = board[0].length;
        for(int i=0;i<columns;i++){
            dfs(board, 0, i);
            dfs(board, rows-1, i);
        }
        for(int i=0;i<rows;i++){
            dfs(board, i, 0);
            dfs(board, i, columns-1);
        }
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(board[i][j] == 'Q') board[i][j] = 'O';
                else if(board[i][j] == 'O') board[i][j] ='X';
            }
        }
    }
    
    private void dfs(char[][] board, int r, int c){
        if(r<0 || c<0 || r>=board.length || c>=board[0].length) return;
        if(board[r][c] == 'O'){
            board[r][c] = 'Q';
            dfs(board, r+1, c);
            dfs(board, r-1, c);
            dfs(board, r, c+1);
            dfs(board, r, c-1);
        }
    }
}