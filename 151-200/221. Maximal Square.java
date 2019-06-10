// 1.based on lc85 solution 1
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int ans = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j] == '1'){
                    dp[i][j] = (j==0) ? 1 : dp[i][j-1] + 1;
                    int width = dp[i][j], height = 0;
                    for(int k=i;k>=0;k--){
                        height++;
                        if(width < height || dp[k][j] == 0) break;
                        width = Math.min(width, dp[k][j]);
                        if(width == height) ans = Math.max(ans, width*height);
                    }
                }
            }
        }
        return ans;
    }
}

// 2.based on lc85 solution 3
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int ans = 0, row = matrix.length, column = matrix[0].length;
        int[] height = new int[column];
        int[] left = new int[column];
        int[] right = new int[column];
        Arrays.fill(right, column);
        for(int i=0;i<row;i++){
            int curleft = 0, curright = column;
            for(int j=0;j<column;j++){
                if(matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
            for(int j=0;j<column;j++){
                if(matrix[i][j] == '1') left[j] = Math.max(left[j], curleft);
                else{left[j] = 0; curleft = j+1;}
            }
            for(int j=column-1;j>=0;j--){
                if(matrix[i][j] == '1') right[j] = Math.min(right[j], curright);
                else{right[j] = column; curright = j;}
            }
            for(int j=0;j<column;j++){
                if(right[j]-left[j] >= height[j]) ans = Math.max(ans, height[j]*height[j]);
            }
        }
        return ans;
    }
}

// 3.dp - O(N^M) - best solution
// represents the side length of the maximum square whose bottom right corner is the cell with index (i,j) in the original matrix.
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int ans = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j] == '1'){
                    if(i==0 || j==0) dp[i][j] = 1;
                    else dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans*ans;
    }
}

// 4.space optimization based on solution 3
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int ans = 0, pre = 0;
        int[] dp = new int[matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                int tmp = dp[j];
                if(matrix[i][j] == '1'){
                    if(i==0 || j==0) dp[j] = 1;
                    else dp[j] = Math.min(pre, Math.min(dp[j], dp[j-1])) + 1;
                    ans = Math.max(ans, dp[j]);
                }else{
                    dp[j] = 0;
                }
                pre = tmp;
            }
        }
        return ans*ans;
    }
}





