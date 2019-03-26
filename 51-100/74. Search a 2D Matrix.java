// 1.search for row, then column
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row == 0) return false;
        int column = matrix[0].length;
        if(column == 0) return false;
        int ans = Integer.MIN_VALUE;
        for(int i=0;i<row;i++){
            if(matrix[i][0] == target) return true;
            if(matrix[i][0] > target){
                ans = i - 1;
                break;
            }
        }
        if(ans == -1) return false;
        if(ans == Integer.MIN_VALUE){ //search in the last row
            for(int i=0;i<column;i++){
                if(matrix[row-1][i] == target) return true;
            }
        }
        else{
            for(int i=0;i<column;i++){
                if(matrix[ans][i] == target) return true;
            }
        }
        return false;
    }
}

// 2.binary search
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row == 0) return false;
        int column = matrix[0].length;
        if(column == 0) return false;
        if(matrix[0][0] > target || matrix[row-1][column-1] < target) return false;
        int low = 0, high = row*column - 1;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(matrix[mid/column][mid%column] == target) return true;
            if(matrix[mid/column][mid%column] > target) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }
}