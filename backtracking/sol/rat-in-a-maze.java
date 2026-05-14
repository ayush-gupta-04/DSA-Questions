// Time : (4^(N*N)) .. every cell could have 4 possible directions to move.
// Space : N*N ... for max depth of recursion.

class Solution {
    int[] dr = {1,0,0,-1};
    int[] dc = {0,-1,1,0};
    char[] dir = {'D','L','R','U'};
    ArrayList<String> res = new ArrayList<>();
    
    private boolean valid(int n, int r ,int c){
        return (r >= 0 && r < n && c >= 0 && c < n);
    }
    
    private void solve(int n, int[][] maze, int r, int c, StringBuilder sb){
        if(r == n-1 && c == n-1){
            res.add(sb.toString());
            return;
        }
            
        maze[r][c] = 0; // mark to not visit again.
            
        for(int i = 0;i < 4 ; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            char direction = dir[i];
            if(valid(n,nr,nc) && maze[nr][nc] == 1){
                sb.append(direction);
                solve(n,maze,nr,nc,sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
        
        maze[r][c] = 1; // backtract .. so unmark
        
    }
    public ArrayList<String> ratInMaze(int[][] maze) {
        // code here
        int n = maze.length;
        if((maze[0][0] == 0) || (maze[n-1][n-1] == 0)) return res;
        
        solve(n,maze,0,0,new StringBuilder());
        return res;
        
    }
}
