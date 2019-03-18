// 1.layer by layer
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int r1 = 0, r2 = n-1, c1 = 0, c2 = n-1, num = 1;
        while(r1<r2 && c1<c2){
            for(int i=c1;i<c2;i++) ans[r1][i] = num++;
            for(int i=r1;i<r2;i++) ans[i][c2] = num++;
            for(int i=c2;i>c1;i--) ans[r2][i] = num++;
            for(int i=r2;i>r1;i--) ans[i][c1] = num++;
            r1++;
            c1++;
            r2--;
            c2--;
        }
        if(r1==r2&&c1==c2) ans[r1][c1] = num++;
        return ans;
    }
}