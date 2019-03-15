// 1.emmmm it looks like so stupid... - O(N)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new LinkedList<>();
        if(matrix.length == 0) return ans;
        spiral(ans, matrix, 'r', 0, 0);
        return ans;
    }
    
    private void spiral(List<Integer> ans, int[][] matrix, char direction, int x, int y){
        if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] == Integer.MIN_VALUE) return;
        if(direction == 'r'){
            while(y < matrix[0].length && matrix[x][y] != Integer.MIN_VALUE){
                ans.add(matrix[x][y]);
                matrix[x][y] = Integer.MIN_VALUE;
                y++;
            }
            spiral(ans, matrix, 'd', x+1, y-1);
        }else if(direction == 'd'){
            while(x < matrix.length && matrix[x][y] != Integer.MIN_VALUE){
                ans.add(matrix[x][y]);
                matrix[x][y] = Integer.MIN_VALUE;
                x++;
            }
            spiral(ans, matrix, 'l', x-1, y-1);
        }else if(direction == 'l'){
            while(y >= 0 && matrix[x][y] != Integer.MIN_VALUE){
                ans.add(matrix[x][y]);
                matrix[x][y] = Integer.MIN_VALUE;
                y--;
            }
            spiral(ans, matrix, 'u', x-1, y+1);
        }else{ //'u'
            while(x >= 0 && matrix[x][y] != Integer.MIN_VALUE){
                ans.add(matrix[x][y]);
                matrix[x][y] = Integer.MIN_VALUE;
                x--;
            }
            spiral(ans, matrix, 'r', x+1, y+1);
        }
    }
} 

// Simulation ,the space complexity is O(N), N is the total number of elements
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]){
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }
}

// Layer-by-Layer
class Solution {
    public List < Integer > spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0)
            return ans;
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }
}