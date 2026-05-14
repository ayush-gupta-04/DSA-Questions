// Time : 9^(n*n)
// Space : 1 .. since we are refilling on the way.

class Solution {
    public boolean canPut(char[][] grid,int row,int col,char c){
        //checking vertically.
        for(int i = 0 ; i < 9 ; i++){
            if(grid[i][col] == c){
                return false;
            }
        }
        //checking horizontally.
        for(int i = 0; i < 9; i++){
            if(grid[row][i] == c){
                return false;
            }
        }


        // c should also not be present in the smaller square where this row and col are.
        int sRow = (row/3)*3;
        int sCol = (col/3)*3;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                if(grid[sRow + i][sCol + j] == c){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean solve(char[][] board){
        // try on every cell.
        for(int i = 0 ;i < 9;i++){
            for(int j = 0;j < 9 ;j++){
                if(board[i][j] != '.') continue;

                for(char c = '1' ; c <= '9' ; c++){
                    if(canPut(board,i,j,c)){
                        board[i][j] = c;
                        if(solve(board)) return true;
                        board[i][j] = '.';
                    }
                }

                // if no number fits .. return false;
                return false;
            }
        }

        return true;
    }

    public void solveSudoku(char[][] board) {
        solve(board);
    }
}
