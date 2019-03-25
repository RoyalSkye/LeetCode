// 1.time: O(m*n) space: O(m + n) 
class Solution {
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        Set<Integer> r = new HashSet<>();
        Set<Integer> c = new HashSet<>();
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(matrix[i][j] == 0){
                    if(!r.contains(i)) r.add(i);
                    if(!c.contains(j)) c.add(j);
                }
            }
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(r.contains(i) || c.contains(j)) matrix[i][j] = 0;
            }
        }
    }
}

// 2.time: O(m*n) space: O(1) 
class Solution {
    public void setZeroes(int[][] matrix) {
        Boolean isCol = false;  //mark for Column 0
        int row = matrix.length;
        int column = matrix[0].length;
        for(int i=0;i<row;i++){
            if(matrix[i][0] == 0) isCol = true; // matrix[0][0] for row 0
            for(int j=1;j<column;j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int i=1;i<row;i++){
            for(int j=1;j<column;j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
            }
        }
        if(matrix[0][0] == 0){
            for(int i=0;i<column;i++) matrix[0][i] = 0;
        }
        if(isCol){
            for(int i=0;i<row;i++) matrix[i][0] = 0;
        }
    }
}