// 1.O(N^2) 先以矩阵对称轴翻转，然后以纵中轴翻转 or 先以横中轴翻转，再以矩阵对称轴翻转
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n/2;j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }
        }
    }
}

// 2.rotate by each circle
// (i,j) -> (j,n-1-i) -> (n-1-i,n-1-j) -> (n-1-j,i) -> (i,j)
// https://leetcode.windliang.cc/leetCode-48-Rotate-Image.html
class Solution {
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        for (int i=0; i<n/2; i++){  //2 or 3 内circle无需翻转; 4 or 5 内圈(2*2 or 3*3)1次; 6 inner(4*4)->(2*2)
            for (int j=i; j<n-i-1; j++) {
                int tmp=matrix[i][j];
                matrix[i][j]=matrix[n-j-1][i];
                matrix[n-j-1][i]=matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1]=matrix[j][n-i-1];
                matrix[j][n-i-1]=tmp;
            }
        }
    }
}