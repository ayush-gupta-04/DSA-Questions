class Solution {
    private int[] dr = {0,0,-1,1};
    private int[] dc = {-1,1,0,0};
    boolean valid(int r, int c , int n, int m){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }
    void dfs(char[][] board , boolean[][] vis , int r, int c){
        vis[r][c] = true;

        for(int i = 0 ; i < 4 ; i++){
            int row = r + dr[i];
            int col = c + dc[i];
            if(valid(row,col , board.length , board[0].length) && board[row][col] == 'O' && !vis[row][col]){
                dfs(board ,vis , row , col);
            }
        }
    }
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        boolean[][] vis = new boolean[n][m];

        for(int i =0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(i==0 || i==n-1 || j==0 || j==m-1){
                    if(board[i][j] == 'O' && !vis[i][j]){
                        dfs(board,vis,i,j);
                    }
                }
            }
        }

        for(int i = 0 ; i < n; i ++){
            for(int j = 0 ; j < m ; j++){
                if(!vis[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    }
}
