// 1.dfs
class Solution {
    public int numIslands(char[][] grid) {
        int ans = 0;
        if(grid.length==0 || grid[0].length==0) return ans;
        int r = grid.length, c = grid[0].length;
        boolean[][] visited = new boolean[r][c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(!visited[i][j] && grid[i][j] == '1'){
                    dfs(grid, visited, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }
    
    private void dfs(char[][] grid, boolean[][] visited, int row, int column){
        if(row < 0 || row >= grid.length || column < 0 || column >= grid[0].length || grid[row][column] == '0' || visited[row][column]) return;
        visited[row][column] = true;
        dfs(grid, visited, row+1, column);
        dfs(grid, visited, row-1, column);
        dfs(grid, visited, row, column+1);
        dfs(grid, visited, row, column-1);
    }
}

// or without visited array - Sink and count the islands - grid[r][c] = '0' 

// 2.BFS
class Solution {
    public int numIslands(char[][] grid) {
        int ans = 0;
        if(grid.length==0 || grid[0].length==0) return ans;
        int r = grid.length, c = grid[0].length;
        char[][] copy = new char[r][c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                copy[i][j] = grid[i][j];
            }
        }
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(copy[i][j] == '1'){
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(i*c+j);
                    copy[i][j] = '0';
                    while(!queue.isEmpty()){
                        int cur = queue.remove();
                        int row = cur / c, column = cur % c;
                        if(row+1<=r-1 && copy[row+1][column]=='1'){
                            queue.add((row+1)*c+column);
                            copy[row+1][column] = '0';
                        }
                        if(row-1>=0 && copy[row-1][column]=='1'){
                            queue.add((row-1)*c+column);
                            copy[row-1][column] = '0';
                        }
                        if(column+1<=c-1 && copy[row][column+1]=='1'){
                            queue.add(row*c+column+1);
                            copy[row][column+1] = '0';
                        }
                        if(column-1>=0 && copy[row][column-1]=='1'){
                            queue.add(row*c+column-1);
                            copy[row][column-1] = '0';
                        }
                    }
                    ans++;
                }
            }
        }
        return ans;
    }
}
// 3.Union-Find
class Solution {
    
    class UF {
        int count;
        int[] parent;
        int[] rank;  //union: change the parent with smaller rank[i] to the other, otherwise, need to change a lot 
        
        public UF(char[][] grid){
            int r = grid.length, c = grid[0].length;
            parent = new int[r*c];
            rank = new int[r*c];
            Arrays.fill(parent, -1);
            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    if(grid[i][j] == '1'){
                        parent[i*c+j] = i*c+j;
                        this.count++;
                    } 
                }
            }
        }
        
        public int find(int i){
            if(parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }
        
        public void union(int m, int n){
            int parent1 = find(m);
            int parent2 = find(n);
            if(parent1!=parent2){
                if(rank[parent1] > rank[parent2]){
                    parent[parent2] = parent1;
                    rank[parent1]++;
                }else if(rank[parent1] < rank[parent2]){
                    parent[parent1] = parent2;
                    rank[parent2]++;
                }else{
                    parent[parent2] = parent1;
                    rank[parent1]++;
                }
                this.count--;
            }
        }
        
        public int getCount(){
            return this.count;
        }
        
        // public void show(int r, int c){
        //     for(int i=0;i<r*c;i++){
        //         System.out.print(parent[i]+" ");
        //     }
        // }
    }
    
    public int numIslands(char[][] grid) {
        int ans = 0;
        if(grid.length==0 || grid[0].length==0) return ans;
        int r = grid.length, c = grid[0].length;
        UF unionfind = new UF(grid);
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(grid[i][j] == '1'){
                    grid[i][j] = '0';
                    if(i+1<=r-1 && grid[i+1][j]=='1'){
                        unionfind.union(i*c+j, (i+1)*c+j);
                    }
                    if(i-1>=0 && grid[i-1][j]=='1'){
                        unionfind.union(i*c+j, (i-1)*c+j);
                    }
                    if(j+1<=c-1 && grid[i][j+1]=='1'){
                        unionfind.union(i*c+j, i*c+j+1);
                    }
                    if(j-1>=0 && grid[i][j-1]=='1'){
                        unionfind.union(i*c+j, i*c+j-1);
                    }
                }
            }
        }
        // unionfind.show(r, c);
        return unionfind.getCount();
    }
}