class Solution {
public:
    vector<int> dc = {-1,0,1};
    int fun(int r, int c1, int c2, vector<vector<int>>& grid){
        // will handle these edge cases explicitely.
        if(c1 < 0 || c2 < 0 || c1 >= grid[0].size() || c2 >= grid[0].size()) return -1e9;

        // base case
        if(r == grid.size() - 1){
            return (c1 == c2 ? grid[r][c1] : grid[r][c1] + grid[r][c2]);
        }

        int level = 0;
        if(c1 == c2){
            level = grid[r][c1];
        }else{
            level = grid[r][c1] + grid[r][c2];
        }


        int ma = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0 ; j < 3 ; j++){
                int nc1 = c1 + dc[i];
                int nc2 = c2 + dc[j];
                ma = max(ma , fun(r + 1 , nc1, nc2 , grid));
            }
        }

        return ma + level;
    }





    int cherryPickup(vector<vector<int>>& grid) {
        // return fun(0 , 0 , grid[0].size() -1 , grid);

        int n = grid.size();
        int m = grid[0].size();

        vector<vector<vector<int>>> dp(n , vector<vector<int>>(m , vector<int>(m , 0)));

        // filling up the base case.
        for(int c1 = 0; c1 < m ; c1++){
            for(int c2 = 0; c2 < m ; c2++){
                dp[n-1][c1][c2] = (c1 == c2 ? grid[n-1][c1] : grid[n-1][c1] + grid[n-1][c2]);
            }
        }


        // the for loop.
        // recur : r = 0 -> n-1
        //         c1 = 0 -> m-1
        //         c2 = 0 -> m-1
        // for loop : r = n-2 -> 0.
        //            c1 = m-1 -> 0.
        //            c2 = m-1 -> 0.
        for(int r = n-2 ; r >= 0 ; r--){
            for(int c1 = m-1 ; c1 >= 0 ; c1--){
                for(int c2 = m-1 ; c2 >= 0 ; c2--){
                    int level = 0;
                    if(c1 == c2){
                        level = grid[r][c1];
                    }else{
                        level = grid[r][c1] + grid[r][c2];
                    }
                    int ma = 0;
                    for(int i = 0; i < 3; i++){
                        for(int j = 0 ; j < 3 ; j++){
                            int nc1 = c1 + dc[i];
                            int nc2 = c2 + dc[j];
                            // explicit handle the edge cases.
                            int call = ((nc1>=0 && nc1<m && nc2>=0 && nc2<m) ? dp[r+1][nc1][nc2] : -1e9);
                            ma = max(ma ,call);
                        }
                    }
                    dp[r][c1][c2] = ma + level;
                }
            }
        }


        return dp[0][0][m-1];

    }
};
