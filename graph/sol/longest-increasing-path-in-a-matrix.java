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
                        // {i,j} -> {nr,nc};
                        indegree[nr][nc]++;
                    }
                }
            }
        }


        Deque<int[]> q = new ArrayDeque<>();
        
        for(int i = 0;i < n;i++){
            for(int j = 0; j< m;j++){
                if(indegree[i][j] == 0){
                    // starting point.
                    q.offerLast(new int[]{i,j,1});
                }
            }
        }

        int maxLen = 0;
        while(!q.isEmpty()){
            int r = q.peekFirst()[0];
            int c = q.peekFirst()[1];
            int step = q.peekFirst()[2];
            q.pollFirst();

            // System.out.println(r + "-" + c + "-" + step);

            maxLen = Math.max(maxLen , step);
            for(int i = 0;i < 4;i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(!valid(nr,nc,n,m)) continue;
                if(nums[nr][nc] > nums[r][c]){
                    indegree[nr][nc]--;
                    if(indegree[nr][nc]==0){
                        q.offerLast(new int[]{nr,nc,step+1});
                    }
                }
            }
        }

        return maxLen;
    }
}
