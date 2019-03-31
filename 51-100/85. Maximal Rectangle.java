// 1.dp - O(N^2*M)
// each max rectangle with lower right corner(i,j)
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int ans = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j] == '1'){
                    dp[i][j] = (j == 0) ? 1 : dp[i][j-1] + 1;
                    int width = dp[i][j];
                    for(int k=i;k>=0;k--){
                    	if(dp[k][j] == 0) break;  // avoid useless iterating, from 38 to 5 ms
                        width = Math.min(width, dp[k][j]);  //be careful!
                        ans = Math.max(ans, width*(i-k+1));
                    }
                }
            }
        }
        return ans;
    }
}

// 2.use Stack based on lc84
// Time: O(N*M) Space: O(N*M)
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int ans = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j] == '1'){
                    dp[i][j] = (i == 0) ? 1 : dp[i-1][j] + 1;
                }
            }
            ans = Math.max(ans, largestRectangleArea(dp[i]));
        }
        return ans;
    }
    
    private int largestRectangleArea(int[] heights) {
        if(heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);stack.push(0);
        int max = 0, left = -1;
        for(int i=1;i<heights.length;i++){
            while(stack.peek()!=-1 && heights[stack.peek()] >= heights[i]){  
                max = Math.max(max, heights[stack.pop()]*(i-stack.peek()-1));
            } 
            stack.push(i);
        }
        while(stack.peek()!=-1){
            max = Math.max(max, heights[stack.pop()]*(heights.length-stack.peek()-1));
        }
        return max;
    }
}

// 3.Dynamic Programming - Maximum Height at Each Point - expand height then expand left and right
// Time: O(N*M) Space: O(M)
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] left = new int[n]; // initialize left as the leftmost boundary possible
        int[] right = new int[n];
        int[] height = new int[n];
        Arrays.fill(right, n); // initialize right as the rightmost boundary possible
        int maxarea = 0;
        for(int i = 0; i < m; i++) {
            int cur_left = 0, cur_right = n;
            // update height
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
            // update left
            for(int j=0; j<n; j++) {
                if(matrix[i][j]=='1') left[j]=Math.max(left[j],cur_left);
                else {left[j]=0; cur_left=j+1;}
            }
            // update right
            for(int j = n - 1; j >= 0; j--) {
                if(matrix[i][j] == '1') right[j] = Math.min(right[j], cur_right);
                else {right[j] = n; cur_right = j;}    
            }
            // update area
            for(int j = 0; j < n; j++) {
                maxarea = Math.max(maxarea, (right[j] - left[j]) * height[j]);
            }
        }
        return maxarea;
    }
}