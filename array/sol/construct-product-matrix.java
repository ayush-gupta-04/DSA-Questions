// TC -> N*M.
// SC -> 1
class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] p = new int[n][m];
        int MOD = 12345;
        long suff = 1;
        long pre = 1;


        // p[i][j] stores product value from (0,0 -> just before i,j)
        for(int i = 0 ;i < n; i++){
            for(int j = 0 ; j <  m ; j++){
                p[i][j] = (int)pre;
                pre = (pre*grid[i][j])%MOD;
            }
        }

        // final p[i][j] = p[i][j] * suff where p[i][j] stores the prefix , and suff stores the suffix pro.
        for(int i = n-1 ; i>= 0 ;i--){
            for(int j = m-1 ; j >= 0 ; j--){
                p[i][j] = (int)(suff * p[i][j])%MOD;
                suff = (suff * grid[i][j])%MOD;
            }
        }
        return p;
    }
}
