
// just counting components.

class Solution {
    int[] dr = {0,0,1,-1};
    int[] dc = {1,-1,0,0};

    boolean valid(int r, int c , int n , int m){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }
    void dfs(int row, int col , char[][] grid , boolean[][] vis){
        int n = grid.length;
        int m = grid[0].length;
        vis[row][col] = true;
        
        for(int i = 0; i < 4 ; i++){
            int nr = row + dr[i];
            int nc = col + dc[i];
            if(valid(nr,nc,n,m) && grid[nr][nc] == '1' && !vis[nr][nc]){
                dfs(nr,nc,grid,vis);
            }
        }
    }
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        int count = 0;
        
        //for every cell if it is 1 .. do any graph tarversal;
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m ; j++){
                if(grid[i][j] == '1' && !vis[i][j]){
                    dfs(i,j,grid,vis);
                    count++;
                }
            }
        }
        return count;
    }
}
