class Solution {
public:
    vector<int> dr = {0,0,-1,1};
    vector<int> dc = {-1,1,0,0};
    bool valid(int n , int m , int r,int c){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }
    bool find(vector<vector<char>>& board , int r , int c , int idx, vector<vector<bool>>& vis, string word){
        if(idx == word.size()-1){
            return true;
        }
        vis[r][c] = true;
        for(int i = 0;i < 4 ; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(!valid(board.size(),board[0].size(),nr,nc)) continue;
            if(vis[nr][nc]) continue;
            if(!(board[nr][nc] == word[idx+1])) continue;
            if(find(board , nr, nc ,idx+1 , vis , word)) return true;
        }
        vis[r][c] = false;
        return false;
    }
    bool exist(vector<vector<char>>& board, string word) {
        int n = board.size();
        int m = board[0].size();
        vector<vector<bool>> vis(n , vector<bool>(m , false));
        for(int i = 0 ; i < n ; i++){
            for(int j = 0;j < m; j++){
                if(board[i][j] == word[0]){
                    if(find(board , i , j , 0 , vis,word)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
};
