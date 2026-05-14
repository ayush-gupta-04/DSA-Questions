// we are marking the track as we go to avoid using vis[].
// Time Complexity: O(m * n * 4^L)
//    - We may start from each of the m×n cells, and explore up to 4 directions for each of the L letters in the word.
// Space Complexity: O(L)
//    - Recursion depth equals the length of the word; we also modify the board in-place, so no extra space for visited tracking.
class Solution {
    int[] dr = {0,0,-1,1};
    int[] dc = {-1,1,0,0};
    boolean valid(int n,int m,int r,int c){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }
    boolean find(char[][] board,int r,int c,int idx,String word){
        // i am standing at (r,c) and i know for 100% that this cell matches with char at idx of word.
        if(idx == word.length() - 1){
            return true;
        }
        char ch = board[r][c];
        board[r][c] = '#';
        for(int i = 0;i < 4;i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(!valid(board.length,board[0].length,nr,nc)) continue;
            if(board[nr][nc] == '#') continue;
            if(!(board[nr][nc] == word.charAt(idx + 1))) continue;

            if(find(board,nr,nc,idx + 1,word)) return true;
        }
        board[r][c] = ch;
        return false;
    }

    public boolean exist(char[][] board,String word) {
        int n = board.length;
        int m = board[0].length;

        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(board[i][j] == word.charAt(0)){
                    if(find(board,i,j,0,word)) return true;
                }
            }
        }
        return false;
    }
}
