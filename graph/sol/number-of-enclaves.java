class Solution {
    int[] dr = {0,0,-1,1};
    int[] dc = {-1,1,0,0};

    boolean valid(int r , int c , int n , int m){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }
    void dfs(int row, int col , boolean[][] vis , int[][] grid){
        vis[row][col] = true;

        for(int i = 0 ; i < 4 ; i++){
            int r = row + dr[i];
            int c = col + dc[i];
            if(valid(r,c,grid.length , grid[0].length) && grid[r][c] == 1 && !vis[r][c]){
                dfs(r,c,vis,grid);
            }
        }
    }
    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];

        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(i==0 || i==n-1 || j==0 || j==m-1){
                    if(grid[i][j] == 1 && !vis[i][j]){
                        dfs(i,j,vis,grid);
                    }
                }
            }
        }

        int cnt = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0; j < m; j++){
                if(!vis[i][j] && grid[i][j] == 1){
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
