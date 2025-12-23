// The first Traversal would change the grid for the second traversal.
// Consider Both traversal is from (0,0) -> (n-1 , n-1);
// We can think of that 2 person are traversing at the same time.
// if path overlapp i will add cherry once but if not then i will add separately.
// handle edge cases explicitly.


class Solution {
public:
    int fun(int r1, int r2, int c1, int c2, vector<vector<int>>& grid){
        if(r1 < 0 || r2 < 0 || c1 < 0 || c2 < 0) return -1e9;
        if(r1 == 0 && r2 == 0 && c1 == 0 && c2 == 0) return grid[0][0];

        if(grid[r1][c1] == -1 || grid[r2][c2] == -1) return -1e9;

        int a = fun(r1,r2,c1-1,c2-1,grid);
        int b = fun(r1-1,r2-1,c1,c2,grid);
        int c = fun(r1,r2-1,c1-1,c2,grid);
        int d = fun(r1-1,r2,c1,c2-1,grid);
        int level = 0;
        if(r1 == r2 && c1 == c2){
            level = grid[r1][c1];
        }else{
            level = grid[r1][c1] + grid[r2][c2];
        }

        return level + max(a , max(b , max(c , d)));
    }

    int cherryPickup(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<vector<vector<vector<int>>>> dp(n , vector<vector<vector<int>>>(n , vector<vector<int>>(n , vector<int>(n,0))));

        for(int r1 = 0 ; r1 < n ; r1++){
            for(int r2 = 0 ; r2 < n ; r2++){
                for(int c1 = 0; c1 < n; c1++){
                    for(int c2 = 0; c2 < n; c2++){
                        if(r1 == 0 && r2 == 0 && c1 == 0 && c2 == 0) {dp[r1][r2][c1][c2] = grid[0][0] ; continue;};
                        if(grid[r1][c1] == -1 || grid[r2][c2] == -1) {dp[r1][r2][c1][c2] = -1e9 ; continue;};
                        int a = (c1-1 >= 0 && c2-1 >= 0 ? dp[r1][r2][c1-1][c2-1] : -1e9);
                        int b = (r1-1>=0 && r2-1>=0 ? dp[r1-1][r2-1][c1][c2] : -1e9);
                        int c = (c1-1 >=0 && r2-1 >= 0 ? dp[r1][r2-1][c1-1][c2] : -1e9);
                        int d = (r1-1 >= 0 && c2-1 >= 0 ? dp[r1-1][r2][c1][c2-1] : -1e9);
                        int level = 0;
                        if(r1 == r2 && c1 == c2){
                            level = grid[r1][c1];
                        }else{
                            level = grid[r1][c1] + grid[r2][c2];
                        }

                        dp[r1][r2][c1][c2] = level + max(a , max(b , max(c , d)));
                    }
                }
            }
        }

        int ans = dp[n-1][n-1][n-1][n-1];
        return ans < -1e7 ? 0 : ans;
    }
};
