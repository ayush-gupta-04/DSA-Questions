// Processing in Topo order.

// time : N*M
// space : N*M


class Solution {
    boolean valid(int r, int c, int n ,int m){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }
    public int longestIncreasingPath(int[][] nums) {
        int n = nums.length;
        int m = nums[0].length;
        int[] dr = {0,0,-1,1};
        int[] dc = {-1,1,0,0};

        int[][] indegree = new int[n][m];

        for(int i = 0;i < n; i++){
            for(int j = 0;j < m; j++){
                for(int k = 0;k < 4;k++){
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if(!valid(nr,nc,n,m)) continue;
                    if(nums[nr][nc] > nums[i][j]){
                        indegree[nr][nc]++;
                    }
                }
            }
        }


        Deque<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[n][m];
        for(int i = 0;i < n;i++){
            for(int j = 0; j< m;j++){
                if(indegree[i][j] == 0){
                    q.offerLast(new int[]{i,j});
                    dist[i][j] = 1;
                }
            }
        }

        while(!q.isEmpty()){
            int r = q.peekFirst()[0];
            int c = q.peekFirst()[1];
            q.pollFirst();

            for(int i = 0;i < 4;i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(!valid(nr,nc,n,m)) continue;

                if(nums[nr][nc] > nums[r][c]){
                    dist[nr][nc] = Math.max(dist[nr][nc], 1 + dist[r][c]);
                    indegree[nr][nc]--;
                    if(indegree[nr][nc]==0){
                        q.offerLast(new int[]{nr,nc});
                    }
                }
            }
        }
        int maxLen = 0;

        for(int i = 0;i < n; i++){
            for(int j=0 ; j< m; j++){
                maxLen = Math.max(maxLen, dist[i][j]);
            }
        }
        return maxLen;
    }
}


// ------------ DFS + Memo --------------
// time : N*M
// space : N + M .. call stack of DFS

class Solution {
    int[][] memo;
    int[] dr = {0, 0, -1, 1};
    int[] dc = {-1, 1, 0, 0};
    int n, m;

    public int longestIncreasingPath(int[][] matrix) {
        n = matrix.length;
        m = matrix[0].length;
        memo = new int[n][m];
        
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxLen = Math.max(maxLen, dfs(matrix, i, j));
            }
        }
        return maxLen;
    }

    private int dfs(int[][] matrix, int r, int c) {
        if (memo[r][c] != 0) return memo[r][c];
        
        int max = 1;
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && matrix[nr][nc] > matrix[r][c]) {
                max = Math.max(max, 1 + dfs(matrix, nr, nc));
            }
        }
        
        memo[r][c] = max;
        return max;
    }
}
