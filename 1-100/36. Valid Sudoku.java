// 1.my stupid solution
class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean ans = true;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        Map<Character, Integer> map3 = new HashMap<>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]!='.'){
                    map1.put(board[i][j], map1.getOrDefault(board[i][j],0)+1);
                }
                if(board[j][i]!='.'){
                    map2.put(board[j][i], map2.getOrDefault(board[j][i],0)+1);
                }
                if(board[j/3+i/3*3][j%3+(i%3)*3]!='.'){
                    map3.put(board[j/3+i/3*3][j%3+(i%3)*3], map3.getOrDefault(board[j/3+i/3*3][j%3+(i%3)*3],0)+1);
                }
            }
            if(!isValid(map1)) return false;
            if(!isValid(map2)) return false;
            if(!isValid(map3)) return false;
            map1.clear();
            map2.clear();
            map3.clear();
        }
        return ans;
    }
    
    private boolean isValid(Map<Character, Integer> map){
        boolean res = true;
        for(int i : map.values()){
            if(i>1) return false;
        }
        return res;
    }
}

// 2.more readable - one iteration
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Integer>[] rows = new HashMap[9];
        Map<Integer, Integer>[] columns = new HashMap[9];
        Map<Integer, Integer>[] boxes = new HashMap[9];
        for(int i=0;i<9;i++){
            rows[i] = new HashMap<Integer, Integer>();
            columns[i] = new HashMap<Integer, Integer>();
            boxes[i] = new HashMap<Integer, Integer>();
        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                char num = board[i][j];
                if(num != '.'){
                    int n = (int)num;
                    rows[i].put(n,rows[i].getOrDefault(n,0)+1);
                    columns[j].put(n,columns[j].getOrDefault(n,0)+1);
                    boxes[i/3*3+j/3].put(n,boxes[i/3*3+j/3].getOrDefault(n,0)+1);
                    if(rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[i/3*3+j/3].get(n) > 1) return false;
                }
            }
        }
        return true;
    }
}

// more readable -set.add()
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set seen = new HashSet();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];
                if (number != '.'){
                    if (!seen.add(number + " in row " + i) ||
                        !seen.add(number + " in column " + j) ||
                        !seen.add(number + " in block " + i/3 + "-" + j/3))
                        return false;
                }
            }
        }
        return true;
    }
}