// First Thought
// - lets go from every cell to the ocean
// - it will be a brute force task.

// We can go from every ocean (boundaries) to all the cells it can visit.
// if we visit them .. we know that water from that ocean can come here.
// we can visit a cell twice (once with pacific ocean water .. next with atlantic ocean water).

class Solution {
    int[] dr = {0,0,-1,1};
    int[] dc = {-1,1,0,0};
    boolean valid(int r, int c, int n, int m){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }
    public void bfs(int[][] nums ,boolean[][][] vis, int sr, int sc , int ocean){
        int n = nums.length;
        int m = nums[0].length;

        Deque<int[]> q = new ArrayDeque<>();
        
        q.offerLast(new int[]{sr,sc});
        vis[sr][sc][ocean] = true;

        while(!q.isEmpty()){
            int r = q.peekFirst()[0];
            int c = q.peekFirst()[1];
            q.pollFirst();

            for(int i = 0;i < 4;i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(valid(nr,nc,n,m) && !vis[nr][nc][ocean] && nums[nr][nc] >= nums[r][c]){
                    q.offerLast(new int[]{nr,nc});
                    vis[nr][nc][ocean] = true;
                }
            }
        }
    }
    public List<List<Integer>> pacificAtlantic(int[][] nums) {
        int n = nums.length;
        int m = nums[0].length;
        boolean[][][] vis = new boolean[n][m][2];  // 0 for pacific .. 1 for atlantic

        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(i == 0 && !vis[i][j][0]) bfs(nums,vis,i,j,0);  // pacific
                if(j == 0 && !vis[i][j][0]) bfs(nums,vis,i,j,0);  // pacific

                if(i == n-1 && !vis[i][j][1]) bfs(nums,vis,i,j,1);  // atlantic
                if(j == m-1 && !vis[i][j][1]) bfs(nums,vis,i,j,1);  // atlantic
            }
        }

        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(vis[i][j][0] && vis[i][j][1]) ans.add(Arrays.asList(i,j));
            }
        }
        return ans;
    }
}
