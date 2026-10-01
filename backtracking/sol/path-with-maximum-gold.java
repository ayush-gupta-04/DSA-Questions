class Solution {
    int[] dr = {0,0,-1,1};
    int[] dc = {-1,1,0,0};
    boolean valid(int r, int c, int n, int m){
        return (r >= 0 && c >= 0 && r < n && c < m);
    }
    int solve(int r, int c, int[][] grid){
        int gold = grid[r][c];
        grid[r][c] = 0;

        int max = 0;

        for(int i = 0;i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(!valid(nr,nc,grid.length,grid[0].length)) continue;
            if(grid[nr][nc] == 0) continue;
            max = Math.max(max , solve(nr, nc, grid));
        }

        grid[r][c] = gold;
        return max + grid[r][c];
    }
    public int getMaximumGold(int[][] grid) {
        int max = 0;
        int n = grid.length;
        int m = grid[0].length;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 0) continue;
                max = Math.max(max, solve(i,j,grid));
            }
        }
        return max;
    }
}
