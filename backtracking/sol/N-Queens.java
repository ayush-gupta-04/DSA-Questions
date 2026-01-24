class Solution {
    List<List<String>> ans = new ArrayList<>();
    private boolean isSafePlace(int n, char[][] nQueens, int row, int col) {
        // Check if there's any queen in the same column above current position
        for (int i = 0; i < n; i++) {
            if (nQueens[i][col] == 'Q') {
                return false;
            }
        }


        // Check upper-left diagonal for any queen
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (nQueens[i][j] == 'Q') {
                return false;
            }
        }


        // Check upper-right diagonal for any queen
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (nQueens[i][j] == 'Q') {
                return false;
            }
        }


        // If no conflicts found, position is safe
        return true;
    }
    public void nQueens(char[][] grid, int row,int n){
        if(n == 0){
            List<String> list = new ArrayList<>();
            for(char[] col : grid){
                list.add(new String(col));
            }
            ans.add(list);
            return;
        }
        if(row == grid.length){
            return;
        }
        for(int i = 0; i < grid.length; i++){
            if(isSafePlace(grid.length,grid,row,i)){
                grid[row][i] = 'Q';
                nQueens(grid,row + 1,n - 1);
                grid[row][i] = '.';
            }
        }


        //if column out of bound --> we can place no queen in this col...hence return.
        return;
    }
    public List<List<String>> solveNQueens(int n) {
        char[][] grid = new char[n][n];
        for(char[] col : grid){
            Arrays.fill(col,'.');
        }
        nQueens(grid,0,n);
        return ans;
    }
}
