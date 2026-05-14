// ------------------ Brute-Force ------------------
// Time : (N!*N) 
//   - we try all possible permutations of placing the queens and check for safety.
// Space : N^2 + N
//   - N^2 -> for storing ans.
//   - N -> for stack space.

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
        if(row == grid.length){
            List<String> list = new ArrayList<>();
            for(char[] col : grid){
                list.add(new String(col));
            }
            ans.add(list);
            return;
        }

        for(int col = 0; col < grid[0].length; col++){
            if(isSafePlace(grid.length,grid,row,col)){
                grid[row][col] = 'Q';
                nQueens(grid,row + 1,n - 1);   // go to the next row.
                grid[row][col] = '.';
            }
        }

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




// ------------------ Optimal Approach : Using an extra Hash Array --------------------

// It is very inefficient to check the whole col , diagonal.
// we use a precomputed hash array for col,upperRight diagonal , upperLeft Diagonal.
// col[] : 
// - size -> n.
// - access -> col.

// upperRight[] :
// - size -> 2*n - 1.
// - access -> row + col

// upperLeft[] :
// - size -> 2*n - 1.
// - access -> (n-1) + (col-row)

// Time Complexity: (N!)
//   - N! -> we try all possible permutations of placing the queens.
// Space Complexity: N^2 + 4N
//   - N^2 : to store ans.
//   - 4N : 3 for hash array ... 1 for stack space
class Solution {
    List<List<String>> ans = new ArrayList<>();
    public void nQueens(int N,char[][] grid, int row,int n , boolean[] column , boolean[] upperRight , boolean[] upperLeft){
        if(row == N){
            List<String> list = new ArrayList<>();
            for(char[] col : grid){
                list.add(new String(col));
            }
            ans.add(list);
            return;
        }

        for(int col = 0; col < N; col++){
            if(column[col] || upperRight[row+col] || upperLeft[N-1 + (col-row)]) continue;

            grid[row][col] = 'Q';
            column[col] = true;
            upperRight[row + col] = true;
            upperLeft[N-1 + (col-row)] = true;

            nQueens(N,grid,row + 1,n - 1,column,upperRight,upperLeft);   // go to the next row.
            
            grid[row][col] = '.';
            column[col] = false;
            upperRight[row + col] = false;
            upperLeft[N-1 + (col-row)] = false;
        }

        return;
    }
    public List<List<String>> solveNQueens(int n) {
        char[][] grid = new char[n][n];
        for(char[] col : grid){
            Arrays.fill(col,'.');
        }

        boolean[] column = new boolean[n];
        boolean[] upperRight = new boolean[2*n - 1];
        boolean[] upperLeft = new boolean[2*n - 1];
        nQueens(grid.length,grid,0,n,column,upperRight,upperLeft);
        return ans;
    }
}
